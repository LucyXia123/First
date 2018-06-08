package com.tsinghuadtv.www.entity.ask;

public class AskQuestionChoice {

	private Integer id;
	private String choice;
	private String content;
	private Integer askQuestionId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getAskQuestionId() {
		return askQuestionId;
	}
	public void setAskQuestionId(Integer askQuestionId) {
		this.askQuestionId = askQuestionId;
	}
}
