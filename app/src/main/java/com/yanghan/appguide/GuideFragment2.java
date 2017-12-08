package com.yanghan.appguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1141705068qq.class_one.R;

public class GuideFragment2 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.guide_fragment2, container, false);
	}
}
