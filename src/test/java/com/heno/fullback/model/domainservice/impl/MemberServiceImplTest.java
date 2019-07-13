package com.heno.fullback.model.domainservice.impl;

import com.heno.fullback.common.exception.DataNotFoundException;
import com.heno.fullback.model.states.Role;
import com.heno.fullback.model.valueobject.Member;
import com.heno.fullback.model.entitiy.builder.MemberBuilder;
import com.heno.fullback.repository.MemberDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class MemberServiceImplTest {

	@InjectMocks
	MemberServiceImpl memberService;

	@Mock
	private MemberDao memberDao;

	@Mock
	private PasswordEncoder passwordEncoder;


	private final String MEMBER_ID = "memberId";
	private final String MEMBER_NAME = "adminUser";
	private final String MEMBER_MAIL = "adminUser";
	private final String ENCODED_PASS = "encodedPassword";
	private final String OLD_PASS = "oldPassword";
	private final String ROW_PASS = "password";

	private Member member = new MemberBuilder()
			.withMemberId(MEMBER_ID)
			.withRole(Role.ROLE_ADMIN)
			.withMemberName(MEMBER_NAME)
			.withMailAddress(MEMBER_MAIL)
			.withPassword(ROW_PASS)
			.withDeleteFlag(false)
			.createMember();


	Member oldMember = new MemberBuilder()
			.withMemberId(MEMBER_ID)
			.withRole(Role.ROLE_ADMIN)
			.withMemberName(MEMBER_NAME)
			.withMailAddress(MEMBER_MAIL)
			.withPassword(OLD_PASS)
			.withDeleteFlag(false)
			.createMember();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		when(memberDao.selectById(MEMBER_ID)).thenReturn(oldMember);
		when(memberDao.update(member)).thenReturn(1);
		when(passwordEncoder.encode(member.getPassword())).thenReturn(ENCODED_PASS);
	}

	@Test
	void updateMemberWithPassword() {
		Member returnedMember = memberService.updateMember(member);

		assertThat(returnedMember.getPassword()).isEqualTo(ENCODED_PASS);

		verify(passwordEncoder, times(1))
				.encode(ROW_PASS);
	}


	@Test
	void updateMemberWithNoPassword() {
		member.setPassword(null);
		Member returnedMember = memberService.updateMember(member);

		assertThat(returnedMember.getPassword()).isEqualTo(OLD_PASS);

		verify(passwordEncoder, times(0));
	}


	@Test
	void deleteMemberWithNonExistsMember() {
		when(memberDao.selectById(MEMBER_ID)).thenReturn(null);
		assertThrows(DataNotFoundException.class,
				() -> memberService.deleteMember(MEMBER_ID));
	}

	@Test
	void deleteMemberWithDeletedMember() {
		Member deletedMember = new MemberBuilder()
				.withMemberId(MEMBER_ID)
				.withRole(Role.ROLE_ADMIN)
				.withMemberName(MEMBER_NAME)
				.withMailAddress(MEMBER_MAIL)
				.withPassword(OLD_PASS)
				.withDeleteFlag(true)
				.createMember();

		when(memberDao.selectById(MEMBER_ID)).thenReturn(deletedMember);
		assertThrows(DataNotFoundException.class,
				() -> memberService.deleteMember(MEMBER_ID));
	}
}


