package com.wenjie.app.Tanxun.activity.fragment.Frag_activity;

import java.io.File;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.wenjie.app.Tanxun.R;
import com.wenjie.app.Tanxun.Controller.IStudentInfoView;
import com.wenjie.app.Tanxun.Controller.frag_controller.IModifyInfoView;
import com.wenjie.app.Tanxun.activity.BaseActivity;
import com.wenjie.app.Tanxun.activity.fragment.PersonFragment;
import com.wenjie.app.Tanxun.model.StudentInfo;
import com.wenjie.app.Tanxun.model.frag_model.IPerson;
import com.wenjie.app.Tanxun.model.frag_model.IPersonImpl;
import com.wenjie.app.Tanxun.service.UploadIconService;
import com.wenjie.app.Tanxun.util.RoundImage;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * �޸ĸ���ͷ��Activity
 * @author dell
 *
 */
public class ModifyInfoActivity extends Activity implements IModifyInfoView{
	public static final int CHOOSE_PHOTO=3;
	private IPerson Iperson;
	private TextView stuNameText;//ѧ�������ı�
	private TextView stuSexText;//ѧ���Ա��ı�
	private TextView stuLevelText;//ѧ���꼶�ı�
	private TextView stuMajorText;//ѧ��רҵ�ı�
	private ImageView personImage;//����ͷ��
	private TextView modifyText;//�༭�ı�
	private TextView uploadText;//����ı�
	private String picPath="";//ͼƬ·��
	private String personObjectId;//��ǰѧ����ObjectId
	private String nowpicPath;//��ǰͼƬ·��
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_person);
		initViewInfo();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initViewInfo();
	}

	/**
	 * ��ʼ����ͼ��Ϣ
	 */
	public void initViewInfo(){
		Iperson=new IPersonImpl();
		personImage=(ImageView)findViewById(R.id.person_image);
		modifyText=(TextView)findViewById(R.id.bianji_text);
		uploadText=(TextView)findViewById(R.id.ok_upload);
		stuNameText=(TextView)findViewById(R.id.studentName);
		stuSexText=(TextView)findViewById(R.id.sex);
		stuLevelText=(TextView)findViewById(R.id.level);
		stuMajorText=(TextView)findViewById(R.id.major);
		//����ѧ����Ϣ�ı�
		Iperson.getStudentInfo(getIntent().getStringExtra("studentName"),
				ModifyInfoActivity.this,this);
		//�༭�ı�ע�����¼�
		modifyText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent("android.intent.action.GET_CONTENT");//������ͼ
				intent.setType("image/*");//����MIME��������
				startActivityForResult(intent, CHOOSE_PHOTO);//�����
			}
		});
		uploadText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(isChoose()){
					doUploadImage();
				}else{
					Toast.makeText(ModifyInfoActivity.this, "�����ϴ���ͷ���Ǿͷ��ذ�", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}
	//�����ͷ��ͼ��ص�APP�е�ͷ��һ�£����ϴ�
	public boolean isChoose(){
		if(picPath.equals(nowpicPath)||picPath.equals("")){
			return false;
		}
		else{
			return true;
		}
	}
	@Override
	public void setTextInfo(StudentInfo stuinfo) {
		stuNameText.setText(stuinfo.getStudentName());
		stuSexText.setText(stuinfo.getStudentSex());
		stuLevelText.setText(stuinfo.getStudentLevel());
		stuMajorText.setText(stuinfo.getMajor());
		
	}
	@Override
	public void updateImage(String imagePath) {
		if(personImage.getDrawable().getCurrent().getConstantState()
				.equals(getResources().getDrawable(R.drawable.head).getConstantState())){

			if(imagePath!=null){
				nowpicPath=imagePath;
				Bitmap rawBitmap=BitmapFactory.decodeFile(imagePath);
				//�õ�ͼƬԭʼ�ĸ߿�
				int rawHeight = rawBitmap.getHeight();
				int rawWidth = rawBitmap.getWidth();
				// �趨ͼƬ�µĸ߿�
				int newHeight = 80;
				int newWidth = 80;
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
				personImage.setImageBitmap(RoundImage.roundImage(newBitmap));
			}else{
				Toast.makeText(ModifyInfoActivity.this, "failed to get image", Toast.LENGTH_SHORT).show();
			}
		}
	}
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		switch (requestCode) {
		case CHOOSE_PHOTO:
			if(resultCode==RESULT_OK){
				if(Build.VERSION.SDK_INT>=19){
					//4.4������ϵͳʹ�������������ͼƬ
					handleImageOnKitKat(data);
				}else{
					//4.4����
					handleImageBeforeKitKat(data);
				}
			}
			break;
		default:
			break;
		}
	}
	@TargetApi(19)
	private void handleImageOnKitKat(Intent data){
		String imagePath=null;
		Uri uri=data.getData();
		if(DocumentsContract.isDocumentUri(this, uri)){
			//�����document���͵�Uri,��ͨ��document id����
			String docId=DocumentsContract.getDocumentId(uri);
			if("com.android.providers.media.documents".equals(uri.getAuthority())){
				//���������ָ�ʽ��id
				String id=docId.split(":")[1];
				String selection=MediaStore.Images.Media._ID+"="+id;
				imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
			}else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
				Uri contentUri=ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
				imagePath=getImagePath(contentUri, null);
			}else if("content".equalsIgnoreCase(uri.getScheme())){
				//�������document���͵�Uri,��ʹ����ͨ��ʽ����
				imagePath=getImagePath(uri, null);
			}

		}
	}
	private String getImagePath(Uri uri,String selection){
		String path=null;
		//ͨ��Uri��Selection����ȡ��ʵ��ͼƬ·��
		Cursor cursor=getContentResolver().query(uri, null, selection, null, null);
		if(cursor!=null){
			if(cursor.moveToFirst()){
				path=cursor.getString(cursor.getColumnIndex(Media.DATA));
			}
			cursor.close();
		}
		return path;


	}
	private void handleImageBeforeKitKat(Intent data){
		Uri uri=data.getData();
		String imagePath=getImagePath(uri, null);
		displayImage(imagePath);
	}
	private void displayImage(String imagePath){
		if(imagePath!=null){
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
			personImage.setImageBitmap(RoundImage.roundImage(newBitmap));
			Log.d("Show", imagePath);
			picPath=imagePath;
		}else{
			Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
		}
	}
	/**
	 * �ϴ�����ͷ���������������浽StudentInfo����
	 */
	public void doUploadImage(){
		final BmobFile bmobFile = new BmobFile(new File(picPath));
		bmobFile.uploadblock(ModifyInfoActivity.this, new UploadFileListener() {
			@Override
			public void onSuccess() {
				StudentInfo stu=new StudentInfo();
				stu.setStudentIcon(bmobFile);
				stu.update(ModifyInfoActivity.this, personObjectId, new UpdateListener() {

					@Override
					public void onSuccess() {
						Toast.makeText(ModifyInfoActivity.this, "�ϴ��ɹ�", Toast.LENGTH_SHORT).show();
						clearPicPath();
						initViewInfo();
						Intent intent=new Intent(ModifyInfoActivity.this,BaseActivity.class);
						startActivity(intent);
						finish();
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						Toast.makeText(ModifyInfoActivity.this, "�ϴ�ʧ��", Toast.LENGTH_SHORT).show();
					}
				});
			}
			@Override
			public void onProgress(Integer value) {
				// ���ص��ϴ����ȣ��ٷֱȣ�
			}

			@Override
			public void onFailure(int code, String msg) {
				Log.d("Show","�����ϴ�����������ͼƬŶ��");
			}
		});
	}

	@Override
	public void getStudentObjectId(StudentInfo stuinfo) {
		personObjectId=stuinfo.getObjectId();
	}
	/**
	 * ���PicPath
	 */
	public void clearPicPath(){
		picPath="";
		BmobQuery.clearAllCachedResults(getApplicationContext());
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
