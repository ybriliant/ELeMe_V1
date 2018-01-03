package com.yanghan.fragment_tab;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.a1141705068qq.main.flowlayout.CommomDialog;
import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.adapter.OrderlistAdapter;
import com.a1141705068qq.main.gson.Order;
import com.a1141705068qq.main.util.HttpUtil;
import com.a1141705068qq.main.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FragmentPage3 extends Fragment {
    private View view;
    private ListView listView;
    private SharedPreferences upref;
    private String user_id;
    private List<Order> orderList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_fragment3, null);
        if (orderList==null)
        {
            LinearLayout ly=(LinearLayout)view.findViewById(R.id.no_order);
            ly.setVisibility(View.VISIBLE);
        }
        listView = (ListView) view.findViewById(R.id.orderlistview);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                new CommomDialog(getContext(), R.style.cartdialog, "确认删除此订单？（不可恢复）", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            Toast.makeText(getActivity(), "点击确定", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }

                })
                        .setTitle("提示").show();
                Toast.makeText(getActivity(), "Long", Toast.LENGTH_SHORT).show();
                return false;
            }

        });
       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Click", Toast.LENGTH_SHORT).show();
            }
        });*/
        return view;

    }

    public List<HashMap<String, Object>> getData() {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 4; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("rename", "k");
            map.put("time", "2017");
            map.put("price", "￥11");
            list.add(map);
        }
        return list;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        upref=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        user_id=upref.getString("user_id",null);
        sendRequest();
    }

    public void sendRequest(){
        String url="http://67.216.210.216/userorders.php?id="+user_id;
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"加载订单信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                orderList= Utility.handleOrderResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        OrderlistAdapter adapter=new OrderlistAdapter(getActivity(),R.layout.item_order,orderList);
                        listView.setAdapter(adapter);
                    }
                });

            }
        });
    }

}