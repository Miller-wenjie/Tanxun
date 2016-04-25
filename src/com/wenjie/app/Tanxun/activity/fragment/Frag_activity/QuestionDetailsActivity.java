package com.wenjie.app.Tanxun.activity.fragment.Frag_activity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wenjie.app.Tanxun.R;
import com.wenjie.app.Tanxun.activity.BaseActivity;
import com.wenjie.app.Tanxun.model.Comments;
import com.wenjie.app.Tanxun.model.Question;
import com.wenjie.app.Tanxun.model.StudentInfo;
import com.wenjie.app.Tanxun.model.adapter.CommentsAdapter;
import com.wenjie.app.Tanxun.util.NetWorkImgeUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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
	/*
	 * ���ۿؼ� 
	 */
	private CommentsAdapter adapter;//������
	static List<Comments> listdata=new ArrayList<Comments>(); //�������ݼ���
	private ListView mCommentsListView;  //ListView
	/*
	 * �������ۿؼ� 
	 */
	private Button btnCommit;//����
	private EditText yourComment;//�����ı�
	private ImageButton imgbt_backhome;//����
	/*
	 * ����
	 */
	private int queid; //����id
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_home);
		initQuestionView();
		initQuestionData();
		queryCommentsData();
	}
	
	

	/**
	 * ��ʼ������ؼ�
	 */
	private void initQuestionView() {
		imgbt_backhome=(ImageButton)findViewById(R.id.imgbt_backhome);
		imgbt_backhome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Intent i=new Intent(QuestionDetailsActivity.this,BaseActivity.class);
				//startActivity(i);
				finish();
			}
		});
		deQuestionTitle=(TextView)findViewById(R.id.dequestion_title);
		deQuestionContent=(TextView)findViewById(R.id.dequestion_content);
		deQuestionTime=(TextView)findViewById(R.id.dequestion_time);
		deQuestionIcon=(ImageView)findViewById(R.id.dequestion_Icon);
		btnCommit=(Button)findViewById(R.id.dequestion_commit);
		btnCommit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveCommentData();
			}
		});
		yourComment=(EditText)findViewById(R.id.dequestion_edit);
		mCommentsListView=(ListView)findViewById(R.id.list_comments);
		adapter=new CommentsAdapter(this, R.layout.comments_details_item, listdata);
		mCommentsListView.setAdapter(adapter);
		
	}
	/**
	 * ��ʼ����������
	 */
	private void initQuestionData() {
		//��ȡintent�����qeustionIdֵ
		 queid=getIntent().getIntExtra("questionId",0);
		//������Ӧ���������
		BmobQuery<Question> query=new BmobQuery<Question>();
		query.addWhereEqualTo("questionId", queid);
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
	/**
	 * ��ʼ����������
	 */
	private void queryCommentsData() {
		 BmobQuery<Comments> query = new BmobQuery<Comments>();
			// ��ʱ�併���ѯ
			query.order("-createdAt");
			query.setSkip(0);
			
			query.addWhereEqualTo("questionId", queid);
			// ��������
			query.findObjects(this, new FindListener<Comments>() {
				@Override
				public void onSuccess(List<Comments> list) {
					if(list.size()>0){
						listdata.clear();
						for (Comments td : list) {
							
							listdata.add(td);
						}
						adapter.notifyDataSetChanged();
					}else{
						listdata.clear();
						adapter.notifyDataSetChanged();
					}
					
				
				}
					@Override
					public void onError(int arg0, String arg1) {
						//adapter.notifyDataSetChanged();
					}
			});
	}
	/**
	 * ��ȡ���ݼ���
	 * @return
	 */
	public static List<Comments> getListData(){
		return listdata;
	}
	/**
	 * �������ݿ����۱�
	 */
	public void saveCommentData(){
		
		//��ȡ��ǰ�û���Ϣ��������IconPath��
		SharedPreferences pref=getSharedPreferences("nowstudentdata", 0);
		String studentId=pref.getString("stuid", "");

		BmobQuery<StudentInfo> stuQue=new BmobQuery<StudentInfo>();
		stuQue.addWhereEqualTo("studentId", studentId);
		stuQue.findObjects(this,new FindListener<StudentInfo>() {

			@Override
			public void onError(int arg0, String arg1) {
				Log.d("findObjects", "��ȡ����ʧ��!");
			}

			@Override
			public void onSuccess(List<StudentInfo> arg0) {
				String picIconPath=arg0.get(0).getStudentIcon().getFileUrl(QuestionDetailsActivity.this);
				String userName=arg0.get(0).getStudentName();
				//��ȡ��������
				String content=yourComment.getText().toString();
				Comments comment=new Comments();
				comment.setQuestionId(queid);
				comment.setCommentContent(content);
				comment.setCommentUserIconPath(picIconPath);
				comment.setCommentUserName(userName);
				comment.save(QuestionDetailsActivity.this, new SaveListener() {
					
					@Override
					public void onSuccess() {
						Log.d("save", "�������ݳɹ�!");
						queryCommentsData();
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						Log.d("save", "��������ʧ��!");
					}
				});
				
			}
		});
	}



	@Override
	public void onBackPressed() {
		finish();
	}
	
	
}
