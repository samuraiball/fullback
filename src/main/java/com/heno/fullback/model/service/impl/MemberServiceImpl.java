package com.heno.fullback.model.service.impl;

import com.heno.fullback.exception.DataNotFoundException;
import com.heno.fullback.model.entitiy.Member;
import com.heno.fullback.model.repository.MemberDao;
import com.heno.fullback.model.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao;
	private PasswordEncoder passwordEncoder;

	public MemberServiceImpl(
			MemberDao memberDao,
			PasswordEncoder passwordEncoder
	) {
		this.memberDao = memberDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<Member> getAllMembers() {
		return memberDao.selectAll();
	}

	@Override
	public Member getMember(String memberId) {
		return memberDao.selectById(memberId);
	}

	@Override
	public Member updateMember(Member member) {

		Member targetMember = memberDao.selectById(member.getMemberId());

		if (StringUtils.isEmpty(member.getPassword())) {
			member.setPassword(targetMember.getPassword());
		} else {
			member.setPassword(passwordEncoder.encode(member.getPassword()));
		}

		if (targetMember.isDeleted()) {
			throw new DataNotFoundException();
		}

		memberDao.update(member);
		return member;
	}


	@Override
	public Member createMember(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberDao.insert(member);
		return member;
	}


	@Override
	public void deleteMember(String memberId) {
		Member member = memberDao.selectById(memberId);
		if (member.isDeleted()) {
			throw new DataNotFoundException();
		}
		member.toggleDeleteFlag();
		memberDao.update(member);
	}
}
