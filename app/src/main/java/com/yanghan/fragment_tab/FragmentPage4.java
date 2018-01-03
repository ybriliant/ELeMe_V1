package com.yanghan.fragment_tab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.MainActivity;
import com.a1141705068qq.main.ggb.LogoutActivity;
import com.a1141705068qq.main.ggb.User_infoActivity;
import com.a1141705068qq.main.gson.User;
import com.a1141705068qq.main.gzcsearchtest.gzcsearchtest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class FragmentPage4 extends Fragment implements View.OnClickListener{
	private Button user_setting;
	private Button user_info;
	private Button user_note;
	private TextView user_name;
	private TextView user_phone;
	private ImageView face;
	private SharedPreferences upref;
	private SharedPreferences.Editor editor;
	private User user;
	private String name;
	private String phone;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.tab_fragment4, null);
		user=new User();
		user_setting=(Button) view.findViewById(R.id.user_setting);
		user_note=(Button)view.findViewById(R.id.user_note);
		user_info=(Button)view.findViewById(R.id.user_info);
		user_name=(TextView)view.findViewById(R.id.p4_user_name);
		user_phone=(TextView)view.findViewById(R.id.p4_user_phone);
		face=(ImageView)view.findViewById(R.id.p4_user_icon);
		upref=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
		name=upref.getString("user_name",null);
		phone=upref.getString("user_phone",null);
		if(name!=null)
			user_name.setText(name);
		if(phone!=null){
			user_phone.setText(phone.substring(0,3)+"****"+phone.substring(7,11));
			Glide.with(this).load("http://67.216.210.216/upload/"+upref.getString("user_id",null)+".jpg"+"?").diskCacheStrategy( DiskCacheStrategy.NONE ).skipMemoryCache( true ).into(face);
		}
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		user_info.setOnClickListener(this);
		user_setting.setOnClickListener(this);
	}

	@Override
	public void onClick(View v){
		Intent intent=null;
		switch (v.getId()){
			case R.id.user_setting:
				intent=new Intent(getActivity(), LogoutActivity.class);
				startActivity(intent);
				break;
			case R.id.user_info:
				intent=new Intent(getActivity(), User_infoActivity.class);
				startActivity(intent);
			case R.id.user_note:
			default:
				break;
		}
	}
}