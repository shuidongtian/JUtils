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

import com.jm.jutils.R;
import com.jm.jutils_core.SDCardUtils;
import com.jm.jutils_core.StorageUtils;

public class StorageInfoActivity extends AppCompatActivity {


    TextView tv_info;
    Button bt_write,bt_copy_assets,bt_copy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_storage_info);





        //写入指定文件
        // 若文件或文件夹不存在，则创建
        bt_write=findViewById(R.id.bt_write);

        bt_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StorageUtils.write2file(SDCardUtils.getSDPath()+"/jm.txt","StorageInfoActivity");


            }
        });


        //从指定目录复制文件到另一目录
        //复制之前创建的jm.txt到cjm.txt
        bt_copy=findViewById(R.id.bt_copy);

        bt_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StorageUtils.copyFile(SDCardUtils.getSDPath()+"/jm.txt", SDCardUtils.getSDPath()+"/cjm.txt");


            }
        });


        //从assets复制文件，复制serial.apk到根目录
        bt_copy_assets=findViewById(R.id.bt_copy_assets);

        bt_copy_assets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StorageUtils.copyFileAsset(StorageInfoActivity.this,"serial.apk", SDCardUtils.getSDPath()+"/");
            }
        });




        tv_info=findViewById(R.id.tv_info);
        tv_info.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv_info.setText(
                "\n\n 是否有内置SD卡:\n\t\t" + SDCardUtils.isSDCardMount()

                        + "\n\n RAM 信息:\n\t\t" + SDCardUtils.getRAMInfo(this)

                        + "\n\n 内部存储信息\n\t\t" + SDCardUtils.getStorageInfo(this, 0)

                        + "\n\n SD卡 信息:\n\t\t" + SDCardUtils.getStorageInfo(this, 1)

                        + "\n\n SD卡 路径:\n\t\t" + SDCardUtils.getSDPath()


        );

    }

    public static void showActivity(Context context){

        Intent intent=new Intent();
        intent.setClass(context, StorageInfoActivity.class);
        context.startActivity(intent);
    }
}
