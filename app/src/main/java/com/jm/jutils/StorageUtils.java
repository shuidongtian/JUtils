package com.jm.jutils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StorageUtils {



    /**
     *@Description:写入指定文件，如果文件或文件夹不存在，则创建
     *@Params:
     */
    public static void write2file(String filePath, String text) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter filerWriter = new FileWriter(file, true);
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(text);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    /**
     *@Description:从指定目录复制文件到另一目录
     *@Params:
     */
    public static void copyFile(String fromFile, String toFile) {
        try {
            InputStream fosFrom = new FileInputStream(fromFile);
            OutputStream foSto = new FileOutputStream(toFile);
            byte[] bt = new byte[1024];
            int c;
            while ((c = fosFrom.read(bt)) > 0) {
                foSto.write(bt, 0, c);
            }
            fosFrom.close();
            foSto.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     *@Description:从assets复制文件
     *@Params:
     */

    public static boolean copyFileAsset(Context context, String fileName, String desPath) {
        try {
            AssetManager assetManager = context.getAssets();
            File file = new File(desPath + fileName);
            if (file.exists())
                file.delete();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = assetManager.open(fileName);
            byte[] buffer = new byte[1024];
            while (inputStream.available() > 0) {
                int len = inputStream.read(buffer, 0, buffer.length);
                fileOutputStream.write(buffer, 0, len);
            }
            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
