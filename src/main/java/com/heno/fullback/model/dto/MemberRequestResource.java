package com.heno.fullback.model.dto;

import com.heno.fullback.model.common.Role;
import com.heno.fullback.model.dto.constraintsgroup.MemberFirstTimeValidationGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MemberRequestResource implements Serializable {

	MemberRequestResource(){
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

	@NotNull
	private  String memberName;

	@NotNull
	private  String mailAddress;

	@NotNull(groups = MemberFirstTimeValidationGroup.class)
	private  String password;

	@NotNull
	private  Role role;

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
