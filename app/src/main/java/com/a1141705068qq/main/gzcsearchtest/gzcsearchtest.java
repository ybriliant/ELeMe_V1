package com.a1141705068qq.main.gzcsearchtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.dxd.SearchResult;
import com.a1141705068qq.main.gson.Restaurant;
import com.a1141705068qq.main.util.HttpUtil;
import com.a1141705068qq.main.util.Utility;
import com.czp.searchmlist.mSearchLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

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
                sendRequest(str);
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

    protected void sendRequest(String str){
        String resUrl="http://67.216.210.216/searchforrestaurant.php";
        RequestBody requestBody=new FormBody.Builder()
                .add("keywords",str)
                .build();
        HttpUtil.postOkHttpRequest(resUrl,requestBody,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(gzcsearchtest.this,"搜索失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                Log.i("gzcsearchtest","response Data:"+responseText);
                Restaurant res= Utility.handleRestaurantResponse(responseText);
                Intent intent=new Intent(gzcsearchtest.this, SearchResult.class);
                intent.putExtra("res_data",res);
                startActivity(intent);
            }
        });
    }
}
