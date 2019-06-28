package com.heno.fullback.model.repository;


import com.heno.fullback.model.entitiy.Member;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@Dao
@ConfigAutowireable
public interface MemberDao {


	@Select(ensureResult = true)
	public Member selectById(String memberId);

	@Select
	public Member selectByMailAddress(String mailAddress);

	@Select
	List<Member> selectAll();

	@Insert
	int insert(Member member);

	@Update
	int update(Member member);
}
