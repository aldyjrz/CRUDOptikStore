package com.aries.pi;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import com.aries.pi.utils.SQLiteDBFavHelper;

import java.util.LinkedList;

/**
 * @author daiyu
 */

public class AppController extends Application {


    public String id;
    public String nama_barang;
    public Double harga_barang;
    public String ukuran_lensa;
    public String lensa;

    public String nama_cust;
    public String telf_cust;
    public String alamat_cust;
    public String tanggal;


//    public long locatingPeriod;
//    public boolean displayNotification;

    public int currentThemeId = R.style.AppTheme;

    public SQLiteDBFavHelper dbHelper;
    private AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        dbHelper = new SQLiteDBFavHelper(this);

    }
    public static AppController obtainApp(Context context) {
        return (AppController) context.getApplicationContext();
    }


    /**
     * 当前应用运行的Activity， 主要是为了让应用能得到当前用户界面的Activity。
     * 而且能退出应用时全部销毁。
     */
    private LinkedList<Activity> activities = new LinkedList<Activity>();

    /**
     * 当Activity 被create的时候放进来
     *
     * @param activity 被创建的Activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);

    }

    /**
     * 当某个Activity destroy的时候
     *
     * @param activity 被销毁的Activity
     */
    public void removeActivity(Activity activity) {
        activities.remove(activity);

    }


    /**
     * Test an activity is  whether in the activities stack.
     *
     * @param cls Activity class
     * @return true if contains this activity
     */
    public boolean testActivityInStack(Class cls) {
        for (Activity activity : activities) {
            if (activity != null && activity.getClass() == cls) {
                return true;
            }
        }
        return false;
    }

    public Activity getActivity(Class clz) {
        for (Activity activity : activities) {
            if (activity != null && activity.getClass() == clz) {
                return activity;
            }
        }
        return null;
    }




    /**
     * @deprecated 发布时一定要关闭
     */



    /**
     * 退出应用
     */
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        Process.killProcess(Process.myPid());
    }

    /**
     * 获取到Application类
     * http://stackoverflow.com/questions/3826905/singletons-vs-application-context-in-android
     *
     * @param context 本应用的一个context
     * @return CurrentApp对象
     */

}