package com.heno.fullback.model.service.impl;

import com.heno.fullback.model.entitiy.Member;
import com.heno.fullback.model.repository.MemberDao;
import com.heno.fullback.model.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao;

	public MemberServiceImpl(MemberDao memberDao) {
		this.memberDao = memberDao;
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
		memberDao.update(member);
		return member;
	}

	@Override
	public Member createMember(Member member) {
		memberDao.insert(member);
		return member;
	}
}
