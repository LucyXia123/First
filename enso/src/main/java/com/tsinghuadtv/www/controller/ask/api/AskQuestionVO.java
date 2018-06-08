package com.tsinghuadtv.www.controller.ask.api;

import java.util.ArrayList;
import java.util.List;

import com.tsinghuadtv.www.entity.ask.AskQuestion;

public class AskQuestionVO {

	private Integer id;
	private String context;
	private List<AskQuestionChoiceVO> choices;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public List<AskQuestionChoiceVO> getChoices() {
		return choices;
	}
	public void setChoices(List<AskQuestionChoiceVO> choices) {
		this.choices = choices;
	}
	
	public static List<AskQuestionVO> fromAskQuestionList(List<AskQuestion> questions) {
		
		List<AskQuestionVO> list = new ArrayList<>();
		for (AskQuestion question : questions) {
			list.add(fromAskQuestion(question));
		}
		
		return list;
	}
	
	public static AskQuestionVO fromAskQuestion(AskQuestion question) {
		
		AskQuestionVO vo = new AskQuestionVO();
		
		vo.setId(question.getId());
		vo.setContext(question.getContext());
		vo.setChoices(AskQuestionChoiceVO.fromAskQuestionChoiceList(question.getChoices()));
		
		return vo;
	}
}
