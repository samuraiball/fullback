package com.heno.fullback.controller;

import com.heno.fullback.model.dto.MemberResource;
import com.heno.fullback.model.entitiy.Member;
import com.heno.fullback.model.entitiy.builder.MemberBuilder;
import com.heno.fullback.model.service.MemberService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

/**
 * MemberController
 * A controller class to manipulate Members
 * @author Yuya Hirooka
 */
@RestController
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	/**
	 * handler to get Member's Information
	 * @param userId User id that you want to get the Information
	 * @return Member the User that you want to get the Information
	 * @throws Exception
	 */
	@GetMapping("/users")
	public Member getAllUser(@PathVariable String userId) throws Exception {
		throw new ExecutionControl.NotImplementedException("");
	}

	@GetMapping("/user/{userId}")
	public Member getUser(@PathVariable String userId) throws Exception {
		throw new ExecutionControl.NotImplementedException("");
	}

	/**
	 * handler to add  a new Member
	 * @param member New member's information
	 * @return Created member's information and the URL
	 */
	@PostMapping("/user")
	public ResponseEntity<Member> postUser(
			@RequestBody @Valid MemberResource member,
			UriComponentsBuilder uriBuilder
	) {
		Member createdMember = memberService.createMember(
				new MemberBuilder()
						.withUserId(UUID.randomUUID().toString())
						.withFirstName(member.getFirstName())
						.withLastName(member.getLastName())
						.createMember()
		);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/user/{userId}")
				.buildAndExpand(createdMember.getUserId()).toUri());

		return new ResponseEntity(createdMember, headers, HttpStatus.CREATED);
	}

	@PutMapping("/user/{userId}")
	public Member putUser(@PathVariable String userId) throws Exception {
		throw new ExecutionControl.NotImplementedException("");
	}

	@DeleteMapping("/user/{userId}")
	public Member deleteUser(@PathVariable String userId) throws Exception {
		throw new ExecutionControl.NotImplementedException("");
	}
}
