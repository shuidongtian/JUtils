package com.jm.jutils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayUtils {











    /**
     * 全屏,在setContentView前调用
     */
    public static void noTitleAndFullScreen(AppCompatActivity activity) {

        //去除标题栏
        activity.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        if (activity.getSupportActionBar() != null){
            activity.getSupportActionBar().hide();
        }
        //全屏展示
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }




    /**
     * 唤醒屏幕
     */
    public static void wakeScreen(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        PowerManager pm = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, activity.getPackageName());
        wakeLock.acquire(2000);

    }


    /**
     *@Description:隐藏软键盘
     *
     */
    public static void hideSoftInput(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }



    /**
     *@Description:获取亮度
     *
     */

    public static  int getScreenBrightness(Activity context) {
        ContentResolver contentResolver = context.getContentResolver();
        int defVal = 125;
        return Settings.System.getInt(contentResolver,
                Settings.System.SCREEN_BRIGHTNESS, defVal);
    }





    /**
     *@Description:设置亮度
     *
     */

    public static void setScreenBrightness(Activity context, int light) {
        //设置系统亮度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            changeAppBrightness(context, light);
        } else {
            settingBrightness(context, light);
        }
    }

    // 根据亮度值修改当前window亮度
    private static void changeAppBrightness(Activity activity, int light) {
        Log.e("changeAppBrightness", "changeAppBrightness: ");
        activity.getCallingActivity();
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        float volumef1 = light / 100f;
        float volumef2 = 255 * volumef1;
        light = (int) volumef2;
        if (light > 255) {
            light = 255;
        } else if (light <= 0) {
            light = 0;
        }
        lp.screenBrightness = light;
        window.setAttributes(lp);
    }

    private static void settingBrightness(Context context, int light) {
        ContentResolver contentResolver = context.getContentResolver();
        float volumef1 = light / 100f;
        float volumef2 = 255 * volumef1;
        light = (int) volumef2;
        if (light > 255) {
            light = 255;
        } else if (light <= 0) {
            light = 0;
        }
        saveBrightness(contentResolver, light);
    }
    private static void saveBrightness(ContentResolver resolver, int brightness) {
        //改变系统的亮度值(申请权限失败)
        //这里需要权限android.permission.WRITE_SETTINGS
        //设置为手动调节模式
        try {
            Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            //保存到系统中
            Uri uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
            Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
            resolver.notifyChange(uri, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
