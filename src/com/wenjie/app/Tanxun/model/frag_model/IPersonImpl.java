package com.wenjie.app.Tanxun.model.frag_model;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.listener.FindListener;

import com.wenjie.app.Tanxun.Controller.IStudentInfoView;
import com.wenjie.app.Tanxun.Controller.frag_controller.IModifyInfoView;
import com.wenjie.app.Tanxun.activity.fragment.PersonFragment;
import com.wenjie.app.Tanxun.model.StudentInfo;

public class IPersonImpl implements IPerson {
	IStudentInfoView perFrag=new PersonFragment();
	
	@Override
	public void getStudentInfo(final String studentName,final Context context,final IModifyInfoView imofidyView) {
		
		//��Bmob�����ݿ��в�ѯ 
		BmobQuery<StudentInfo> bmobQuery = new BmobQuery<StudentInfo>();
		bmobQuery.addWhereEqualTo("studentName", studentName);
		bmobQuery.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);    // �ȴӻ����ȡ���ݣ����û�У��ٴ������ȡ��
		bmobQuery.findObjects(context, new FindListener<StudentInfo>() {
			
			@Override
			public void onSuccess(List<StudentInfo> arg0) {
				imofidyView.setTextInfo(arg0.get(0));
				String imagePath=context.getApplicationContext().getCacheDir()+"/bmob/"+
						arg0.get(0).getStudentIcon().getFilename();
				imofidyView.updateImage(imagePath);
				imofidyView.getStudentObjectId(arg0.get(0));
			}
			@Override
			public void onError(int arg0, String arg1) {
				Toast.makeText(context, "�����Ѵ����ڻ���", Toast.LENGTH_SHORT).show();
			}
		});
		
		
	}

}
