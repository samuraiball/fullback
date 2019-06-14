package com.heno.fullback.model.dto;

import com.heno.fullback.model.common.Role;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MemberResource implements Serializable {

	public MemberResource(
			String firstName,
			String lastName,
			String accountName,
			Role role
	) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountName = accountName;
		this.role = role;
	}

	@NotNull
	private final String firstName;

	@NotNull
	private final String lastName;

	@NotNull
	private final String accountName;

	@NotNull
	private final Role role;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAccountName() {
		return accountName;
	}

	public Role getRole() {
		return role;
	}
}
