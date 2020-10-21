package com.letian.jutils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.letian.jutils.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    public ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);


        initView();
        initData();
    }



    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


}