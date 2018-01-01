package com.a1141705068qq.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by asd on 2017/12/27.
 */

public class OrderlistAdapter extends SimpleAdapter {
    Context context;
    public OrderlistAdapter(Context context, List<? extends Map<String, ?>>  list, int resource, String[] from, int[] to)
    {
        super(context, list, resource, from, to);
        this.context = context;
    }


   public View getView(final int position, final View convertView, ViewGroup parent) {
      final View view = super.getView(position, convertView, parent);
       ImageView ibn;
       TextView resname,confirm,order;
       ibn=(ImageView)view.findViewById(R.id.item_respicture);
      resname=(TextView)view.findViewById(R.id.item_resname);
       confirm=(TextView) view.findViewById(R.id.confirm_delivery);
       order=(TextView)view.findViewById(R.id.order_again);
       order.setTag(position);
       order.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               Toast.makeText(context, "1"+position,LENGTH_SHORT).show();
           }
       });
       confirm.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               Toast.makeText(context, "1",LENGTH_SHORT).show();
           }
       });
       resname.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               Toast.makeText(context, "2",LENGTH_SHORT).show();
           }
       });
       ibn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               Toast.makeText(context, "3",LENGTH_SHORT).show();
           }
       });


       return view;
   }
}
