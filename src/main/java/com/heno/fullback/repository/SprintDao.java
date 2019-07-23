package com.heno.fullback.repository;

import com.heno.fullback.model.entitiy.Sprint;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface SprintDao {
	@Select
	Sprint findById(String sprintId);



}
