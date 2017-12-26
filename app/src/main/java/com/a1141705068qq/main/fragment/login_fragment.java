package com.a1141705068qq.main.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.MainActivity;
import com.a1141705068qq.main.gson.User;
import com.a1141705068qq.main.util.Utility;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/12/10.
 */

public class login_fragment extends Fragment{
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private SharedPreferences pref;
    private SharedPreferences upref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    private String isSucess;
    private User user;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_login,container,false);
        accountEdit=(EditText)view.findViewById(R.id.account);
        passwordEdit=(EditText)view.findViewById(R.id.password);
        login=(Button)view.findViewById(R.id.login);
        rememberPass=(CheckBox)view.findViewById(R.id.remenber_pass);
        isSucess="{\"success\":0}";
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //pref= PreferenceManager.getDefaultSharedPreferences(getActivity());
        pref=getActivity().getSharedPreferences("key",MODE_PRIVATE);
        upref=getActivity().getSharedPreferences("user",MODE_PRIVATE);
        boolean isRemember=pref.getBoolean("remember_password",false);
        if(isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
           /* if(!password.equals("")){
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
            */
        }

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                sendRequestWithOkHttp(account,password);
            }
        });
    }

    private void sendRequestWithOkHttp(final String account, final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder()
                            .add("username",account)
                            .add("password",password)
                            .build();
                    Request request=new Request.Builder()
                            .url("http://67.216.210.216/login.php")
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    if(!responseData.equals(isSucess)) {
                        sucess(account,password,responseData);
                    }else{
                        Looper.prepare();
                        Toast.makeText(getContext(), "account or password is invalid", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                        //Toast的使用基于主线程的looper进行信息传递，此处为子线程需手动添加looper
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sucess(String account,String password,String responseData){
        editor=pref.edit();
        if(rememberPass.isChecked()){
            editor.putBoolean("remember_password",true);
            editor.putString("account",account);
            editor.putString("password",password);
        }else{
            editor.clear();
        }
        editor.apply();
        user=Utility.handleUserResponse(responseData);
        editor=upref.edit();
        editor.putString("user_id",user.getUser_id());
        editor.putString("user_name",user.getUser_name());
        editor.putString("user_phone",user.getUser_phone());
        editor.putString("user_picture",user.getUser_picture());
        editor.putString("user_location",user.getUser_location());
        editor.apply();
        Intent intent=new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}