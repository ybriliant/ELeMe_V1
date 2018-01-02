package com.yanghan.fragment_tab;

import android.app.Dialog;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentPage3 extends Fragment {
    private View view;
    private ListView listView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_fragment3, null);
        final List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 4; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("rename", "k");
            map.put("time", "2017");
            map.put("price", "￥11");
            list.add(map);
        }
        final OrderlistAdapter adapter = new OrderlistAdapter(getContext(), (ArrayList<HashMap<String, Object>>) list, R.layout.item_order, new String[]{"rename", "time", "price"}
                , new int[]{R.id.item_resname, R.id.item_time, R.id.setPrice});
        if (list==null)
        {
            LinearLayout ly=(LinearLayout)view.findViewById(R.id.no_order);
            ly.setVisibility(View.VISIBLE);
        }
        listView = (ListView) view.findViewById(R.id.orderlistview);
        listView.setAdapter(adapter);
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
                adapter.notifyDataSetChanged();
                return false;
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Click", Toast.LENGTH_SHORT).show();
            }
        });
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


}