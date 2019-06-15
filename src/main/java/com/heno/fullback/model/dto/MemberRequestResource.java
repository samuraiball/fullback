package com.heno.fullback.model.dto;

import com.heno.fullback.model.common.Role;
import com.heno.fullback.model.dto.constraintsgroup.MemberFirstTimeValidationGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MemberRequestResource implements Serializable {

	public MemberRequestResource(
			String memberName,
			String password,
			Role role
	) {
		this.memberName = memberName;
		this.password = password;
		this.role = role;
	}

	@NotNull
	private final String memberName;

	@NotNull(groups = MemberFirstTimeValidationGroup.class)
	private final String password;

	@NotNull
	private final Role role;

	public String getMemberName() {
		return memberName;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}

}
