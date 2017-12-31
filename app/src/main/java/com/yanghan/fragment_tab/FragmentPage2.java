package com.yanghan.fragment_tab;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.Shop_Activity;
import com.a1141705068qq.main.dxd.FoodView;
import com.a1141705068qq.main.gson.Dish;
import com.a1141705068qq.main.util.HttpUtil;
import com.a1141705068qq.main.util.Utility;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FragmentPage2 extends Fragment{
	private View view;

	private List<Dish> mdishes;

	private List<Dish> ndishes;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		view=inflater.inflate(R.layout.tab_fragment2, null);
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		Random random=new Random();
		int id=random.nextInt(3)+2;
		sendRequest(id,1);
		if(id>0&&id<5)
			id++;
		sendRequest(id,2);
	}

	public void sendRequest(int id,final int tag){
		String dishUrl="http://67.216.210.216/dishes.php?id="+id;
		HttpUtil.sendOkHttpRequest(dishUrl, new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getActivity(),"获取菜品数据失败",Toast.LENGTH_SHORT).show();
					}
				});
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				final String responseText=response.body().string();
				if(tag==1) {
					mdishes= Utility.handleDishResponse(responseText);
					refresh1();
					refresh3();
				}
				if(tag==2){
					ndishes=Utility.handleDishResponse(responseText);
					refresh2();
					refresh4();
				}
			}
		});
	}

	public void refresh1(){
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run(){
				FoodView commend1=(FoodView)view.findViewById(R.id.food_commend1_1);
				FoodView commend2=(FoodView)view.findViewById(R.id.food_commend1_2);
				FoodView commend3=(FoodView)view.findViewById(R.id.food_commend1_3);
				commend1.setMassage(mdishes.get(0).getDis_picture(),mdishes.get(0).getDis_name(),String.valueOf(mdishes.get(0).getDis_price()));
				commend2.setMassage(mdishes.get(1).getDis_picture(),mdishes.get(1).getDis_name(),String.valueOf(mdishes.get(1).getDis_price()));
				commend3.setMassage(mdishes.get(2).getDis_picture(),mdishes.get(2).getDis_name(),String.valueOf(mdishes.get(2).getDis_price()));
			}
		});
	}

	public void refresh2(){
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run(){
				FoodView commend1=(FoodView)view.findViewById(R.id.food_commend2_1);
				FoodView commend2=(FoodView)view.findViewById(R.id.food_commend2_2);
				FoodView commend3=(FoodView)view.findViewById(R.id.food_commend2_3);
				commend1.setMassage(ndishes.get(2).getDis_picture(),ndishes.get(2).getDis_name(),String.valueOf(ndishes.get(2).getDis_price()));
				commend2.setMassage(ndishes.get(3).getDis_picture(),ndishes.get(3).getDis_name(),String.valueOf(ndishes.get(3).getDis_price()));
				commend3.setMassage(ndishes.get(1).getDis_picture(),ndishes.get(1).getDis_name(),String.valueOf(ndishes.get(1).getDis_price()));
			}
		});
	}

	public void refresh3(){
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run(){
				FoodView commend1=(FoodView)view.findViewById(R.id.food_commend3_1);
				FoodView commend2=(FoodView)view.findViewById(R.id.food_commend3_2);
				FoodView commend3=(FoodView)view.findViewById(R.id.food_commend3_3);
				commend1.setMassage(mdishes.get(3).getDis_picture(),mdishes.get(3).getDis_name(),String.valueOf(mdishes.get(3).getDis_price()));
				commend2.setMassage(mdishes.get(0).getDis_picture(),mdishes.get(0).getDis_name(),String.valueOf(mdishes.get(0).getDis_price()));
				commend3.setMassage(mdishes.get(1).getDis_picture(),mdishes.get(1).getDis_name(),String.valueOf(mdishes.get(1).getDis_price()));
			}
		});
	}

	public void refresh4(){
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run(){
				FoodView commend1=(FoodView)view.findViewById(R.id.food_commend4_1);
				FoodView commend2=(FoodView)view.findViewById(R.id.food_commend4_2);
				FoodView commend3=(FoodView)view.findViewById(R.id.food_commend4_3);
				commend1.setMassage(ndishes.get(3).getDis_picture(),ndishes.get(3).getDis_name(),String.valueOf(ndishes.get(3).getDis_price()));
				commend2.setMassage(ndishes.get(2).getDis_picture(),ndishes.get(1).getDis_name(),String.valueOf(ndishes.get(1).getDis_price()));
				commend3.setMassage(ndishes.get(0).getDis_picture(),ndishes.get(0).getDis_name(),String.valueOf(ndishes.get(0).getDis_price()));
			}
		});
	}
}
