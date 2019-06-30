package com.heno.fullback.model.repository;

import com.heno.fullback.model.entitiy.Task;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.stereotype.Repository;

@Repository
@ConfigAutowireable
public interface TaskDao {

	@Select(ensureResult = true)
	public Task selectById(String taskId);

	@Insert
	int insert(Task task);
}
