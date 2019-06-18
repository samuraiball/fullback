package com.heno.fullback.model.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Role {
	ROLE_SCRUM_MASTER("SCRUM_MASTER"),
	ROLE_TEAM_MEMBER("TEAM_MEMBER"),
	ROLE_PRODUCT_OWNER("PRODUCT_OWNER"),
	ROLE_STAKEHOLDER("STAKEHOLDER"),
	ROLE_ADMIN("ADMIN");

	Role(final String value){
		this.value = value;
	}

	private String value;

	@JsonValue
	public String toValue() {
		return this.value;
	}

	@JsonCreator
	public static Enum fromValue(String value) {
		return Arrays.stream(values())
				.filter(v -> v.value.equals(value))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.valueOf(value)));
	}
}
