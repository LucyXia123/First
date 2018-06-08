package com.tsinghuadtv.www.entity.ask;

public class UserAskQuestionAnswer {

	private Integer id;
	private String answer;
	private String userNumber;
	private Integer askQuestionId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public Integer getAskQuestionId() {
		return askQuestionId;
	}
	public void setAskQuestionId(Integer askQuestionId) {
		this.askQuestionId = askQuestionId;
	}
}
