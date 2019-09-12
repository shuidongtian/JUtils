package com.jm.jutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Locale;


public class DeviceInfoUtils {

    /**
     * 获取设备宽度（px）
     *
     */
    public static int getDeviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取设备高度（px）
     */
    public static int getDeviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取设备的唯一标识， 需要 “android.permission.READ_Phone_STATE”权限
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String deviceId = tm.getDeviceId();
        if (deviceId == null) {
            return "UnKnown";
        } else {
            return deviceId;
        }
    }

    /**
     * 获取厂商名
     * **/
    public static String getDeviceManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 获取产品名
     * **/
    public static String getDeviceProduct() {
        return android.os.Build.PRODUCT;
    }

    /**
     * 获取手机品牌
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     */
    public static String getDeviceModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机主板名
     */
    public static String getDeviceBoard() {
        return android.os.Build.BOARD;
    }

    /**
     * 设备名
     * **/
    public static String getDeviceDevice() {
        return android.os.Build.DEVICE;
    }

    /**
     *
     *
     * fingerprit 信息
     * **/
    public static String getDeviceFubgerprint() {
        return android.os.Build.FINGERPRINT;
    }

    /**
     * 硬件名
     *
     * **/
    public static String getDeviceHardware() {
        return android.os.Build.HARDWARE;
    }

    /**
     * 主机
     *
     * **/
    public static String getDeviceHost() {
        return android.os.Build.HOST;
    }

    /**
     *
     * 显示ID
     * **/
    public static String getDeviceDisplay() {
        return android.os.Build.DISPLAY;
    }

    /**
     * ID
     *
     * **/
    public static String getDeviceId() {
        return android.os.Build.ID;
    }

    /**
     * 获取手机用户名
     *
     * **/
    public static String getDeviceUser() {
        return android.os.Build.USER;
    }

    /**
     * 获取手机 硬件序列号
     * **/
    public static String getDeviceSerial() {
        return android.os.Build.SERIAL;
    }

    /**
     * 获取手机Android 系统SDK
     *
     * @return
     */
    public static int getDeviceSDK() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本
     *
     * @return
     */
    public static String getDeviceAndroidVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取当前手机系统语言。
     */
    public static String getDeviceDefaultLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     */
    public static String getDeviceSupportLanguage() {
        Log.e("wangjie", "Local:" + Locale.GERMAN);
        Log.e("wangjie", "Local:" + Locale.ENGLISH);
        Log.e("wangjie", "Local:" + Locale.US);
        Log.e("wangjie", "Local:" + Locale.CHINESE);
        Log.e("wangjie", "Local:" + Locale.TAIWAN);
        Log.e("wangjie", "Local:" + Locale.FRANCE);
        Log.e("wangjie", "Local:" + Locale.FRENCH);
        Log.e("wangjie", "Local:" + Locale.GERMANY);
        Log.e("wangjie", "Local:" + Locale.ITALIAN);
        Log.e("wangjie", "Local:" + Locale.JAPAN);
        Log.e("wangjie", "Local:" + Locale.JAPANESE);
        return Locale.getAvailableLocales().toString();
    }

    public static String getDeviceAllInfo(Context context) {

        return "\n\n IMEI:\n\t\t" + getIMEI(context)

                + "\n\n 设备宽度:\n\t\t" + getDeviceWidth(context)

                + "\n\n 设备高度:\n\t\t" + getDeviceHeight(context)

                + "\n\n 是否有内置SD卡:\n\t\t" + SDCardUtils.isSDCardMount()

                + "\n\n RAM 信息:\n\t\t" + SDCardUtils.getRAMInfo(context)

                + "\n\n 内部存储信息\n\t\t" + SDCardUtils.getStorageInfo(context, 0)

                + "\n\n SD卡 信息:\n\t\t" + SDCardUtils.getStorageInfo(context, 1)

                + "\n\n 是否联网:\n\t\t" + NetworkUtil.isConnected(context)

                + "\n\n 网络类型:\n\t\t" + NetworkUtil.getNetworkType(context)

                + "\n\n 系统默认语言:\n\t\t" + getDeviceDefaultLanguage()

                + "\n\n 硬件序列号(设备名):\n\t\t" + android.os.Build.SERIAL

                + "\n\n 手机型号:\n\t\t" + android.os.Build.MODEL

                + "\n\n 生产厂商:\n\t\t" + android.os.Build.MANUFACTURER

                + "\n\n 手机Fingerprint标识:\n\t\t" + android.os.Build.FINGERPRINT

                + "\n\n Android 版本:\n\t\t" + android.os.Build.VERSION.RELEASE

                + "\n\n Android SDK版本:\n\t\t" + android.os.Build.VERSION.SDK_INT

                + "\n\n 发布时间:\n\t\t" + Utils.timeStamp2Date(android.os.Build.TIME)

                + "\n\n 版本类型:\n\t\t" + android.os.Build.TYPE

                + "\n\n 用户名:\n\t\t" + android.os.Build.USER

                + "\n\n 产品名:\n\t\t" + android.os.Build.PRODUCT

                + "\n\n ID:\n\t\t" + android.os.Build.ID

                + "\n\n 显示ID:\n\t\t" + android.os.Build.DISPLAY

                + "\n\n 硬件名:\n\t\t" + android.os.Build.HARDWARE

                + "\n\n 产品名:\n\t\t" + android.os.Build.DEVICE

                + "\n\n Bootloader:\n\t\t" + android.os.Build.BOOTLOADER

                + "\n\n 主板名:\n\t\t" + android.os.Build.BOARD

                + "\n\n CodeName:\n\t\t" + android.os.Build.VERSION.CODENAME
                + "\n\n 语言支持:\n\t\t" + getDeviceSupportLanguage();

    }
}