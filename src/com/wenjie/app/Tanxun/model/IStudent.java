package com.wenjie.app.Tanxun.model;

import com.wenjie.app.Tanxun.Controller.ILoginController;
import com.wenjie.app.Tanxun.Controller.IStudentInfoView;

import android.content.Context;

/**
 * 
 * ѧ��ҵ��ӿ�
 * @author dell
 *
 */
public interface IStudent {
	/**
	 * ��ʱ����
	 */
	void doLoginHandle(final String stuId, final String stuPawd,final Context context,final ILoginController logincon);
	/**
	 * �����¼���
	 */
	void doLogin(final String stuId, final String stuPawd,final Context context,final ILoginController logincon);
	/**
	 * ͨ��ѧ�Ų�ѯѧ�������ֺ�ͷ��
	 * @param studentId
	 * @param context ��ǰFragment������Activity
	 */
	void doPersonShow(String studentId,final Context context,final IStudentInfoView infoView);
}
