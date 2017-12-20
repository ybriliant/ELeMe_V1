package com.yanghan.fragment_tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.Shop_Activity;
import com.yanghan.fragment_tab.fragmentOneNeed.Shop;
import com.yanghan.fragment_tab.fragmentOneNeed.ShopAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentPage1 extends Fragment{
	private View view;
	private ImageView aaaa;
	private ListView listView=null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.tab_fragment1, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);

		listView=(ListView)view.findViewById(R.id.restaurant_list);
		final List<Map<String, Object>> list=getData();
		listView.setAdapter(new ShopAdapter(getActivity(),list));

		aaaa=(ImageView)view.findViewById(R.id.iv_icon1);
		aaaa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent=new Intent(getActivity(),Shop_Activity.class);
				startActivity(intent);
			}
		});
	}


	public List<Map<String, Object>> getData(){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("image_restaurant", R.drawable.img1);
			map.put("name_restaurant", "鸡排"+(i+1));
			map.put("start_send", "14:00");
			map.put("send_fee", "10块");
			map.put("arrive_time_restaurant", "14:30");
			map.put("distant_restaurant", "1km");
			map.put("abc", R.drawable.img5);
			list.add(map);
		}
		return list;
	}
}
