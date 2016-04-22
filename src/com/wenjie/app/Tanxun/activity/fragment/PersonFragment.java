package com.wenjie.app.Tanxun.activity.fragment;

import com.wenjie.app.Tanxun.R;
import com.wenjie.app.Tanxun.Controller.IStudentInfoView;
import com.wenjie.app.Tanxun.activity.LoginActivity;
import com.wenjie.app.Tanxun.activity.fragment.Frag_activity.ModifyInfoActivity;
import com.wenjie.app.Tanxun.model.IStudent;
import com.wenjie.app.Tanxun.model.IStudentImpl;
import com.wenjie.app.Tanxun.util.DataCleanManager;
import com.wenjie.app.Tanxun.util.RoundImage;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
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
	private ImageView imageHead;//ѧ��ͷ��ͼƬ
	private LinearLayout clearCahe;//����������
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		personView=inflater.inflate(R.layout.person, container, false);
		initView();
		return personView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
	}
	

	@Override
	public void onStart() {
		super.onStart();
		initView();
	}

	/**
	 * ��ʼ����ͼ
	 */
	private void initView(){
		Istudent=new IStudentImpl();
		InBaseActivity=getActivity();
		textStuName=(TextView)personView
				.findViewById(R.id.text_studentName);
		imageHead=(ImageView)personView.findViewById(R.id.image_head);
		clearCahe=(LinearLayout)personView.findViewById(R.id.clear_data);
		//�������ļ�ȡ��id
		SharedPreferences pref=getActivity().getSharedPreferences("nowstudentdata", 0);
		studentId=pref.getString("stuid", "");
		//studentId=InBaseActivity.getIntent().getStringExtra("studentId");
		modifyInfoLay=(LinearLayout)personView.findViewById(R.id.modify_info);
		modifyInfoLay.setOnClickListener(this);
		clearCahe.setOnClickListener(this);
		isOnlie=isOnline(studentId);
	}
	@Override
	public void UpdateInfoName(String studentName) {
		this.studentName=studentName;
		textStuName.setText(studentName);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.clear_data:
			Log.d("dianji", "�����");
			DataCleanManager.cleanApplicationData(getActivity().getApplicationContext(), 
					"");
			break;
		case R.id.modify_info:
			if(isOnlie){
					//��ת���޸�ҳ��
					Intent intent=new Intent(InBaseActivity,ModifyInfoActivity.class);
					intent.putExtra("studentName", studentName);
					startActivity(intent);
			}else{
				//��ת����¼ҳ��
				Intent intent=new Intent(InBaseActivity,LoginActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
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
	@Override
	public void updateInfoImage(String imagePath) {

		if(imageHead.getDrawable().getCurrent().getConstantState()
				.equals(getResources().getDrawable(R.drawable.head).getConstantState()))
		{

			if(imagePath!=null){
				Log.d("picupdate", imagePath);
				Bitmap rawBitmap=BitmapFactory.decodeFile(imagePath);
				//�õ�ͼƬԭʼ�ĸ߿�
				int rawHeight = rawBitmap.getHeight();
				int rawWidth = rawBitmap.getWidth();
				// �趨ͼƬ�µĸ߿�
				int newHeight = 90;
				int newWidth = 90;
				// ������������
				float heightScale = ((float) newHeight) / rawHeight;
				float widthScale = ((float) newWidth) / rawWidth;
				// �½�������
				Matrix matrix = new Matrix();
				matrix.postScale(heightScale, widthScale);
				// ѹ����ͼƬ�Ŀ�͸��Լ�kB��С����仯
				Bitmap newBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, rawWidth,
						rawHeight, matrix, true);
				//���մ�ͼ�Ķ���
				if(!rawBitmap.isRecycled())
				{
					rawBitmap.recycle();
				}     
				imageHead.setImageBitmap(RoundImage.roundImage(newBitmap));
			}else{
				Toast.makeText(InBaseActivity, "failed to get image", Toast.LENGTH_SHORT).show();
			}
		}
	}

	
}
