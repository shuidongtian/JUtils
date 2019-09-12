package com.jm.jmmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jm.jutils_core.DisplayUtils;
import com.jm.jutils.R;

public class DisplayInfoActivity extends AppCompatActivity {


    TextView tv_info;
    EditText ed_test;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //设置全屏
        DisplayUtils.noTitleAndFullScreen(this);
        //唤醒屏幕
        DisplayUtils.wakeScreen(this);

        setContentView(R.layout.layout_display);

        //隐藏软键盘
        DisplayUtils.hideSoftInput(this);
        //设置亮度，6.0以前设置系统亮度，6.0及以后设置app亮度,范围0~100
        DisplayUtils.setScreenBrightness(this,100);


      tv_info=findViewById(R.id.tv_info);

        tv_info.setMovementMethod(ScrollingMovementMethod.getInstance());


        //范围0~255
        tv_info.setText("当前系统亮度："+DisplayUtils.getScreenBrightness(this)+"");



    }









    public static void showActivity(Context context){

        Intent intent=new Intent();
        intent.setClass(context, DisplayInfoActivity.class);
        context.startActivity(intent);
    }



}
