package com.jm.jmmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jm.jutils.R;
import com.jm.jutils_core.RootUtils;

public class RootActivity extends AppCompatActivity {



    Button bt_screenshot,bt_reboot;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_root);




        //root下截屏
        bt_screenshot=findViewById(R.id.bt_screenshot);

        bt_screenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RootUtils.shotScreen();
            }
        });


        //root系统重启
        bt_reboot=findViewById(R.id.bt_reboot);

        bt_reboot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RootUtils.reboot();
            }
        });





    }

    public static void showActivity(Context context){

        Intent intent=new Intent();
        intent.setClass(context, RootActivity.class);
        context.startActivity(intent);
    }
}
