package com.heno.fullback.controller;

import com.heno.fullback.exception.ForbiddenException;
import com.heno.fullback.model.dto.MemberRequestResource;
import com.heno.fullback.model.dto.constraintsgroup.MemberAddValidationGroup;
import com.heno.fullback.model.dto.constraintsgroup.MemberUpdateValidationGroup;
import com.heno.fullback.model.entitiy.Member;
import com.heno.fullback.model.entitiy.builder.MemberBuilder;
import com.heno.fullback.model.service.MemberService;
import com.heno.fullback.security.MemberUserDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
@EnableWebMvc
public class MemberController {

	private final MemberService memberService;

	public MemberController(
			MemberService memberService
	) {
		this.memberService = memberService;
	}

	/**
	 * Handler to get all Member's Information
	 *
	 * @return All Members (includes deleted members)
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
						.withPassword(memberRequestResource.getPassword())
						.withDeleteFlag(false)
						.createMember());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/member/{memberId}")
				.buildAndExpand(createdMember.getMemberId()).toUri());
		return new ResponseEntity(createdMember, headers, HttpStatus.CREATED);
	}

	/**
	 * Handler to update a member info.
	 * Allowed to Admin or when update own info
	 *
	 * @param memberRequestResource New member's information
	 * @return Updated member's information and the URL
	 */
	@PutMapping("/member")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Member> putUser(
			@RequestBody @Validated(MemberUpdateValidationGroup.class)
					MemberRequestResource memberRequestResource,
			@AuthenticationPrincipal
					MemberUserDetail memberUserDetail,
			UriComponentsBuilder uriBuilder
	) {

		if (!memberUserDetail.getMemberId()
				.equals(memberRequestResource.getMemberId()) && !memberUserDetail.isAdmin()) {
			throw new ForbiddenException();
		}

		Member updatedMember = memberService.updateMember(
				new MemberBuilder()
						.withMailAddress(memberRequestResource.getMailAddress())
						.withMemberName(memberRequestResource.getMemberName())
						.withMemberId(memberRequestResource.getMemberId())
						.withPassword(memberRequestResource.getPassword())
						.withRole(memberRequestResource.getRole())
						.createMember());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/member/{memberId}")
				.buildAndExpand(updatedMember.getMemberId()).toUri());
		return new ResponseEntity(updatedMember, headers, HttpStatus.OK);
	}


	/**
	 * Handler to delete a member.
	 * Allowed to Admin.
	 */
	@DeleteMapping("/member/{memberId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(
			@PathVariable String memberId,
			@AuthenticationPrincipal
					MemberUserDetail memberUserDetail
	) throws Exception {

		if (!memberUserDetail.isAdmin()) throw new ForbiddenException();
		memberService.deleteMember(memberId);
	}
}
