package com.wenjie.app.Tanxun.service;

import com.wenjie.app.Tanxun.activity.BaseActivity;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

public class UploadIconService extends IntentService {

	public UploadIconService() {
		super("uploadIconService");
		
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		String IconUrl=intent.getStringExtra("IconUrl");
		String fileName=intent.getStringExtra("fileName");
		//���ڱ��ز����Ƿ���ͷ��ͼƬ,���û�У�����������
		Bitmap rawBitmap=BitmapFactory.decodeFile(getApplicationContext().getCacheDir()+"/bmob/"+fileName);
		if(rawBitmap==null){
			BmobFile bmobFile=new BmobFile(fileName,"",IconUrl);
			bmobFile.download(this, new DownloadFileListener() {

				@Override
				public void onSuccess(String arg0) {
					Log.d("Upload", "���ر���ɹ�!");
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					Log.d("Upload", "���ر���ʧ��!");
				}
			});
		}else{
			Log.d("beidi", "���ش���");
		}
	}

}
