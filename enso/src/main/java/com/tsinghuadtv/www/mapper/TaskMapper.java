package com.tsinghuadtv.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tsinghuadtv.www.entity.task.Task;
import com.tsinghuadtv.www.entity.task.UserTask;
import com.tsinghuadtv.www.model.filter.task.TaskFilter;
import com.tsinghuadtv.www.model.filter.task.UserTaskFilter;

public interface TaskMapper {
	
	// task
	Task getTaskById(@Param("id") int id);
	
	Task getTaskByTopicId(@Param("topicId") int topicId);
	
	List<Task> selectTaskByFilter(@Param("filter") TaskFilter filter);
	
	// user task
	UserTask selectUserTaskByUserAndTask(
			@Param("userNumber") String userNumber, @Param("taskId") Integer taskId);
	
	int insertUserTask(UserTask userTask);
	
	int updateUserTask(UserTask userTask);

	List<UserTask> selectUserTaskByFilter(@Param("filter") UserTaskFilter filter);
	
	int countUserTaskByFilter(@Param("filter") UserTaskFilter filter);
	
}
