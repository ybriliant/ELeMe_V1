package com.yanghan.fragment_tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.MainActivity;
import com.a1141705068qq.main.ggb.LogoutActivity;
import com.a1141705068qq.main.ggb.User_infoActivity;
import com.a1141705068qq.main.gzcsearchtest.gzcsearchtest;

public class FragmentPage4 extends Fragment implements View.OnClickListener{
	private Button user_setting;
	private Button user_info;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.tab_fragment4, null);
		user_setting=(Button) view.findViewById(R.id.user_setting);
		user_info=(Button)view.findViewById(R.id.user_info);
		user_info.setOnClickListener(this);
		user_setting.setOnClickListener(this);
		return view;
	}

	/*@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		user_setting.setOnClickListener(this);
		arrow_back.setOnClickListener(this);
	}
	*/

	@Override
	public void onClick(View v){
		switch (v.getId()){
			case R.id.user_setting:
				Intent intent1=new Intent(getActivity(), LogoutActivity.class);
				startActivity(intent1);
				break;
			case R.id.user_info:
				Intent intent2=new Intent(getActivity(), User_infoActivity.class);
				startActivity(intent2);
			default:
				break;
		}
	}
}