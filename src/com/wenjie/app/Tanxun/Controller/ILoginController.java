package com.wenjie.app.Tanxun.Controller;

import cn.bmob.v3.datatype.BmobFile;
import android.content.Intent;

/**
 * ��¼ģ��
 * �������ӿ�
 * @author dell
 *
 */
public interface ILoginController {
	/**
	 * ���ý������Ƿ�ɼ�
	 */
	void onsetProgressBarVin(int vis);
	/**
	 * ����������
	 */
	void enterBaseActivity(Intent intent);
	/**
	 * �������ظ���ͷ��ĺ�̨����
	 */
	void startServiceForupload(BmobFile fileIcon);
	
}
