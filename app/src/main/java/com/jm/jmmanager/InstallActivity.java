package com.jm.jmmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jm.jutils.InstallUtils;
import com.jm.jutils.R;
import com.jm.jutils.SDCardUtils;
import com.jm.jutils.StorageUtils;

public class InstallActivity extends AppCompatActivity {


    TextView tv_info;
    Button bt_install,bt_uninstall;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_install);


        //测试安装使用的apk为


        //安装apk
        bt_install=findViewById(R.id.bt_install);

        bt_install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageUtils.copyFileAsset(InstallActivity.this,"serial.apk", SDCardUtils.getSDPath()+"/");

                InstallUtils.installApk(InstallActivity.this, SDCardUtils.getSDPath()+"/serial.apk");

            }
        });


        //卸载apk
        bt_uninstall=findViewById(R.id.bt_uninstall);

        bt_uninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InstallUtils.uninstallApk(InstallActivity.this,"com.bjw.ComAssistant");

            }
        });






        tv_info=findViewById(R.id.tv_info);
        tv_info.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv_info.setText("");

    }

    public static void showActivity(Context context){

        Intent intent=new Intent();
        intent.setClass(context, InstallActivity.class);
        context.startActivity(intent);
    }
}
