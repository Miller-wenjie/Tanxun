package com.wenjie.app.Tanxun.activity.fragment.Frag_activity;

import com.wenjie.app.Tanxun.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * ��������Activity
 * @author dell
 *
 */
public class QuestionDetailsActivity extends Activity {
	/*
	 * ��������ؼ�
	 */
	private TextView deQuestionTitle;//�������
	private TextView deQuestionContent;//��������
	private TextView deQuestionTime;//���ⷢ��ʱ��
	private ImageView deQuestionIcon;//���ⷢ����ͼƬ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_home);
		initQuestionView();
		initQuestionData();
	}
	
	/**
	 * ��ʼ������ؼ�
	 */
	private void initQuestionView() {
		deQuestionTitle=(TextView)findViewById(R.id.dequestion_title);
		deQuestionContent=(TextView)findViewById(R.id.dequestion_content);
		deQuestionTime=(TextView)findViewById(R.id.dequestion_time);
		deQuestionIcon=(ImageView)findViewById(R.id.dequestion_Icon);
	}
	/**
	 * ��ʼ����������
	 */
	private void initQuestionData() {
		int id=getIntent().getIntExtra("questionId",0);
		Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
	}
	
}
