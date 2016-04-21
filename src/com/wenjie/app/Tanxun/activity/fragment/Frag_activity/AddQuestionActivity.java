package com.wenjie.app.Tanxun.activity.fragment.Frag_activity;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.wenjie.app.Tanxun.R;
import com.wenjie.app.Tanxun.Controller.frag_controller.IAddQuestionView;
import com.wenjie.app.Tanxun.activity.BaseActivity;
import com.wenjie.app.Tanxun.activity.fragment.HomeFragment;
import com.wenjie.app.Tanxun.model.Question;
import com.wenjie.app.Tanxun.model.StudentInfo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * �������Activity
 * @author dell
 *
 */
public class AddQuestionActivity extends Activity implements IAddQuestionView {
	/*
	 * �������ҳ��ؼ�
	 */
	private EditText editque_title;//�������
	private EditText editque_content;//��������
	private Button   editque_commit;//��������
	private static String  nowquesObjectId;//��ǰ����Id
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addquestion_home);
		initView();
	}
	/**
	 * ��ʼ����ͼ
	 */
	public void initView(){
		editque_title=(EditText)findViewById(R.id.addq_title);
		editque_content=(EditText)findViewById(R.id.addq_content);
		editque_commit=(Button)findViewById(R.id.addq_commit);
		//�ύ�¼� 
		editque_commit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveQuestionData(AddQuestionActivity.this);
			}
		});
	}
	/**
	 * �����������������ݿ���
	 */
	public void saveQuestionData(final IAddQuestionView addQuestion){
		/*
		 * ��ȡ����
		 */
		//��ȡ��ǰ�û���Ϣ��studentId��ͼƬ����
		SharedPreferences pref=getSharedPreferences("nowstudentdata", 0);
		String studentId=pref.getString("stuid", "");
		BmobQuery<StudentInfo> stuQue=new BmobQuery<StudentInfo>();
		stuQue.addWhereEqualTo("studentId", studentId);
		stuQue.findObjects(this, new FindListener<StudentInfo>() {
			
			@Override
			public void onSuccess(List<StudentInfo> arg0) {
				Question question=new Question();
				question.setStudentId(arg0.get(0).getStudentId());
				question.setQuestionTitle(editque_title.getText().toString());
				question.setQuestionContent(editque_content.getText().toString());
				
				//��������
				question.save(AddQuestionActivity.this,new SaveListener() {
					
					@Override
					public void onSuccess() {
						Toast.makeText(getApplicationContext(), "�����������ݳɹ���", Toast.LENGTH_SHORT).show();
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						Toast.makeText(getApplicationContext(), "������������ʧ�ܣ�", Toast.LENGTH_SHORT).show();
					}
				});
				//��ȡId
				getQuestionObjectId();
				//�ϴ�ͼƬ
				String picfile=getApplicationContext()
						.getCacheDir()+"/bmob/"+arg0.get(0).getStudentIcon().getFilename();
				final BmobFile bmobFile=new BmobFile(new File(picfile));
				bmobFile.uploadblock(AddQuestionActivity.this, new UploadFileListener() {
					
					@Override
					public void onSuccess() {
						Toast.makeText(getApplicationContext(), "�ϴ�ͼƬ�ɹ���", Toast.LENGTH_SHORT).show();
						//����ͼƬ
						Question que=new Question();
						que.setStudentIcon(bmobFile);
						que.update(AddQuestionActivity.this, nowquesObjectId, new UpdateListener() {
							
							@Override
							public void onSuccess() {
								Toast.makeText(getApplicationContext(), "��������ɹ���", Toast.LENGTH_SHORT).show();
								addQuestion.updateView();
								Intent intent=new Intent(AddQuestionActivity.this,BaseActivity.class);
								startActivity(intent);
							}
							
							@Override
							public void onFailure(int arg0, String arg1) {
								Toast.makeText(getApplicationContext(), "��������ʧ�ܣ�", Toast.LENGTH_SHORT).show();
							}
						});
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						Toast.makeText(getApplicationContext(), "�ϴ�ͼƬʧ�ܣ�", Toast.LENGTH_SHORT).show();
					}
					@Override
					public void onProgress(Integer value) {
						// ���ص��ϴ����ȣ��ٷֱȣ�
					}
				});
				
				
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				
			}
		});
		
		
	}
	
	@Override
	public void updateView() {
		editque_title.setText("");
		editque_content.setText("");
	}
	/**
	 * ͨ����������ȡ��ȡobjectId
	 */
	public void getQuestionObjectId(){
		BmobQuery<Question> query=new BmobQuery<Question>();
		query.addWhereEqualTo("questionTitle", editque_title.getText().toString());
		query.findObjects(this, new FindListener<Question>() {

			@Override
			public void onError(int arg0, String arg1) {
				
			}

			@Override
			public void onSuccess(List<Question> arg0) {
				nowquesObjectId=arg0.get(0).getObjectId();
			}
		});
	}

}
