package com.wenjie.app.Tanxun.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * ѹ��ͼƬ������
 * @author dell
 *
 */
public class CompressImage {
	/**
	 * ѹ��ͼƬ
	 * @param bitmap
	 * @return
	 */
	public static Bitmap compressImage(Bitmap bitmap){
		//�õ�ͼƬԭʼ�ĸ߿�
		int rawHeight = bitmap.getHeight();
		int rawWidth = bitmap.getWidth();
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
		Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, rawWidth,
				rawHeight, matrix, true);
		//���մ�ͼ�Ķ���
		if(!bitmap.isRecycled())
		{
			bitmap.recycle();
		}     
		return newBitmap;
		
	}
}
