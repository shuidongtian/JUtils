package com.jm.jmmanager;

import android.Manifest;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jm.jutils.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {

    RecyclerView recycle_view;

    NormalAdapter recycleAdapter;
    List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();

        init();

    }


    public void requestPermissions() {

        final RxPermissions rxPermissions = new RxPermissions(MainActivity.this); // where this is an Activity or Fragment instance




        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.WRITE_SETTINGS,
                        Manifest.permission.READ_PHONE_STATE
                ).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted) {
                    // All requested permissions are granted
                } else {
                    // At least one permission is denied
                }
            }
        });


    }


    private void init() {
        recycle_view = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//设置布局管理器
        recycle_view.setLayoutManager(layoutManager);

        //设置分隔线
        recycle_view.addItemDecoration( new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//设置Adapter
        data=new ArrayList<>();
        data.add("设备信息相关");
        data.add("显示相关");
        data.add("网络信息相关");
        data.add("安装相关");
        data.add("存储相关");
        data.add("声音相关");
        data.add("root权限相关");

       recycleAdapter= new NormalAdapter(data);
       recycle_view.setAdapter(recycleAdapter);

    }



    // ① 创建Adapter
    public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH>{


        //② 创建ViewHolder
        public  class VH extends RecyclerView.ViewHolder{
            public final TextView title;
            public VH(View v) {
                super(v);
                title = (TextView) v.findViewById(R.id.tv_info);
            }
        }

        private List<String> mDatas;
        public NormalAdapter(List<String> data) {
            this.mDatas = data;
        }

        //③ 在Adapter中实现3个方法
        @Override
        public void onBindViewHolder(VH holder, final int position) {
            holder.title.setText(mDatas.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,mDatas.get(position), Toast.LENGTH_SHORT).show();
                    if(mDatas.get(position).contains("设备信息相关")){
                        DeviceInfoActivity.showActivity(MainActivity.this);
                    }else if(mDatas.get(position).contains("显示相关")){
                        DisplayInfoActivity.showActivity(MainActivity.this);
                    }else if(mDatas.get(position).contains("网络信息相关")){
                        NetInfoActivity.showActivity(MainActivity.this);
                    }else if(mDatas.get(position).contains("声音相关")){
                        VoiceActivity.showActivity(MainActivity.this);
                    }else if(mDatas.get(position).contains("存储相关")){
                        StorageInfoActivity.showActivity(MainActivity.this);
                    }else if(mDatas.get(position).contains("安装相关")){
                        InstallActivity.showActivity(MainActivity.this);
                    }else if(mDatas.get(position).contains("root权限相关")){
                        RootActivity.showActivity(MainActivity.this);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            //LayoutInflater.from指定写法
            //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            View v = getLayoutInflater().inflate(R.layout.item, parent, false);
            return new VH(v);
        }
    }




}
