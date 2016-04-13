package com.wenjie.app.Tanxun.activity.fragment.Frag_activity;

import cn.bmob.v3.listener.InitListener;

import com.wenjie.app.Tanxun.R;
import com.wenjie.app.Tanxun.Controller.frag_controller.IModifyInfoView;
import com.wenjie.app.Tanxun.model.StudentInfo;
import com.wenjie.app.Tanxun.model.frag_model.IPerson;
import com.wenjie.app.Tanxun.model.frag_model.IPersonImpl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
/**
 * �޸ĸ���ͷ��Activity
 * @author dell
 *
 */
public class ModifyInfoActivity extends Activity implements IModifyInfoView{
	private IPerson Iperson;
	private TextView stuNameText;//ѧ�������ı�
	private TextView stuSexText;//ѧ���Ա��ı�
	private TextView stuLevelText;//ѧ���꼶�ı�
	private TextView stuMajorText;//ѧ��רҵ�ı�
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_person);
		initViewInfo();
	}
	/**
	 * ��ʼ����ͼ��Ϣ
	 */
	public void initViewInfo(){
		Iperson=new IPersonImpl();
		stuNameText=(TextView)findViewById(R.id.studentName);
		stuSexText=(TextView)findViewById(R.id.sex);
		stuLevelText=(TextView)findViewById(R.id.level);
		stuMajorText=(TextView)findViewById(R.id.major);
		Iperson.getStudentInfo(getIntent().getStringExtra("studentName"),
				ModifyInfoActivity.this,this);
	}
	@Override
	public void setTextInfo(StudentInfo stuinfo) {
		stuNameText.setText(stuinfo.getStudentName());
		stuSexText.setText(stuinfo.getStudentSex());
		stuLevelText.setText(stuinfo.getStudentLevel());
		stuMajorText.setText(stuinfo.getMajor());
		
	}
	
}
