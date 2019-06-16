package com.heno.fullback.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heno.fullback.controller.MemberController;
import com.heno.fullback.model.common.Role;
import com.heno.fullback.model.dto.MemberRequestResource;
import com.heno.fullback.model.entitiy.Member;
import com.heno.fullback.model.entitiy.builder.MemberBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class MemberApiTest {

	@Autowired
	MemberController memberController;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	JdbcTemplate jdbcTemplate;

	private MockMvc mockMvc;

	private String memberId = "1";
	private String memberName = "henohenomohezi";
	private String mailAddress = "henoheno@mohe.zi";
	private String password = "password";


	private Member expectedMember = new MemberBuilder()
			.withMemberId(memberId)
			.withMemberName(memberName)
			.withMailAddress(mailAddress)
			.createMember();

	private MemberRequestResource requestResource =
			new MemberRequestResource(
					memberName,
					password,
					mailAddress,
					Role.TEAM_MEMBER);

	private String expectedMemberStr;
	private String requestResourceStr;


	@BeforeEach
	void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
		expectedMemberStr =
				objectMapper.writeValueAsString(expectedMember);
		requestResourceStr =
				objectMapper.writeValueAsString(requestResource);
	}

	@Test
	@Sql("classpath:META-INF/sql/init-tables.sql")
	void getMemberApiTest() throws Exception {
		mockMvc.perform(get("/member/" + memberId))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedMemberStr));
	}

	@Test
	@Sql("classpath:META-INF/sql/init-tables.sql")
	void addMemberTest() throws Exception {
		MvcResult result = mockMvc.perform(post("/member")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestResourceStr))
				.andExpect(status().isCreated())
				.andReturn();

		Member actual = objectMapper
				.readValue(result.getResponse().getContentAsString(), Member.class);

		Map<String, Object> dbResult = jdbcTemplate
				.queryForMap("select * from member where id = ? ", actual.getMemberId());

		assertThat(dbResult.get("id")).isEqualTo(actual.getMemberId());
		assertThat(dbResult.get("mail_address")).isEqualTo(actual.getMailAddress());
		assertThat(dbResult.get("member_name")).isEqualTo(actual.getMemberName());
		assertThat(dbResult.get("password")).isNotNull();
		assertThat(dbResult.get("password")).isNotEqualTo(password);

		assertThat(actual.getMemberId()).isNotNull();
		assertThat(actual.getMailAddress()).isEqualTo(mailAddress);
		assertThat(actual.getMemberName()).isEqualTo(memberName);
		assertThat(actual.getPassword()).isNull();
	}


	@Test
	@Sql("classpath:META-INF/sql/init-tables.sql")
	void putMemberTest() throws Exception {
		// TODO
	}

	@Test
	@Sql("classpath:META-INF/sql/init-tables.sql")
	void deleteMemberTest() throws Exception {
		// TODO
	}
}
