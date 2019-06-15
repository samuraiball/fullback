package com.heno.fullback.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heno.fullback.controller.MemberController;
import com.heno.fullback.model.entitiy.Member;
import com.heno.fullback.model.entitiy.builder.MemberBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class MemberApiTest {

	@Autowired
	MemberController memberController;

	@Autowired
	ObjectMapper objectMapper;

	private MockMvc mockMvc;
	private String memberId;

	private Member expectedMember = new MemberBuilder()
			.withMemberId("1")
			.withMemberName("henohenomohezi")
			.withMailAddress("henoheno@mohe.zi")
			.createMember();

	private String expectedMemberStr;


	@BeforeEach
	void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
		expectedMemberStr =
				objectMapper.writeValueAsString(expectedMember);
	}

	@Test
	@Sql("classpath:META-INF/sql/init-tables.sql")
	void getMemberApiTest() throws Exception {
		mockMvc.perform(get("/member/" + memberId))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedMemberStr));
	}

	@Test
	void addMemberTest() {

	}
}
