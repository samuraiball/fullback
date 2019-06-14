package com.heno.fullback.model.repository;


import com.heno.fullback.model.entitiy.Member;
import org.springframework.stereotype.Repository;

public interface MemberRepository {
	public Member createMember(Member member);
}
