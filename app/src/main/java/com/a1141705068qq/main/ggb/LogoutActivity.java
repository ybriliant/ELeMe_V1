package com.a1141705068qq.main.ggb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

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
        logout.setOnClickListener(this);
        arrow_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.logout:
                Intent intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.arrow_back:
                finish();
                break;
        }
    }
}
