package com.heno.fullback.model.service;

import com.heno.fullback.model.entitiy.Member;

public interface MemberService {
	Member createMember(Member member);
	Member getMemberInfo(String memberId);
}
