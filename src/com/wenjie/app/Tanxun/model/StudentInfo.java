package com.wenjie.app.Tanxun.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
/**
 * ѧ����Ϣʵ����
 * @author dell
 *
 */
public class StudentInfo extends BmobObject {
	private String studentId;//ѧ��
	private String studentPasswd;//����
	private String studentName;//����
	private BmobFile studentIcon;//ѧ���Զ���ͷ��
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentPasswd() {
		return studentPasswd;
	}
	public void setStudentPasswd(String studentPasswd) {
		this.studentPasswd = studentPasswd;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public BmobFile getStudentIcon() {
		return studentIcon;
	}
	public void setStudentIcon(BmobFile studentIcon) {
		this.studentIcon = studentIcon;
	}
	
}
