package com.wenjie.app.Tanxun.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
/**
 * ������Ӧģ��
 * @author dell
 *
 */
public class Question extends BmobObject {
	private String studentId; //ѧ��ID
	private Integer questionId;//����ID
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	private String questionTitle; //�������
	private String questionContent; //��������
	private BmobFile studentIcon;//ѧ��ͷ��
	public BmobFile getStudentIcon() {
		return studentIcon;
	}
	public void setStudentIcon(BmobFile studentIcon) {
		this.studentIcon = studentIcon;
	}
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
