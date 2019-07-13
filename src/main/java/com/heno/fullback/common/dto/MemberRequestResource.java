package com.heno.fullback.common.dto;

import com.heno.fullback.model.states.Role;
import com.heno.fullback.common.dto.constraintsgroup.MemberAddValidationGroup;
import com.heno.fullback.common.dto.constraintsgroup.MemberUpdateValidationGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MemberRequestResource implements Serializable {

	MemberRequestResource() {
	}


	public MemberRequestResource(
			String memberName,
			String password,
			String mailAddress,
			Role role
	) {
		this.memberName = memberName;
		this.password = password;
		this.mailAddress = mailAddress;
		this.role = role;
	}

	public MemberRequestResource(
			String memberId,
			String memberName,
			String password,
			String mailAddress,
			Role role
	) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.password = password;
		this.mailAddress = mailAddress;
		this.role = role;
	}

	@NotNull(groups = MemberUpdateValidationGroup.class)
	private String memberId;

	@NotNull
	private String memberName;

	@NotNull
	private String mailAddress;

	@NotNull(groups = MemberAddValidationGroup.class)
	private String password;

	@NotNull
	private Role role;


	public String getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getPassword() {
		return password;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public Role getRole() {
		return role;
	}
}
