package com.wenjie.app.Tanxun.Controller;

import cn.bmob.v3.datatype.BmobFile;

/**
 * ѧ��������Ϣҳ��ӿ�
 * @author dell
 *
 */
public interface IStudentInfoView {
	/**
	 * ����������Ϣ
	 * @param studentName
	 */
	void UpdateInfoName(String studentName);
	/**
	 * ����ͷ��
	 */
	void updateInfoImage(String imagePath);
	
}
