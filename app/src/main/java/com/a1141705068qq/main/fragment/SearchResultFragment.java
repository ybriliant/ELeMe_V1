package com.a1141705068qq.main.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1141705068qq.class_one.R;

/**
 * Created by Shinobu on 2017/12/31.
 */

public class SearchResultFragment extends android.support.v4.app.Fragment {
    private View view;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        view=inflater.inflate(R.layout.search_result,container,false);
        return view;
    }
}
