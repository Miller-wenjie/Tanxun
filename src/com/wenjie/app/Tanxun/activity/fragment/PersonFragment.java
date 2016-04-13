package com.wenjie.app.Tanxun.activity.fragment;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.wenjie.app.Tanxun.R;
import com.wenjie.app.Tanxun.Controller.IStudentInfoView;
import com.wenjie.app.Tanxun.model.IStudent;
import com.wenjie.app.Tanxun.model.IStudentImpl;
import com.wenjie.app.Tanxun.model.StudentInfo;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Person��ҳ
 * @author dell
 *
 */
public class PersonFragment extends Fragment implements IStudentInfoView {
	private IStudent Istudent;//������Ϣ��ѯ�Ľӿ�
	private String studentId=null;
	private String studentName="";
	private View personView;
	private Activity InBaseActivity;//��ǰFragment������Activity
	private TextView textStuName;//ѧ�������ı�
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
		if(studentId!=null){
			Istudent.doPersonShow(studentId, InBaseActivity,this);
		}else{
			textStuName.setText("δ��¼");
		}
		
	}
	@Override
	public void UpdateInfoName(String studentName) {
		textStuName.setText(studentName);
	}
}
