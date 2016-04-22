package com.wenjie.app.Tanxun.util;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * �������ӹ�����
 * @author dell
 *
 */
public class NetWorkTUtil {
	/**
	 * ���������Ƿ���Ч
	 * @return
	 */
	public static boolean isNetAvailable(Activity activity){
		ConnectivityManager connectionManager=(ConnectivityManager)
				activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo=connectionManager.getActiveNetworkInfo();
		if(networkInfo!=null && networkInfo.isAvailable()){
			return true;
		}else{
			return false;
		}
	}
}
