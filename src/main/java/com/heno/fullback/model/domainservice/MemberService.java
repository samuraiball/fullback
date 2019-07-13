package com.heno.fullback.model.domainservice;

import com.heno.fullback.model.valueobject.Member;

import java.util.List;

/**
 * MemberService InterFace.
 * Deal with Member
 */
public interface MemberService {

	List<Member> getAllMembers();
	Member create(Member member);
	Member getMember(String memberId);
	Member updateMember(Member member);
	void deleteMember(String memberId);
}
