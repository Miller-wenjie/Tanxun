package com.wenjie.app.Tanxun.activity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;

import com.wenjie.app.Tanxun.R;
import com.wenjie.app.Tanxun.Controller.ILoginController;
import com.wenjie.app.Tanxun.model.IStudent;
import com.wenjie.app.Tanxun.model.IStudentImpl;
import com.wenjie.app.Tanxun.service.UploadIconService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * ��¼Activity
 * @author dell
 *
 */
public class LoginActivity extends Activity implements OnClickListener,ILoginController{
	private IStudent Istudent;
	private Button loginButton;//��¼��ť
	private EditText idText;//ѧ��
	private EditText passwdText;//����
	private ProgressBar progressLogin;//Բ�ν�����
	private final String AppId="dbfed305dbefc572cc9f0805b25c9d14";//BmobӦ��ID
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		initView();
	}
	public void initView(){
		//��ʼ��Bmob SDK
		Bmob.initialize(this,AppId);
		//��ʼ���ؼ�
		loginButton=(Button)findViewById(R.id.loginButton);
		idText=(EditText)findViewById(R.id.idText);
		passwdText=(EditText)findViewById(R.id.passwdText);
		progressLogin=(ProgressBar)findViewById(R.id.progresslogin);

		//��ʼ���ý��������ɼ�
		onsetProgressBarVin(View.INVISIBLE);
		loginButton.setOnClickListener(this);
		Istudent=new IStudentImpl();
	}

	@Override
	public void onClick(View v) {
		Intent intent=new Intent(this, BaseActivity.class);
		switch (v.getId()) {
		case R.id.loginButton:
			Istudent.doLoginHandle(idText.getText().toString(),
					passwdText.getText().toString(),this,this);
			break;
		default:
			break;
		}
	}
	@Override
	public void onsetProgressBarVin(int vis) {
		progressLogin.setVisibility(vis);
	}
	@Override
	public void enterBaseActivity(Intent intent) {
		startActivity(intent);
		finish();
	}

	@Override
	public void startServiceForupload(BmobFile fileIcon) {
		Intent intentService=new Intent(this,UploadIconService.class);
		String IconUrl=fileIcon.getFileUrl(this);
		String fileName=fileIcon.getFilename();
		intentService.putExtra("IconUrl",IconUrl);
		intentService.putExtra("fileName", fileName);
		startService(intentService);


	}
}
