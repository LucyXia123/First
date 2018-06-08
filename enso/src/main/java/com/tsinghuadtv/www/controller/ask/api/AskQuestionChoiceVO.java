package com.tsinghuadtv.www.controller.ask.api;

import java.util.ArrayList;
import java.util.List;

import com.tsinghuadtv.www.entity.ask.AskQuestionChoice;

public class AskQuestionChoiceVO {

	private String choice;
	private String content;

	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public static List<AskQuestionChoiceVO> fromAskQuestionChoiceList(List<AskQuestionChoice> choices) {
		
		List<AskQuestionChoiceVO> list = new ArrayList<>();
		for (AskQuestionChoice choice : choices) {
			list.add(fromAskQuestionChoice(choice));
		}
		
		return list;
	}
	
	public static AskQuestionChoiceVO fromAskQuestionChoice(AskQuestionChoice choice) {
		
		AskQuestionChoiceVO vo = new AskQuestionChoiceVO();
		vo.setChoice(choice.getChoice());
		vo.setContent(choice.getContent());
		
		return vo;
	}
}
