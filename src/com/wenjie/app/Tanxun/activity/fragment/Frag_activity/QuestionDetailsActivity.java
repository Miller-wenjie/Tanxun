package com.wenjie.app.Tanxun.activity.fragment.Frag_activity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.wenjie.app.Tanxun.R;
import com.wenjie.app.Tanxun.model.Question;
import com.wenjie.app.Tanxun.util.NetWorkImgeUtil;

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
		//��ȡintent�����qeustionIdֵ
		int id=getIntent().getIntExtra("questionId",0);
		//������Ӧ���������
		BmobQuery<Question> query=new BmobQuery<Question>();
		query.addWhereEqualTo("questionId", id);
		query.findObjects(this, new FindListener<Question>() {
			
			@Override
			public void onSuccess(List<Question> arg0) {
				Question nowqu=arg0.get(0);
				deQuestionTitle.setText(nowqu.getQuestionTitle());
				deQuestionContent.setText(nowqu.getQuestionContent());
				deQuestionTime.setText(nowqu.getCreatedAt());
				//��ȡͼƬURL
				String path=nowqu.getStudentIcon().getFileUrl(QuestionDetailsActivity.this);
				//��ʾͼƬ
				NetWorkImgeUtil.getInstance(QuestionDetailsActivity.this).imageRequest(path, deQuestionIcon);
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				
			}
		});
	}
	
}
