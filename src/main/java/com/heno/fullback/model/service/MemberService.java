package com.heno.fullback.model.service;

import com.heno.fullback.model.entitiy.Member;

import java.util.List;

/**
 * MemberService InterFace.
 * Deal with Member
 */
public interface MemberService {

	List<Member> getAllMembers();
	Member createMember(Member member);
	Member getMember(String memberId);
	Member updateMember(Member member);
	void deleteMember(String memberId);
}
