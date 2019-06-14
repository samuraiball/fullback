package com.heno.fullback.model.repository.impl;

import com.heno.fullback.model.entitiy.Member;
import com.heno.fullback.model.entitiy.builder.MemberBuilder;
import com.heno.fullback.model.repository.MemberRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryMock implements MemberRepository {
	@Override
	public Member createMember(Member member) {
		return new MemberBuilder().createMember();
	}
}
