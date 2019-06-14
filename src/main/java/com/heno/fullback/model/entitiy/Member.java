package com.heno.fullback.model.entitiy;

import com.heno.fullback.model.common.Role;

import java.io.Serializable;

/**
 * user DTO
 * @author Yuya Hirooka
 */
public class Member implements Serializable {

	public Member(String userId, String firstName,
				  String lastName, String middleName, Role role) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.role = role;
	}

	private final String userId;
	private final String  firstName;
	private final String  middleName;
	private final String  lastName;
	private final Role role;

	public String getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public Role getRole() {
		return role;
	}
}
