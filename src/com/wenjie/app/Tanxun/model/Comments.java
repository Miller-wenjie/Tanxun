package com.wenjie.app.Tanxun.model;

import cn.bmob.v3.BmobObject;
/**
 * ����ʵ��ģ��
 * @author dell
 *
 */
public class Comments extends BmobObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Number questionId;//����Id
	private String commentContent;//��������
	private String commentUserName;//����������
	private String commentUserIconPath;//������ͷ��URL
	public Number getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Number questionId) {
		this.questionId = questionId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentUserName() {
		return commentUserName;
	}
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}
	public String getCommentUserIconPath() {
		return commentUserIconPath;
	}
	public void setCommentUserIconPath(String commentUserIconPath) {
		this.commentUserIconPath = commentUserIconPath;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
