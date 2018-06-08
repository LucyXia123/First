package com.tsinghuadtv.www.test;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tsinghuadtv.www.entity.ask.AskQuestion;
import com.tsinghuadtv.www.entity.ask.UserAskQuestionAnswer;
import com.tsinghuadtv.www.mapper.AskMapper;

public class AskMapperTest extends AbstractPersistenceTest {

	@Autowired
	private AskMapper askMapper;
	
//	@Test
	public void selectAskQuestionsByAskId() {
		
		List<AskQuestion> list = askMapper.selectAskQuestionsByTaskId(1);
		
		printList(list);
	}

//	@Test
	public void batchInsertUserAskQuestionAnswers() {
		
		UserAskQuestionAnswer answer1 = new UserAskQuestionAnswer();
		answer1.setAnswer("A");
		answer1.setAskQuestionId(1);
		answer1.setUserNumber("STU201709291720560001");
		
		UserAskQuestionAnswer answer2 = new UserAskQuestionAnswer();
		answer2.setAnswer("B");
		answer2.setAskQuestionId(2);
		answer2.setUserNumber("STU201709291720560001");
		
		System.out.println(askMapper.batchInsertUserAskQuestionAnswers(Arrays.asList(answer1, answer2)));
	}
}
