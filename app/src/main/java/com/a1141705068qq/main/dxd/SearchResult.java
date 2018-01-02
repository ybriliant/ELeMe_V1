package com.a1141705068qq.main.dxd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.MainActivity;
import com.a1141705068qq.main.Shop_Activity;
import com.a1141705068qq.main.gzcsearchtest.gzcsearchtest;
import com.yanghan.fragment_tab.fragmentOneNeed.ShopAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResult extends AppCompatActivity {
    private List<Map<String, Object>> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        initView();
    }

    private void initView(){
        Button backButton=(Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchResult.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button exitButton=(Button)findViewById(R.id.edit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchResult.this,gzcsearchtest.class);
                startActivity(intent);
            }
        });
        initDish();
        ShopAdapter adapter=new ShopAdapter(getApplicationContext(),result);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    //搜索的结果 放入result中 待修改
    private void initDish(){
        result=new ArrayList<Map<String,Object>>();
        for(int i=0;i<3;i++){
            float a= (float) 3.6;
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image_restaurant",R.drawable.hbg);
            map.put("name_restaurant","麦加美");
            map.put("start_send", "14:00");
            map.put("send_fee", "介绍：" + "麦加美");
            map.put("arrive_time_restaurant", "45分");
            map.put("ratingbar2", a);
            map.put("ratingbar1", 5);
            map.put("distant_restaurant", "1km");
            map.put("abc", R.drawable.img5);
            result.add(map);
        }
    }
}
