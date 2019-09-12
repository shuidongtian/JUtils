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

import com.jm.jutils_core.NetworkUtil;
import com.jm.jutils.R;

public class NetInfoActivity extends AppCompatActivity {


    TextView tv_info;
    Button bt_open_wifi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_net_info);

        bt_open_wifi=findViewById(R.id.bt_open_wifi);


        bt_open_wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkUtil.openWifiSwitch(NetInfoActivity.this);
            }
        });




        tv_info=findViewById(R.id.tv_info);
        tv_info.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv_info.setText(
                "\n\n 是否联网:\n\t\t" + NetworkUtil.isConnected(this)
                        + "\n\n 网络类型:\n\t\t" + NetworkUtil.getNetworkType(this)
                        + "\n\n wifimac:\n\t\t" + NetworkUtil.getWifiMac(this)
                        + "\n\n 以太网mac:\n\t\t" + NetworkUtil.getMacAddrNew(1)
                        + "\n\n 本地ip:\n\t\t" + NetworkUtil.getLocalInetAddress().toString()


        );

    }

    public static void showActivity(Context context){

        Intent intent=new Intent();
        intent.setClass(context, NetInfoActivity.class);
        context.startActivity(intent);
    }
}
