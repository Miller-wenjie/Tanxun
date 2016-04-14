package com.wenjie.app.Tanxun.model;

import java.util.List;

import com.wenjie.app.Tanxun.Controller.ILoginController;
import com.wenjie.app.Tanxun.Controller.IStudentInfoView;
import com.wenjie.app.Tanxun.activity.BaseActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

/**
 * ѧ��ҵ��ӿ�ʵ����
 * @author dell
 *
 */
public class IStudentImpl implements IStudent {
	
	StudentInfo stuInfo;
	private Handler handler;
	//���췽��
	public IStudentImpl(){
		handler=new Handler(Looper.getMainLooper());
	}
	@Override
	public void doLogin(final String stuId, final String stuPawd,
			final Context context,final ILoginController logincon) {
		
		//��Bmob�����ݿ��в�ѯ 
		BmobQuery<StudentInfo> bmobQuery = new BmobQuery<StudentInfo>();
		bmobQuery.addWhereEqualTo("studentId", stuId);
		bmobQuery.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);    // �ȴӻ����ȡ���ݣ����û�У��ٴ������ȡ��
		bmobQuery.findObjects(context, new FindListener<StudentInfo>() {
			
			@Override
			public void onSuccess(List<StudentInfo> arg0) {
				Intent intent=new Intent(context,BaseActivity.class);
				stuInfo=arg0.get(0);
				if(stuInfo!=null){
					if(stuId.equals(stuInfo.getStudentId())&& 
							stuPawd.equals(stuInfo.getStudentPasswd())){
						
						logincon.onsetProgressBarVin(View.INVISIBLE);
						//��ѧ����Ϣ����intent
						intent.putExtra("studentId", stuId);
						//��һ����װ��Log��ӡ
						Toast.makeText(context, "��½�ɹ�������:"+stuInfo.getStudentName(),
								Toast.LENGTH_SHORT).show();
						BmobFile fileIcon=stuInfo.getStudentIcon();
						if(fileIcon!=null){
							//�ٽ�����ҳ���ͬʱ��������̨�������ظ���ͷ��
							logincon.startServiceForupload(fileIcon);
						}
						logincon.enterBaseActivity(intent);
					}else{
						logincon.onsetProgressBarVin(View.INVISIBLE);
						Toast.makeText(context, "�������!", Toast.LENGTH_SHORT).show();
					}
				}
			}
			
			@Override
			public void onError(int arg0, String arg1) {
//				Toast.makeText(context, "��ѯʧ�ܣ�����ѧ���Ƿ���ȷ", Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	@Override
	public void doLoginHandle(final String stuId, final String stuPawd,
			final Context context,final ILoginController logincon) {
		logincon.onsetProgressBarVin(View.VISIBLE);
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				doLogin(stuId, stuPawd, context,logincon);
			}
		},3000);		
	}
	
	@Override
	public void doPersonShow(String studentId, final Context context,final IStudentInfoView infoView) {
		//ͨ��ѧ�Ų�������
		 BmobQuery<StudentInfo> query=new BmobQuery<StudentInfo>();
		 query.addWhereEqualTo("studentId", studentId);
		 query.setLimit(5);
		 query.findObjects(context, new FindListener<StudentInfo>() {

			@Override
			public void onError(int arg0, String arg1) {
				//Toast.makeText(getActivity(), "��ѯʧ��",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSuccess(List<StudentInfo> studentlist) {
				StudentInfo studentInfo=studentlist.get(0);
				String studentName=studentInfo.getStudentName();
				String imagePath=context.getApplicationContext().getCacheDir()+"/bmob/"+
				studentInfo.getStudentIcon().getFilename();
				infoView.UpdateInfoName(studentName);
				infoView.updateInfoImage(imagePath);
			}
		});
	}

}
