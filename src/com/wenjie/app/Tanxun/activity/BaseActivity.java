package com.wenjie.app.Tanxun.activity;

import com.wenjie.app.Tanxun.R;

import android.app.Activity;
import android.os.Bundle;
/**
 * Tab�л����ƽ���
 * @author dell
 *
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
	}
	
}
