package com.yanghan.fragment_tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.dxd.FoodView;

public class FragmentPage2 extends Fragment{
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		view=inflater.inflate(R.layout.tab_fragment2, null);
		refresh();
		return view;
	}
	public void refresh(){
		FoodView commend1=(FoodView)getActivity().findViewById(R.id.food_commend1);
		FoodView commend2=(FoodView)getActivity().findViewById(R.id.food_commend2);
		FoodView commend3=(FoodView)getActivity().findViewById(R.id.food_commend3);
		commend1.setMassage(R.drawable.hbg,"至尊七虾堡",12);
		commend2.setMassage(R.drawable.chicken,"鸡腿",20);
		commend3.setMassage(R.drawable.fries,"薯条",8);
	}
}
