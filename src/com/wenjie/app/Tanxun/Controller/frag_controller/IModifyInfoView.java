package com.wenjie.app.Tanxun.Controller.frag_controller;

import cn.bmob.v3.datatype.BmobFile;

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
	 * @param imagePath   ͼƬpath
	 */
	void updateImage(String imagePath);
	void startServiceForupload(BmobFile fileIcon);
	
}
