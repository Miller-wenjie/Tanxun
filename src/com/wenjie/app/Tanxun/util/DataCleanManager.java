package com.wenjie.app.Tanxun.util;

import java.io.File;
import java.math.BigDecimal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

/**
 * ������������
 * @author dell
 *
 */
@SuppressLint("SdCardPath") public class DataCleanManager {
	/**
	 * �������
	 * @param context
	 */
	 public static void clearAllCache(Context context) {
	        deleteDir(context.getCacheDir());
	        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {  
	            deleteDir(context.getCacheDir());
	        }  
	    }
	   
	   private static boolean deleteDir(File dir) {
		   boolean success = true;
	        if (dir != null && dir.isDirectory() ) {
	            String[] children = dir.list();
	            for (int i = 0; i < children.length; i++) {
	            	//�����bmob�ļ�
	            	if(!children[i].equals("bmob")){
	            		success = deleteDir(new File(dir, children[i]));
	                }else{
	                	success=false;
	                }
	                if (!success) {
	                    return false;
	                }
	            }
	        }
	        return dir.delete();
	    }
    /**
     * ��ȡ�����С
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {  
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }  
        return getFormatSize(cacheSize);
    }
    public static long getFolderSize(File file) throws Exception {  
        long size = 0;  
        try {  
        	//fileList�õ����ǵ�ǰĿ¼�µ��ļ�/�ļ��У�·������������ǰĿ¼��
            File[] fileList = file.listFiles();  
            	for (int i = 0; i < fileList.length; i++) { 
            			// ������滹���ļ�  
            			if (fileList[i].isDirectory()) {
            				//�жϵ�ǰĿ¼�Ƿ�Ϊbmob,������ͷ���ļ��Ĵ�С
            				if(!fileList[i].getName().equals("bmob")){
            					size = size + getFolderSize(fileList[i]);  
            				}else{
            					size=size+0;
            				}
            			} else {  
            				size = size + fileList[i].length();  
            			}  
            		
                }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return size;  
    }  
    /** 
     * ��ʽ����λ 
     * @param size 
     * @return 
     */ 
    public static String getFormatSize(double size) {  
        double kiloByte = size / 1024;  
        if (kiloByte < 1) {  
            return "0KB";
        }  
   
        double megaByte = kiloByte / 1024;  
        if (megaByte < 1) {  
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));  
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)  
                    .toPlainString() + "KB";  
        }  
   
        double gigaByte = megaByte / 1024;  
        if (gigaByte < 1) {  
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));  
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)  
                    .toPlainString() + "MB";  
        }  
   
        double teraBytes = gigaByte / 1024;  
        if (teraBytes < 1) {  
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));  
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)  
                    .toPlainString() + "GB";  
        }  
        BigDecimal result4 = new BigDecimal(teraBytes);  
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()  
                + "TB";  
    }  
       
       
}
