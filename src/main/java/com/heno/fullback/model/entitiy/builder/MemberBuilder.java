package com.heno.fullback.model.entitiy.builder;

import com.heno.fullback.model.entitiy.Member;

public class MemberBuilder {
	private String memberId;
	private String memberName;
	private String mailAddress;
	private String password;

	public MemberBuilder withMemberId(String memberId) {
		this.memberId = memberId;
		return this;
	}

	public MemberBuilder withMemberName(String memberName) {
		this.memberName = memberName;
		return this;
	}

	public MemberBuilder withMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
		return this;
	}

	public MemberBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public Member createMember() {
		return new Member(memberId, memberName, mailAddress, password);
	}
}