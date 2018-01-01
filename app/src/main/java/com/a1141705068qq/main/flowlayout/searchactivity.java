package com.a1141705068qq.main.flowlayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.flowlayout.FlowLayout;
import com.a1141705068qq.main.flowlayout.TagAdapter;
import com.a1141705068qq.main.flowlayout.TagFlowLayout;
import com.a1141705068qq.main.fragment.SearchLayoutFragment;
import com.a1141705068qq.main.fragment.SearchResultFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import com.a1141705068qq.main.fragment.SearchResultFragment;

public class searchactivity extends AppCompatActivity {

    private TagFlowLayout mFlowLayout;
    private EditText editText;
    private Button button;
    private List<String> strings;

    //热门搜索内容
    private List<String> strings2 = new ArrayList<String>() {{
        add("string1");
        add("string2");
        //some other add() code......
        add("stringN");
    }};


    //布局管理器
    private LayoutInflater mInflater;
    //流式布局的子布局
    private TextView tv;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mFlowLayout.setAdapter(new TagAdapter<String>(strings) {
                        @Override
                        public View getView(FlowLayout parent, int position, String s) {
                            tv = (TextView) mInflater.inflate(R.layout.tv,
                                    mFlowLayout, false);
                            tv.setText(s);
                            return tv;
                        }
                    });
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        mInflater = LayoutInflater.from(this);

        //初始化热门搜索内容
        mFlowLayout = (TagFlowLayout)findViewById(R.id.flowlayout_hot);
        mFlowLayout = (TagFlowLayout) findViewById(R.id.flowlayout_hot);
        mFlowLayout.setAdapter(new TagAdapter<String>(strings2) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                tv = (TextView) mInflater.inflate(R.layout.tv,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });

        ImageButton ib = (ImageButton) findViewById(R.id.clear_history_bn);
        ib.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                strings = new ArrayList<String>();
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.search_history_ly);
                handler.sendEmptyMessageDelayed(1, 0);
                rl.setVisibility(View.GONE);
            }

        });
        mFlowLayout = (TagFlowLayout) findViewById(R.id.flowlayout_history);
        editText = (ClearableEditText) findViewById(R.id.edt);
        button = (Button) findViewById(R.id.btn);
        strings = new ArrayList<>();
        replaceFragment(new SearchLayoutFragment());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.search_history_tv);
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.search_history_ly);
                String aa = editText.getText().toString().trim();
                if (!strings.contains(aa) && !aa.isEmpty())//查重
                {
                    rl.setVisibility(View.VISIBLE);
                    strings.add(aa);
                    editText.setText("");//
                }

                if (strings.size() > 10)//记录最多显示10条，超过十条时删除最前
                    strings.remove(0);

                //通知handler更新UI


                handler.sendEmptyMessageDelayed(1, 0);
                replaceFragment(new SearchResultFragment());
            }
        });

        //流式布局tag的点击方法
        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(searchactivity.this, tv.getText(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();
    }
}
