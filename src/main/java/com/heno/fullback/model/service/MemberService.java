package com.heno.fullback.model.service;

import com.heno.fullback.model.entitiy.Member;

import java.util.List;

public interface MemberService {

	List<Member> getAllMembers();
	Member createMember(Member member);
	Member getMember(String memberId);
}
