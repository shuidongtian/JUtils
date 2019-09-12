package com.jm.jmmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.jm.jutils.R;
import com.jm.jutils_core.DeviceInfoUtils;

public class DeviceInfoActivity extends AppCompatActivity {


    TextView tv_info;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_device_info);
        tv_info=findViewById(R.id.tv_info);
        tv_info.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv_info.setText(DeviceInfoUtils.getDeviceAllInfo(this));

    }

    public static void showActivity(Context context){

        Intent intent=new Intent();
        intent.setClass(context, DeviceInfoActivity.class);
        context.startActivity(intent);
    }
}
