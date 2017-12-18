package com.yanghan.fragment_tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.Shop_Activity;

public class FragmentPage1 extends Fragment{
	private View view;
	private Button aaaa;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.tab_fragment1, null);
		aaaa=(Button)view.findViewById(R.id.button1);
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		aaaa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Toast.makeText(getContext(),"aaaaaaa",Toast.LENGTH_LONG).show();*/
				Intent intent=new Intent(getActivity(),Shop_Activity.class);
				startActivity(intent);
			}
		});
	}
}
