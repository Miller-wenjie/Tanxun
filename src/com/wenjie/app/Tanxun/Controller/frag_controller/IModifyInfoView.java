package com.wenjie.app.Tanxun.Controller.frag_controller;

import com.wenjie.app.Tanxun.model.StudentInfo;

/**
 * �޸ĸ�������UI�ӿ�
 * @author dell
 *
 */
public interface IModifyInfoView {
	/**
	 * ���¸�����Ϣ
	 * @param stuinfo
	 */
	void setTextInfo(StudentInfo stuinfo);
	/**
	 * ��ȡ��ǰѧ����ObjectId
	 * @param stuinfo
	 */
	void getStudentObjectId(StudentInfo stuinfo);
	/**
	 * ����ͷ��
	 */
	void updateImage(String imagePath);
}
