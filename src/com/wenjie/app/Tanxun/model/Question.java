package com.wenjie.app.Tanxun.model;

import cn.bmob.v3.BmobObject;
/**
 * ������Ӧģ��
 * @author dell
 *
 */
public class Question extends BmobObject {
	private String studentId; //ѧ��ID
	private String questionTitle; //�������
	private String questionContent; //��������
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
}
