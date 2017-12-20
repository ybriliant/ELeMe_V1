package com.yanghan.fragment_tab.fragmentOneNeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.a1141705068qq.class_one.R;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/12/18.
 */

public class ShopAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public ShopAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应take_out_support_list.xml中的控件
     */
    public final class Shop{
        public ImageView image_restaurant;
        public TextView name_restaurant;
        //public RatingBar ratingbar1;
        public TextView start_send;
        public TextView send_fee;
        public TextView arrive_time_restaurant;
        public TextView distant_restaurant;
        public TextView discount_restaurant;
        public ImageView abc;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Shop shop=null;
        //final int numStars = zujian.ratingbar1.getNumStars();
        if(convertView==null){
            shop =new Shop();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.take_out_support_item, parent,false);
            shop.image_restaurant=(ImageView) convertView.findViewById(R.id.image_restaurant);
            //zujian.ratingbar1=(RatingBar) convertView.findViewById(R.id.ratingbar1) ;
            shop.name_restaurant=(TextView)convertView.findViewById(R.id.name_restaurant) ;
            shop.start_send=(TextView)convertView.findViewById(R.id.start_send) ;
            shop.send_fee=(TextView)convertView.findViewById(R.id.send_fee) ;
            shop.arrive_time_restaurant=(TextView)convertView.findViewById(R.id.arrive_time_restaurant) ;
            shop.distant_restaurant=(TextView)convertView.findViewById(R.id.distant_restaurant) ;
            shop.discount_restaurant=(TextView)convertView.findViewById(R.id.discount_restaurant) ;
            shop.abc=(ImageView) convertView.findViewById(R.id.abc);
            convertView.setTag(shop);
        }else{
            shop=(Shop)convertView.getTag();
        }
        //绑定数据
        shop.image_restaurant.setBackgroundResource((Integer)data.get(position).get("image_restaurant"));
        shop.name_restaurant.setText((String)data.get(position).get("name_restaurant"));
        //zujian.ratingbar1.setNumStars(numStars);
        shop.start_send.setText((String)data.get(position).get("start_send"));
        shop.send_fee.setText((String)data.get(position).get("send_fee"));
        shop.arrive_time_restaurant.setText((String)data.get(position).get("arrive_time_restaurant"));
        shop.distant_restaurant.setText((String)data.get(position).get("distant_restaurant"));
        shop.discount_restaurant.setText((String)data.get(position).get("discount_restaurant"));
        shop.abc.setBackgroundResource((Integer)data.get(position).get("abc"));
        return convertView;
    }

}
