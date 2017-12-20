package com.yanghan.appguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;


import com.a1141705068qq.main.MainActivity;
import com.a1141705068qq.class_one.R;

import java.util.ArrayList;
import java.util.List;


public class AppGuide extends FragmentActivity implements OnClickListener,OnPageChangeListener {
	private Button btnStart;
	private ViewPager viewPager;
	private GuideFragmentAdapter guideFragmentAdapter;
	private List<Fragment> fragmentList;
	private ImageView[] imageViews=new ImageView[4];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_guide);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

		//initView
		btnStart=(Button) findViewById(R.id.btnStart);
		viewPager=(ViewPager) findViewById(R.id.viewPager);
		fragmentList=new ArrayList<Fragment>();

		//这么说吧，类似List<String> data--------这是准备数据源
		fragmentList.add(new GuideFragment1());
		fragmentList.add(new GuideFragment2());
		fragmentList.add(new GuideFragment3());
		fragmentList.add(new GuideFragment4());
		//-----------------
		imageViews[0]= (ImageView) findViewById(R.id.guidedot_1);
		imageViews[1]= (ImageView) findViewById(R.id.guidedot_2);
		imageViews[2]= (ImageView) findViewById(R.id.guidedot_3);
		imageViews[3]= (ImageView) findViewById(R.id.guidedot_4);

		//创建适配器
		guideFragmentAdapter=new GuideFragmentAdapter(getSupportFragmentManager(), fragmentList);
		//视图加载适配器
		viewPager.setAdapter(guideFragmentAdapter);
		//事件
		viewPager.setOnPageChangeListener(this);
		btnStart.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			//开始体验的按钮
		case R.id.btnStart:
			Intent intent=new Intent(this, MainActivity.class);
			startActivity(intent);
			this.finish();
			break;

		default:
			break;
		}
		
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
			
	}

	//滑动到最后一页，显示按钮
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		//如果滑动到最后一页，则把“开始体验”显示出来
		if(position==fragmentList.size()-1){
			btnStart.setVisibility(View.VISIBLE);
		}else {
			btnStart.setVisibility(View.GONE);
		}
		for(int i=0;i<4;i++){
			if(i==position){
				imageViews[i].setSelected(true);
			}else {
				imageViews[i].setSelected(false);
			}
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		
	}
}
