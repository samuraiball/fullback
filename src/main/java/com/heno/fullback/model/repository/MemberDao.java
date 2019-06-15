package com.heno.fullback.model.repository;


import com.heno.fullback.model.entitiy.Member;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@Dao
@ConfigAutowireable
public interface MemberDao {

	@Select
	public Member selectById(String memberId);

	@Select
	List<Member> selectAll();

	@Insert
	int insert(Member member);
}
