package com.wenjie.app.Tanxun.model;

import com.wenjie.app.Tanxun.Controller.ILoginController;

import android.content.Context;

/**
 * ��¼ģ��
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
}
