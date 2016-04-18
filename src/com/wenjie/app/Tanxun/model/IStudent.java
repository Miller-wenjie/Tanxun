package com.wenjie.app.Tanxun.model;

import com.wenjie.app.Tanxun.Controller.ILoginController;
import com.wenjie.app.Tanxun.Controller.IStudentInfoView;

import android.content.Context;

/**
 * 
 * ѧ��ҵ��ӿ�:�����¼��BaseActivity�����ݵķ��ʺʹ���
 * @author dell
 *
 */
public interface IStudent {
	
	/**
	 * ��ʱ����
	 * @param stuId     ѧ��
	 * @param stuPawd   ����
	 * @param context  
	 * @param logincon  ��¼ģ��Controllerʵ��(��ǰActivity)
	 */
	void doLoginHandle(final String stuId, final String stuPawd,final Context context,final ILoginController logincon);
	/**
	 * ��¼����
	 * @param stuId
	 * @param stuPawd
	 * @param context
	 * @param logincon
	 */
	void doLogin(final String stuId, final String stuPawd,final Context context,final ILoginController logincon);
	/**
	 * ͨ��ѧ�Ų�ѯѧ�������ֺ�ͷ��
	 * @param studentId
	 * @param context  ��ǰFragment������Activity
	 */
	void doPersonShow(String studentId,final Context context,final IStudentInfoView infoView);
	/**
	 * ͨ��ѧ�Ų�ѯͷ��pic
	 * @param studentId
	 */
	void queryImageById(String studentId,final Context context,final int i);
}
