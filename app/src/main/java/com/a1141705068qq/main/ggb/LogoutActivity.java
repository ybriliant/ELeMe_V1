package com.a1141705068qq.main.ggb;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.LoginActivity;
import com.a1141705068qq.main.MainActivity;

public class LogoutActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        Button logout=(Button)findViewById(R.id.logout);
        Button arrow_back=(Button)findViewById(R.id.arrow_back);
        TextView text1=(TextView)findViewById(R.id.text1);
        TextView text2=(TextView)findViewById(R.id.text2);
        LinearLayout securuty=(LinearLayout)findViewById(R.id.security);
        LinearLayout tongyong=(LinearLayout)findViewById(R.id.tongyong);
        LinearLayout guanyu=(LinearLayout)findViewById(R.id.guanyu);
        guanyu.setOnClickListener(this);
        securuty.setOnClickListener(this);
        tongyong.setOnClickListener(this);
        logout.setOnClickListener(this);
        arrow_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.logout:
                intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.arrow_back:
                finish();
                break;
            case R.id.security:
                intent=new Intent(this,User_infoActivity.class);
                startActivity(intent);
                break;
            case R.id.tongyong:
                intent=new Intent(this,User_infoActivity.class);
                startActivity(intent);
                break;
            case R.id.guanyu:
                new AlertDialog.Builder(this)
                        .setTitle("EleMe1.0")
                        .setMessage("Founders:郭子琛，张杰，杨涵，杜晓东，李轩昂")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create().show();
            default:
                break;
        }
    }
}
