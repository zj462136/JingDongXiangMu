package com.bwie.myapplication.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.bwie.myapplication.model.db.AddrDao;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class JunApplication extends Application {

    private static int myTid;
    private static Handler handler;
    private static Context context;

    //实际开发中 这些平台的id,key,secret全都是要去对应的开放平台申请
    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myTid = Process.myTid();
        handler = new Handler();
        context = getApplicationContext();
        //初始化友盟
        UMShareAPI.get(this);
        //开启debug
        Config.DEBUG = true;
        //初始化地址的数据库
        new AddrDao(this).initAddrDao();
        //初始化zxing库
        ZXingLibrary.initDisplayOpinion(this);
        Fresco.initialize(this);
    }

    //获取主线程的id
    public static int getMainThreadId() {
        return myTid;
    }

    //获取handler
    public static Handler getAppHanler() {
        return handler;
    }

    public static Context getAppContext() {
        return context;
    }
}
