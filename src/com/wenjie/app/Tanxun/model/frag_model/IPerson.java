package com.wenjie.app.Tanxun.model.frag_model;

import android.content.Context;

import com.wenjie.app.Tanxun.Controller.frag_controller.IModifyInfoView;
import com.wenjie.app.Tanxun.model.StudentInfo;

/**
 * ����ҳ������ӿ�
 * @author dell
 *
 */
public interface IPerson {
	/**
	 * ��ȡ��ǰѧ����Ϣ����
	 * @return
	 */
	void getStudentInfo(final String studentName,final Context context,final IModifyInfoView imofidyView);
}
