package com.tsinghuadtv.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tsinghuadtv.www.entity.ask.AskQuestion;
import com.tsinghuadtv.www.entity.ask.UserAskQuestionAnswer;

public interface AskMapper {
	
	List<AskQuestion> selectAskQuestionsByTaskId(@Param("taskId") int taskId);

	int batchInsertUserAskQuestionAnswers(@Param("answers") List<UserAskQuestionAnswer> answers);
	
}
