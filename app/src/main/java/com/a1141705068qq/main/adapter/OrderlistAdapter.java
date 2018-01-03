package com.a1141705068qq.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.MainActivity;
import com.a1141705068qq.main.Shop_Activity;
import com.a1141705068qq.main.gson.Order;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by asd on 2017/12/27.
 */

public class OrderlistAdapter extends ArrayAdapter<Order>  {
    Context context;
    private int resourceId;
    private String dateNowStr;
    private Button order_confirm;
    private TextView order_finish;
    public OrderlistAdapter(Context context,int textViewResourceId,List<Order> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
        Date d = new Date();
        System.out.println(d);
        this.context = context;
    }


   public View getView(final int position,View convertView, ViewGroup parent) {
        Order order=getItem(position);
        View view=LayoutInflater.from(context).inflate(resourceId,parent,false);
       ImageView res_picture=(ImageView)view.findViewById(R.id.res_picture);
       TextView res_name=(TextView)view.findViewById(R.id.res_name);
       TextView item_time=(TextView)view.findViewById(R.id.item_time);
       TextView item_dish=(TextView)view.findViewById(R.id.item_dish);
       TextView item_price=(TextView)view.findViewById(R.id.item_price);
       order_confirm=(Button)view.findViewById(R.id.order_confirm);
       Glide.with(context).load(order.getOrder_pic()).into(res_picture);
       res_name.setText("  -"+order.getRes_name()+"-");
       dateNowStr = randomDate("20170101", "20171231");
       item_time.setText(dateNowStr);
       item_dish.setText(order.getDis_name());
       item_price.setText("￥"+order.getOrder_sum());
       order_confirm.setOnClickListener((new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               Random random=new Random();
               int res_id=random.nextInt(5)+1;
               Intent intent=new Intent(context,Shop_Activity.class);
               intent.putExtra("res_id",String.valueOf(res_id));
               context.startActivity(intent);
           }
       }));

       return view;
   }


    private static String randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime()) ;
            return format.format(new Date(date)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

}
