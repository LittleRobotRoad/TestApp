package lol.niconico.dev.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

import lol.niconico.dev.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ZhangQianqian on 2016/10/11  09:46.
 * email 415692689@qq.com
 */

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = BaseApplication.class.getSimpleName();

    public static Context mContext;
    private static Map<String, Activity> hmActivity = new HashMap<>();


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        ExceptionHandler exHandler = ExceptionHandler.getInstance();  //异常日志打印
        exHandler.init(mContext);
        registerActivityLifecycleCallbacks(this); //注册activity生命周期回调
        Utils.init(this); //工具类初始化
    }


    /**
     * ActivityLifecycleCallbacks  --start---
     * 监听每个Activity的生命周期
     */
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        //取toString作为hashmap的key
        BaseApplication.addDestoryActivity(activity, activity.toString());
//        Log.e(activity.getClass().getSimpleName(), "onActivityCreated: " + activity.toString());
    }

    @Override
    public void onActivityStarted(Activity activity) {
//        Log.e(activity.getClass().getSimpleName(), "onActivityStarted: ");
    }

    @Override
    public void onActivityResumed(Activity activity) {
//        Log.e(activity.getClass().getSimpleName(), "onActivityResumed: ");
    }

    @Override
    public void onActivityPaused(Activity activity) {
//        Log.e(activity.getClass().getSimpleName(), "onActivityPaused: ");
    }

    @Override
    public void onActivityStopped(Activity activity) {
//        Log.e(activity.getClass().getSimpleName(), "onActivityStopped: ");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//        Log.e(activity.getClass().getSimpleName(), "onActivitySaveInstanceState: ");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        BaseApplication.removeActivity(activity.toString());
//        Log.e(activity.getClass().getSimpleName(), "onActivityDestroyed: " + activity.toString());
    }
    /** ActivityLifecycleCallbacks  --end---*/

    /***
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */

    public static void addDestoryActivity(Activity activity, String activityName) {
        hmActivity.put(activityName, activity);
    }

    /**
     * 销毁指定Activity
     */
    public static void destroyActivity(String activityName) {

        Set<String> keySet = hmActivity.keySet();
        for (String key : keySet) {
            if (key.contains(activityName)) {
                final Activity activity = hmActivity.get(key);
                hmActivity.remove(key);
                activity.finish();
                break;
            }
        }
    }

    /**
     * 销毁类名多个Activity
     */
    public static void destoryActivitys(String activityName) {

        Set<String> keySet = hmActivity.keySet();
        List<String> acName = new ArrayList<>();
        for (String key : keySet) {
            if (key.contains(activityName)) {
                acName.add(key);
            }
        }

        for (String name : acName) {
            final Activity activity = hmActivity.get(name);
            hmActivity.remove(name);
            activity.finish();
        }
        acName.clear();
    }

    /**
     * 去除指定Activity的引用
     */
    public static void removeActivity(String activityName) {
        Set<String> keySet = hmActivity.keySet();
        for (String key : keySet) {
            if (key.contains(activityName)) {
                hmActivity.remove(key);
                break;
            }
        }
    }

    /**
     * 销毁全部activity
     */
    public static void destroyAllActivity() {
        final Iterator<String> iterator = hmActivity.keySet().iterator();
        while (iterator.hasNext()) {
            Activity activity = hmActivity.get(iterator.next());
            iterator.remove();
            activity.finish();
        }
        hmActivity.clear();
//        android.os.Process.killProcess(android.os.Process.myPid());   //自杀进程
    }

    /**
     * 判断当前应用是否处于前台
     */
    public static boolean isRunningForeground() {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        return currentPackageName != null && currentPackageName.equals(mContext.getPackageName());
    }
}
