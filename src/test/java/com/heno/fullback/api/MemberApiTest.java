package com.heno.fullback.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heno.fullback.controller.MemberController;
import com.heno.fullback.model.common.Role;
import com.heno.fullback.model.dto.MemberRequestResource;
import com.heno.fullback.model.entitiy.Member;
import com.heno.fullback.model.entitiy.builder.MemberBuilder;
import com.heno.fullback.security.MemberUserDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MemberApiTest {

	@Autowired
	MemberController memberController;

	@Autowired
	WebApplicationContext context;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	JdbcTemplate jdbcTemplate;

	private MockMvc mockMvc;

	private String memberId = "1";
	private String memberName = "henohenomohezi1";
	private String mailAddress = "henoheno1@mohe.zi";
	private String password = "password";
	private Role role = Role.ROLE_TEAM_MEMBER;


	private Member expectedMember1 = new MemberBuilder()
			.withMemberId(memberId)
			.withMemberName(memberName)
			.withRole(role)
			.withMailAddress(mailAddress)
			.createMember();


	private MemberRequestResource requestResource =
			new MemberRequestResource(
					memberName,
					password,
					mailAddress,
					Role.ROLE_TEAM_MEMBER);

	private String expectedMemberStr;
	private String requestResourceStr;


	private HandlerMethodArgumentResolver putAuthenticationPrincipal = new HandlerMethodArgumentResolver() {
		@Override
		public boolean supportsParameter(MethodParameter parameter) {
			return parameter.getParameterType().isAssignableFrom(MemberUserDetail.class);
		}

		@Override
		public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
									  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			return new MemberUserDetail(
					new MemberBuilder()
							.withMemberId("1")
							.withPassword("")
							.withMemberName("")
							.withRole(Role.ROLE_ADMIN)
							.createMember());
		}
	};

	private HandlerMethodArgumentResolver putForbiddenAuthenticationPrincipal = new HandlerMethodArgumentResolver() {
		@Override
		public boolean supportsParameter(MethodParameter parameter) {
			return parameter.getParameterType().isAssignableFrom(MemberUserDetail.class);
		}

		@Override
		public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
									  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			return new MemberUserDetail(
					new MemberBuilder()
							.withMemberId("1")
							.withRole(Role.ROLE_TEAM_MEMBER)
							.createMember());
		}
	};


	@BeforeEach
	void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
		expectedMemberStr =
				objectMapper.writeValueAsString(expectedMember1);
		requestResourceStr =
				objectMapper.writeValueAsString(requestResource);
	}


	@Test
	@Sql("classpath:META-INF/sql/init-tables.sql")
	void getAllMembersApiTest() throws Exception {

		Member expectedMember2 = new MemberBuilder()
				.withMemberId("2")
				.withMemberName("henohenomohezi2")
				.withRole(Role.ROLE_TEAM_MEMBER)
				.withMailAddress("henoheno2@mohe.zi")
				.createMember();

		List expectList = new ArrayList<Member>();

		expectList.add(expectedMember1);
		expectList.add(expectedMember2);

		String expectMembersStr =
				objectMapper.writeValueAsString(expectList);

		// get data which inserted with init-tables.sql
		mockMvc.perform(get("/members"))
				.andExpect(status().isOk())
				.andExpect(content().json(expectMembersStr));
	}

	@Test
	@Sql("classpath:META-INF/sql/init-tables.sql")
	void getMemberApiTest() throws Exception {

		// get data which inserted with init-tables.sql
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

		// check if data is stored in DB
		Map<String, Object> dbResult = jdbcTemplate
				.queryForMap("select * from member where id = ? ", actual.getMemberId());
		assertThat(dbResult.get("id")).isEqualTo(actual.getMemberId());
		assertThat(dbResult.get("mail_address")).isEqualTo(actual.getMailAddress());
		assertThat(dbResult.get("member_name")).isEqualTo(actual.getMemberName());
		assertThat(dbResult.get("role")).isEqualTo("ROLE_TEAM_MEMBER");
		assertThat(dbResult.get("password")).isNotNull();
		assertThat(dbResult.get("password")).isNotEqualTo(password);

		// assert returned value
		assertThat(actual.getMemberId()).isNotNull();
		assertThat(actual.getMailAddress()).isEqualTo(mailAddress);
		assertThat(actual.getMemberName()).isEqualTo(memberName);
		assertThat(actual.getPassword()).isNull();
	}


	@Test
	@Sql("classpath:META-INF/sql/init-tables.sql")
	void putMemberTest() throws Exception {

		MemberRequestResource requestResource =
				new MemberRequestResource(
						memberId,
						memberName,
						password,
						mailAddress,
						Role.ROLE_TEAM_MEMBER);

		String requestResourceStr =
				objectMapper.writeValueAsString(requestResource);

		mockMvc = MockMvcBuilders
				.standaloneSetup(memberController)
				.setCustomArgumentResolvers(putAuthenticationPrincipal)
				.build();

		MvcResult result = mockMvc.perform(put("/member")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestResourceStr))
				.andExpect(status().isOk())
				.andReturn();
	}



	@Test
	@Sql("classpath:META-INF/sql/init-tables.sql")
	//FIXME
	void putMemberForbiddenTest() throws Exception {

		MemberRequestResource requestResource =
				new MemberRequestResource(
						memberId,
						memberName,
						password,
						mailAddress,
						Role.ROLE_TEAM_MEMBER);

		String requestResourceStr =
				objectMapper.writeValueAsString(requestResource);

		mockMvc = MockMvcBuilders
				.standaloneSetup(memberController)
				.setCustomArgumentResolvers(putForbiddenAuthenticationPrincipal)
				.build();

		MvcResult result = mockMvc.perform(put("/member")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestResourceStr))
				.andExpect(status().isForbidden())
				.andReturn();
	}

	@Test
	@Sql("classpath:META-INF/sql/init-tables.sql")
	void deleteMemberTest() throws Exception {
		// TODO
	}
}
