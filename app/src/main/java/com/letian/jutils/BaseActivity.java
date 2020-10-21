package com.letian.jutils;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * Activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {



    private String TAG=BaseActivity.class.getSimpleName();
    BaseFragment currentFragment;


    protected abstract void initView();
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏

        requestPermissions();
    }

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(BaseFragment currentFragment) {
        this.currentFragment = currentFragment;
        Log.e(TAG,"当前fragment:"+currentFragment.getClass().getSimpleName());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            hideNavigationBar();//隐藏导航栏
        }
    }

      public void hideNavigationBar() {
                int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN; // hide status bar
                 if( android.os.Build.VERSION.SDK_INT >= 19 ){
                         uiFlags |= 0x00001000;    //SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide navigation bars - compatibility: building API level is lower thatn 19, use magic number directly for higher API target level
                     } else {
                         uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                    }
                getWindow().getDecorView().setSystemUiVisibility(uiFlags);
      }


    public void requestPermissions() {

        final RxPermissions rxPermissions = new RxPermissions(BaseActivity.this); // where this is an Activity or Fragment instance


        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.RECEIVE_BOOT_COMPLETED,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE

                ).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted) {
                    // All requested permissions are granted
                    Log.e(TAG,"权限通过");
                    //初始化工作
//                    init();

                } else {
                    // At least one permission is denied
                    Log.e(TAG,"有权限未通过");
                    Toast.makeText(BaseActivity.this, "有权限未通过", Toast.LENGTH_SHORT).show();
//                    finish();
                }
            }
        });

    }


}
