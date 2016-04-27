package com.wenjie.app.Tanxun.activity.fragment.Frag_activity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.wenjie.app.Tanxun.R;
import com.wenjie.app.Tanxun.model.Comments;
import com.wenjie.app.Tanxun.model.adapter.CommentsAdapter;
import com.wenjie.app.Tanxun.model.adapter.MyQueCommentsAdapter;
import com.wenjie.app.Tanxun.model.adapter.MyQuestionAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
/**
 * �ҵ�������������
 * @author dell
 *
 */
public class MyQuestionComments extends Activity {
	/*
	 * ���ۿؼ� 
	 */
	private MyQueCommentsAdapter adapter;//������
	static List<Comments> listdata=new ArrayList<Comments>(); //�������ݼ���
	private ListView mCommentsListView;  //ListView
	private TextView tvCommentNum;//����
	private ImageButton ibBackMyqedetail;//���ؼ�
	/*
	 * ����
	 */
	private int queid; //����id
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comments_myqe_home);
		initView();
		queryCommentsData();
	}
	
	/**
	 * ��ʼ���ؼ�
	 */
	public void initView() {
		queid=getIntent().getIntExtra("myquedetailid", 0);
		tvCommentNum=(TextView)findViewById(R.id.tv_comment_num);
		ibBackMyqedetail=(ImageButton)findViewById(R.id.ib_back_myqedetail);
		ibBackMyqedetail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mCommentsListView=(ListView)findViewById(R.id.lv_comments);
		adapter=new MyQueCommentsAdapter(this, R.layout.comments_myqe_item, listdata);
		mCommentsListView.setAdapter(adapter);
	}
	/**
	 * ��ѯ��������
	 */
	public void queryCommentsData() {
		
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
						int num=0;
						for (Comments td : list) {
							listdata.add(td);
							num++;
						}
						setTitleBar(num);
						adapter.notifyDataSetChanged();
					}else{
						listdata.clear();
						adapter.notifyDataSetChanged();
					}
				}
					@Override
					public void onError(int arg0, String arg1) {
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
	 * ���ñ�������
	 * @param num �ش����
	 */
	public void setTitleBar(int num){
		tvCommentNum.setText(String.valueOf(num)+"���ش�");
	}
	
	
}
