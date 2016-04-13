package com.wenjie.app.Tanxun.activity.fragment;

import com.wenjie.app.Tanxun.R;
import com.wenjie.app.Tanxun.Controller.IStudentInfoView;
import com.wenjie.app.Tanxun.activity.LoginActivity;
import com.wenjie.app.Tanxun.activity.fragment.Frag_activity.ModifyInfoActivity;
import com.wenjie.app.Tanxun.model.IStudent;
import com.wenjie.app.Tanxun.model.IStudentImpl;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * Person��ҳ
 * @author dell
 *
 */
public class PersonFragment extends Fragment implements IStudentInfoView ,OnClickListener{
	private IStudent Istudent;//������Ϣ��ѯ�Ľӿ�
	private String studentId=null;
	private View personView;
	private Activity InBaseActivity;//��ǰFragment������Activity
	private TextView textStuName;//ѧ�������ı�
	private LinearLayout modifyInfoLay;//�޸����ϲ���
	private boolean isOnlie;//�û��Ƿ�����
	private String studentName="";//ѧ������
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		personView=inflater.inflate(R.layout.person, container, false);
		initView();
		return personView;
	}
	/**
	 * ��ʼ����ͼ
	 */
	private void initView(){
		Istudent=new IStudentImpl();
		InBaseActivity=getActivity();
		textStuName=(TextView)personView
				.findViewById(R.id.text_studentName);
		studentId=InBaseActivity.getIntent().getStringExtra("studentId");
		modifyInfoLay=(LinearLayout)personView.findViewById(R.id.modify_info);
		modifyInfoLay.setOnClickListener(this);
		isOnlie=isOnline(studentId);
	}
	@Override
	public void UpdateInfoName(String studentName) {
		this.studentName=studentName;
		textStuName.setText(studentName);
	}
	@Override
	public void onClick(View v) {
		if(isOnlie){
			if(v.getId()==R.id.modify_info){
				//��ת���޸�ҳ��
				Intent intent=new Intent(InBaseActivity,ModifyInfoActivity.class);
				intent.putExtra("studentName", studentName);
				startActivity(intent);
			}
		}else{
			//��ת����¼ҳ��
			Intent intent=new Intent(InBaseActivity,LoginActivity.class);
			startActivity(intent);
		}
	}
	/**
	 * �ж��û��Ƿ��¼
	 * @param studentId
	 * @return
	 */
	public boolean isOnline(String studentId){
		if(studentId!=null){
			Istudent.doPersonShow(studentId, InBaseActivity,this);
			return true;
		}else{
			textStuName.setText("δ��¼");
			return false;
		}
	}
}
