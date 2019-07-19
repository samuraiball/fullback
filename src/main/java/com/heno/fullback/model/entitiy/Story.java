package com.heno.fullback.model.entitiy;

import com.heno.fullback.model.valueobject.Task;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import java.io.Serializable;
import java.util.List;

/**
 * Story
 */
@Entity
public class Story implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "story_id")
	private String storyId;

	@Column(name = "sprint_id")
	private String sprintId;

	@Column(name = "story_name")
	private String storyName;

	@Column(name = "story_point")
	private Integer storyPoint;

	private List<Task> tasks;

	public String getStoryId() {
		return storyId;
	}

	public String getStoryName() {
		return storyName;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public Integer getStoryPoint() {
		return storyPoint;
	}

	public Integer calcWorkTime() {
		return tasks.stream()
				.mapToInt(t -> t.getWorkTime())
				.sum();
	}

}
