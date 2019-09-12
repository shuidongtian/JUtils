package com.jm.jmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.List;


public class InstallReceiver extends BroadcastReceiver {
    private final String TAG = this.getClass().getSimpleName();


    @Override
    public void onReceive(Context context, Intent intent) {
        PackageManager pm = context.getPackageManager();

        if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getData().getSchemeSpecificPart();

             Toast.makeText(context, "安装成功" + packageName, Toast.LENGTH_LONG).show();

        } else if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_REPLACED)) {
            String packageName = intent.getData().getSchemeSpecificPart();

            //  Toast.makeText(context, "替换成功" + packageName, Toast.LENGTH_LONG).show();

        } else if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_REMOVED)) {
            String packageName = intent.getData().getSchemeSpecificPart();

            Toast.makeText(context, "卸载成功" + packageName, Toast.LENGTH_LONG).show();


        }
    }


    private boolean isUseExist(Context cxt, String pkgName) {
        PackageManager pm = cxt.getPackageManager();
        List<PackageInfo> packageInfoList = pm.getInstalledPackages(0);
        for (int i = 0; i < packageInfoList.size(); i++) {
            if (packageInfoList.get(i).packageName.equalsIgnoreCase(pkgName)) {
                return true;
            }
        }
        return false;
    }

}
