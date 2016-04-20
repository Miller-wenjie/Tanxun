package com.wenjie.app.Tanxun.model.adapter;

import java.util.List;

import com.wenjie.app.Tanxun.R;
import com.wenjie.app.Tanxun.activity.fragment.HomeFragment;
import com.wenjie.app.Tanxun.activity.fragment.Frag_activity.QuestionDetailsActivity;
import com.wenjie.app.Tanxun.model.Comments;
import com.wenjie.app.Tanxun.model.Question;
import com.wenjie.app.Tanxun.util.NetWorkImgeUtil;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * ����������
 * @author dell
 *
 */
public class CommentsAdapter extends ArrayAdapter<Comments> {
	private Context context;
	private int resourceId;
	public CommentsAdapter(Context context, int resource, List<Comments> objects) {
		super(context, resource, objects);
		this.context=context;
		this.resourceId=resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		ViewHolder viewHodler;
		if(convertView==null){
			viewHodler=new ViewHolder();
			 view=LayoutInflater.from(getContext()).inflate(resourceId,null);
			 viewHodler.commentUserName=(TextView)view.findViewById(R.id.userName);
			 viewHodler.commentIcon=(ImageView)view.findViewById(R.id.userIcon);
			 viewHodler.commentContent=(TextView)view.findViewById(R.id.commentCont);
			 viewHodler.commentTime=(TextView)view.findViewById(R.id.commentTime);
			 view.setTag(viewHodler);
		}else{
			view=convertView;
			viewHodler=(ViewHolder)view.getTag();
		}
		Comments comment=(Comments)getItem(position);
		viewHodler.commentUserName.setText(comment.getCommentUserName());
		viewHodler.commentContent.setText(comment.getCommentContent());
		viewHodler.commentTime.setText(comment.getCreatedAt());
		String picPath=comment.getCommentUserIconPath();
		if(picPath!=null){
	    	   //����������غ��ͼƬ
	    	   NetWorkImgeUtil.getInstance(context).imageRequest(picPath,
	    			   viewHodler.commentIcon);
	       }else{
	    	   viewHodler.commentIcon.setImageResource(com.wenjie.app.Tanxun.R.drawable.ic_launcher);
	       }
		return view;
	}
	@Override
	public Comments getItem(int position) {
		return QuestionDetailsActivity.getListData().get(position);
	}
	class ViewHolder{
		TextView commentUserName;//����������
		TextView commentContent;//��������
		TextView commentTime;//���۷���ʱ��
		ImageView commentIcon;//������ͷ��
	}
	

}
