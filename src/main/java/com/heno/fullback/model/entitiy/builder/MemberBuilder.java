package com.heno.fullback.model.entitiy.builder;

import com.heno.fullback.model.common.Role;
import com.heno.fullback.model.entitiy.Member;

public class MemberBuilder {
	private String userId;
	private String firstName;
	private String lastName;
	private String middleName;
	private Role role;

	public MemberBuilder withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public MemberBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public MemberBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public MemberBuilder withMiddleName(String middleName) {
		this.middleName = middleName;
		return this;
	}

	public MemberBuilder withRole(Role role) {
		this.role = role;
		return this;
	}

	public Member createMember() {
		return new Member(userId, firstName, lastName, middleName, role);
	}
}