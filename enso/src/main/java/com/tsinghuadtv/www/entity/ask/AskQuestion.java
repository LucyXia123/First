package com.tsinghuadtv.www.entity.ask;

import java.util.List;

public class AskQuestion {

	private Integer id;
	private String context;
	private Integer sequence;
	private Integer askId;
	
	private List<AskQuestionChoice> choices;
	
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
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Integer getAskId() {
		return askId;
	}
	public void setAskId(Integer askId) {
		this.askId = askId;
	}
	public List<AskQuestionChoice> getChoices() {
		return choices;
	}
	public void setChoices(List<AskQuestionChoice> choices) {
		this.choices = choices;
	}
	
}
