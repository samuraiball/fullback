package com.heno.fullback.model.repository;

import com.heno.fullback.model.valueobject.Task;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConfigAutowireable
public interface TaskDao {

	@Select(ensureResult = true)
	Task selectById(String taskId);

	@Select
	List<Task> selectAll();

	@Insert
	int insert(Task task);
}
