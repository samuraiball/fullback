package com.heno.fullback.controller;

import com.heno.fullback.model.dto.MemberRequestResource;
import com.heno.fullback.model.dto.constraintsgroup.MemberAddValidationGroup;
import com.heno.fullback.model.dto.constraintsgroup.MemberUpdateValidationGroup;
import com.heno.fullback.model.entitiy.Member;
import com.heno.fullback.model.entitiy.builder.MemberBuilder;
import com.heno.fullback.model.service.MemberService;
import com.heno.fullback.security.MemberUserDetail;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

/**
 * MemberController
 * A handler class to manipulate Members
 *
 * @author Yuya Hirooka
 */
@RestController
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	public MemberController(
			MemberService memberService,
			PasswordEncoder passwordEncoder
	) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Handler to get all Member's Information
	 *
	 * @return getAllMembers
	 */
	@GetMapping("/members")
	public List<Member> getAllMembers() {
		return memberService.getAllMembers();
	}

	/**
	 * Handler to get  Member's Information that Member is specified by ID.
	 *
	 * @param memberId User id that you want to get the Information
	 * @return Member
	 */
	@GetMapping("/member/{memberId}")
	public Member getMember(@PathVariable String memberId) {
		return memberService.getMember(memberId);
	}

	/**
	 * Handler to add  a new Member.
	 *
	 * @param memberRequestResource New member's information
	 * @return Created member's information and the URL
	 */
	@PostMapping("/member")
	public ResponseEntity<Member> postMember(
			@RequestBody @Validated(MemberAddValidationGroup.class)
					MemberRequestResource memberRequestResource,
			UriComponentsBuilder uriBuilder
	) {
		Member createdMember = memberService.createMember(
				new MemberBuilder()
						.withMemberId(UUID.randomUUID().toString())
						.withMemberName(memberRequestResource.getMemberName())
						.withRole(memberRequestResource.getRole())
						.withMailAddress(memberRequestResource.getMailAddress())
						.withPassword(passwordEncoder.encode(memberRequestResource.getPassword()))
						.createMember()
		);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/member/{memberId}")
				.buildAndExpand(createdMember.getMemberId()).toUri());
		return new ResponseEntity(createdMember, headers, HttpStatus.CREATED);
	}

	@PutMapping("/member")
	@ResponseStatus(HttpStatus.OK)
	public Member putUser(
			@RequestBody @Validated(MemberUpdateValidationGroup.class)
					MemberRequestResource memberRequestResource,
			@AuthenticationPrincipal
					MemberUserDetail memberUserDetail
	) {

		//FIXME:Exceptionを作成する。
		//FIXME:楽観ロックのハンドリング
		if (!memberUserDetail.getMemberId().equals(memberRequestResource.getMemberId())) {
			throw new RuntimeException();
		}
		return memberService.updateMember(
				new MemberBuilder()
						.withMemberId(memberRequestResource.getMemberId())
						.withRole(memberRequestResource.getRole())
						.createMember());
	}

	@DeleteMapping("/member/{memberId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Member deleteUser(@PathVariable String memberId) throws Exception {
		throw new ExecutionControl.NotImplementedException("");
	}
}
