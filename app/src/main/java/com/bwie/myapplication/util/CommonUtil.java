package com.bwie.myapplication.util;

import android.content.SharedPreferences;

import com.bwie.myapplication.application.JunApplication;

public class CommonUtil {

    private static SharedPreferences sharedPreferences;
    private static final String TAG="Jun";
    /**
     * 自己写的运行在主线程的方法
     * 如果是主线程,执行任务,否则使用handler发送到主线程中去执行
     */
    public static void runOnUIThread(Runnable runnable){
        //先判断当前属于子线程还是主线程
        if (android.os.Process.myTid()== JunApplication.getMainThreadId()){
            runnable.run();
        }else {
            //子线程
            JunApplication.getAppHanler().post(runnable);
        }
    }
//    sp存入字符串类型的值
    public static void saveString(String flag,String str){
        if (sharedPreferences==null){
            sharedPreferences = JunApplication.getAppContext().getSharedPreferences(TAG, JunApplication.getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(flag,str);
        edit.commit();
    }
    public static String getString(String flag){
        if (sharedPreferences==null){
            sharedPreferences = JunApplication.getAppContext().getSharedPreferences(TAG, JunApplication.getAppContext().MODE_PRIVATE);
        }
       return sharedPreferences.getString(flag,"");
    }
    public static boolean getBoolean(String tag){
        if (sharedPreferences==null){
            sharedPreferences = JunApplication.getAppContext().getSharedPreferences(TAG, JunApplication.getAppContext().MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(tag,false);
    }
    public static void putBoolean(String tag,boolean content){
        if (sharedPreferences==null){
            sharedPreferences = JunApplication.getAppContext().getSharedPreferences(TAG, JunApplication.getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(tag,content);
        edit.commit();
    }
//    清除sp数据
    public static void clearSp(String tag){
        if (sharedPreferences==null){
            sharedPreferences = JunApplication.getAppContext().getSharedPreferences(TAG, JunApplication.getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(tag);
        edit.commit();
    }
}
