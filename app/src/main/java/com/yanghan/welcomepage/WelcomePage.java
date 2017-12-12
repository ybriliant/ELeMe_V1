package com.yanghan.welcomepage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.a1141705068qq.main.MainActivity;
import com.a1141705068qq.class_one.R;
import com.yanghan.appguide.AppGuide;


public class WelcomePage extends Activity {
	//使用Handler实现延时或者定时器也可以
	private Handler handler;
	private boolean isFirst=true;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_page);

		//将是否是第一次的状态用SharedPreferences保存和取出
		final SharedPreferences sp= getSharedPreferences("FirstLaunch",MODE_PRIVATE);
		isFirst=sp.getBoolean("FirstValue",true);
		handler=new Handler();
		
		handler.postDelayed(new Runnable() {		
			@Override
			public void run() {
				if(isFirst){
					//修改状态，并跳转到引导页
					sp.edit().putBoolean("FirstValue", false).commit();
					intent=new Intent(WelcomePage.this, AppGuide.class);
					startActivity(intent);
				}else{
					//不是第一次进入该APP,则跳转到MainActivity
					intent=new Intent(WelcomePage.this, MainActivity.class);
					startActivity(intent);
				}
				finish();
			}
		}, 1000);
	}
}
