package com.wenjie.app.Tanxun.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
/**
 * ͼƬ���湤����
 * @author dell
 *
 */
public class NetWorkImgeUtil {
	 private static RequestQueue queue;
	    private static NetWorkImgeUtil netWorkUtils = null;
	    private Context context;
	    private NetWorkImgeUtil(Context context) {
	        this.context = context;
	        queue = Volley.newRequestQueue(context);
	    }
	   
	    public static NetWorkImgeUtil getInstance(Context context) {
	        if (netWorkUtils == null) {
	            netWorkUtils = new NetWorkImgeUtil(context);
	        }
	        return netWorkUtils;
	    }
	    /**
	     * ����һ��ͼƬ�����ַ�ʽ:
	     * 1.ֱ������ͼƬ,�ڷ�����.
	     * 
	     *
	     */
	    public void imageRequest(String url, final ImageView img) {
	        ImageRequest request = new ImageRequest(url, new Listener<Bitmap>() {
	            @Override
	            public void onResponse(Bitmap bitmap) {
	                img.setImageBitmap(bitmap);
	            }
	        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
	           

				@Override
				public void onErrorResponse(VolleyError error) {
					
				}
	        });
	        queue.add(request);
	    }
	    
}
