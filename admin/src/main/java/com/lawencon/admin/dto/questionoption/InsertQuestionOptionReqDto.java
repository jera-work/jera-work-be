package com.lawencon.admin.dto.questionoption;

public class InsertQuestionOptionReqDto {

	private String optionLabel;
	private Boolean isCorrect;

	public String getOptionLabel() {
		return optionLabel;
	}

	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

}
