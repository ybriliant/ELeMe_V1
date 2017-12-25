package com.yanghan.fragment_tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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

public class FragmentPage1 extends Fragment{
	private View view;
	private ListView listView=null;
	private  TextView tv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.tab_fragment1, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
        //sendRequest();
		listView=(ListView)view.findViewById(R.id.restaurant_list);
		final List<Map<String, Object>> list=getData();
		listView.setAdapter(new ShopAdapter(getActivity(),list));

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Map<String,Object> shop = list.get(position);
				Intent intent=new Intent(getActivity(),Shop_Activity.class);
				startActivity(intent);
				Toast.makeText(getContext(),"进入店铺",Toast.LENGTH_SHORT).show();
			}
		});

		tv=(TextView)view.findViewById(R.id.search_tv);
		tv.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),gzcsearchtest.class);
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
			map.put("ratingbar1",5);
			map.put("fuck",4.5f);
			map.put("arrive_time_restaurant", "14:30");
			map.put("distant_restaurant", "1km");
			map.put("abc", R.drawable.img5);
			list.add(map);
		}
		return list;
	}

	public void sendRequest(int id){
		String dishUrl="http://67.216.210.216/showrestaurant.php?id="+id;
		HttpUtil.sendOkHttpRequest(dishUrl, new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				final String responseText=response.body().string();
				List<com.a1141705068qq.main.gson.Restaurant> restaurants= Utility.handleRestaurantResponse(responseText);
			}
		});
	}



}
