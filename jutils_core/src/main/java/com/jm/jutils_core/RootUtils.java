package com.jm.jutils_core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class RootUtils {




    /**
     * 截屏 此种方式需要root权限
     */
    public static boolean shotScreen() {

        String[] commands = new String[1];
        commands[0] = "/system/bin/screencap -p ";
        boolean isRoot = true;

        // ----------------------------------------

        if (commands == null || commands.length == 0)
            return false;
        Process process = null;
        DataOutputStream os;
        try {
            process = Runtime.getRuntime().exec(isRoot ? "su" : "sh");
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }
                os.write(command.getBytes());
                os.writeBytes("\n");
                os.flush();
            }
            os.writeBytes("exit\n");
            os.flush();

            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            // 缩放系数为4 1920*1080缩放后是480*270
            newOpts.inSampleSize = 4;
            Bitmap bitmap = BitmapFactory.decodeStream(process.getInputStream(), null, newOpts);
            if (bitmap != null) {
                saveBitmap(bitmap, SDCardUtils.getSDPath()+"/screen.png");
                if (!bitmap.isRecycled()) {
                    bitmap.recycle();
                    bitmap = null;
                }
                return true;
            }


            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (process != null) {
                    process.exitValue();
                }
            } catch (IllegalThreadStateException e) {
                process.destroy();
            }
        }
        return false;
    }




    public static void saveBitmap(Bitmap bitmap, String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void reboot(){

                do_exec("reboot");
    }



    public static void do_exec(String paramString) {
        Process process = null;

        BufferedReader input = null;
        PrintWriter output = null;

        try {
            process = Runtime.getRuntime().exec("su");


            String str = paramString + "\n" + "exit\n";
            process.getOutputStream().write(str.getBytes());

            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));
            output.flush();
            String line;

            while ((line = input.readLine()) != null) {


            }


            Log.d("MyProtectService", "James DBG:>>>> do_exec cmd:"+paramString);
            Log.d("MyProtectService", "James DBG:>>>> do_exec cmd:waitForStart");
            if (process.waitFor() != 0)
            {

                Log.i("MyProtectService","waitFor!=0");

            }else{
                Log.i("MyProtectService","waitFor=0");
            }

            Log.d("MyProtectService", "James DBG:>>>> do_exec cmd:waitForEnd");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("MyProtectService", "James DBG:>>>>do_exec Exception:"+e.toString());
        }finally {

            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (output != null) {
                output.close();
            }
            if (process != null) {
                process.destroy();
            }

        }


    }

}
