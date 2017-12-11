package com.a1141705068qq.main;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.a1141705068qq.class_one.R;
import com.yanghan.fragment_tab.FragmentPage1;
import com.yanghan.fragment_tab.FragmentPage2;
import com.yanghan.fragment_tab.FragmentPage3;
import com.yanghan.fragment_tab.FragmentPage4;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{

    //定义对象
    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面\图片、文字
    private Class fragmentArray[] = {FragmentPage1.class, FragmentPage2.class, FragmentPage3.class,
            FragmentPage4.class};

    private int mImageViewArray[] = {R.drawable.home_seletor,R.drawable.info_seletor,
            R.drawable.my_selector, R.drawable.setting_selector};

    private String mTextviewArray[] = {"外卖", "发现", "订单", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.home_container);
        //设置取消分割线
        mTabHost.getTabWidget().setDividerDrawable(null);


        //得到fragment的个数
        int count = fragmentArray.length;
        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);

        }

    }//end of initView

    /**
     * 给Tab按钮设置图标和文字
     */
    public View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_tab);
        imageView.setImageResource(mImageViewArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.tv_tab);
        textView.setText(mTextviewArray[index]);
        return view;
    }

}
