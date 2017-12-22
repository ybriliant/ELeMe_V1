package com.yanghan.fragment_tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.MainActivity;
import com.a1141705068qq.main.Shop_Activity;
import com.a1141705068qq.main.gson.Restaurant;
import com.a1141705068qq.main.gzcsearchtest.gzcsearchtest;
import com.a1141705068qq.main.util.HttpUtil;
import com.a1141705068qq.main.util.Utility;
import com.yanghan.fragment_tab.fragmentOneNeed.ShopAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.media.CamcorderProfile.get;

public class FragmentPage1 extends Fragment implements View.OnClickListener{
	private View view;
	private ListView listView=null;
	private Restaurant res1;
	private Restaurant res2;
	private TextView search_tv;
	private List<Restaurant> restaurants;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.tab_fragment1, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		listView=(ListView)view.findViewById(R.id.restaurant_list);
		search_tv=(TextView)view.findViewById(R.id.search);
		search_tv.setOnClickListener(this);
		sendRequest(1);
	}

	@Override
	public void onClick(View v){
		switch (v.getId()){
			case R.id.search:
				Intent intent=new Intent(getActivity(),gzcsearchtest.class);
				startActivity(intent);
				break;
			default:
				break;
		}
	}


	public List<Map<String, Object>> getData(Restaurant res){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		for (int i=0;i<4;i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("image_restaurant", res.getRes_picture());
			map.put("name_restaurant", res.getRes_name());
			map.put("start_send", "14:00");
			map.put("send_fee", "介绍："+res.getRes_note());
			map.put("arrive_time_restaurant", res.getRes_mark()+"分");
			map.put("ratingbar2",(float)res.getRes_mark());
			map.put("ratingbar1",5);
			map.put("distant_restaurant", "1km");
			map.put("abc", R.drawable.img5);
			list.add(map);
		}
		return list;
	}

	public void sendRequest(final int id){
		String resUrl="http://67.216.210.216/showrestaurant.php?id="+id;
		HttpUtil.sendOkHttpRequest(resUrl, new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getActivity(),"获取店家信息失败",Toast.LENGTH_SHORT).show();
					}
				});
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				final String responseText=response.body().string();
				final Restaurant res=Utility.handleRestaurantResponse(responseText);
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						final List<Map<String, Object>> list=getData(res);
						listView.setAdapter(new ShopAdapter(getActivity(),list));
						listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
								Map<String,Object> shop = list.get(position);
								int res_id=Integer.parseInt(res.getRes_id());
								Intent intent=new Intent(getActivity(),Shop_Activity.class);
								intent.putExtra("res_id",res_id);
								startActivity(intent);
								Toast.makeText(getContext(),"进入店铺",Toast.LENGTH_SHORT).show();
							}
						});
					}
				});

			}
		});
	}


}
