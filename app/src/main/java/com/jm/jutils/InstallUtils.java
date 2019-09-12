package com.jm.jutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;



import java.io.File;

public class InstallUtils {


    public static void installApk(Context context, String apkPath) {




        File apkFile = new File(apkPath);
        if (!apkFile.exists()) {
            return;
        }
        try {

            // 通过Intent安装APK文件
            Intent intent = new Intent(Intent.ACTION_VIEW);


            //判断是否是AndroidN以及更高的版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", apkFile);
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }


            //i.setDataAndType(Uri.parse("file://" + apkFile.toString()), "application/vnd.android.package-archive");
           context. startActivity(intent);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public static void uninstallApk(Context context, String packageName){
        Uri uri = Uri.fromParts("package", packageName, null);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        context.startActivity(intent);
    }

}
