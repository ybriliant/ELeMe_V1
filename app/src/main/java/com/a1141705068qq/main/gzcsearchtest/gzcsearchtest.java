package com.a1141705068qq.main.gzcsearchtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.dxd.SearchResult;
import com.czp.searchmlist.mSearchLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by governormars on 2017/12/20.
 */


public class gzcsearchtest extends AppCompatActivity {
    private mSearchLayout msearchLy;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gzcsearchtest);
        msearchLy = (mSearchLayout)findViewById(R.id.msearchlayout);
        initData();
        Button button_jump=(Button)findViewById(R.id.jump);
        button_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(gzcsearchtest.this, SearchResult.class);
                startActivity(intent);
            }
        });
    }

    protected void initData() {
        String shareData = "澳洲美食,长沙美食,韩国料理,日本料理,舌尖上的中国,意大利餐,山西菜";
        List<String> skills = Arrays.asList(shareData.split(","));

        String shareHotData ="粤菜,浙菜,苏菜";
        List<String> skillHots = Arrays.asList(shareHotData.split(","));

        msearchLy.initData(skills, skillHots, new mSearchLayout.setSearchCallBackListener() {
            @Override
            public void Search(String str) {
                //POST请求进行数据库查询
            }
            @Override
            public void Back() {
                finish();
            }

            @Override
            public void ClearOldData() {
                //清除历史搜索记录  更新记录原始数据
            }
            @Override
            public void SaveOldData(ArrayList<String> AlloldDataList) {
                //保存所有的搜索记录
            }
        });
    }
}
