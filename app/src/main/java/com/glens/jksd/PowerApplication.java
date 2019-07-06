package com.glens.jksd;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.glens.jksd.utils.ActivityManager;
import com.glens.jksd.utils.imagePick.model.ImageItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.scwang.smartrefresh.header.waveswipe.DropBounceInterpolator;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 登录有效时间30分钟
 */
public class PowerApplication extends Application {

    // 下拉和上拉的头、底部
    //static 代码段可以防止内存泄露
    static {

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.stop_true_item_text, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public static final String TAG = PowerApplication.class.getSimpleName();
    public static ArrayList<Activity> activityList;
    public static Activity topActivity;
    private static String token = "/";
    public static Application mApplication;

    private static HashMap<String, ImageItem> selectedImgs;

    private static List<ImageItem> imageList;

    private static String mTowerName;
    private static String mTowerNumber;

    /**
     * 从图库每次选择的图片
     */
    private static List<ImageItem> mDataList;

    /**
     * 当前操作的图片列表
     */
    private static List<ImageItem> currentDataList;


    private static Context context; //全局context

    public PowerApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(this);

        ImageLoader.getInstance().init(config);     //UniversalImageLoader初始化
        initAPP();
        application = this;
        context = getApplicationContext();
    }

    /**
     * 单例模式
     */
    private static PowerApplication application;

    public static PowerApplication getInstance() {
        return application;
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        System.runFinalization();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void initAPP() {
        mApplication = this;
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {


            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (activityList == null) {
                    activityList = new ArrayList<>();
                }
                activityList.add(activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                topActivity = activity;
                ActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activityList.remove(activity);
            }
        });
    }

    public static String getmTowerName() {
        return mTowerName;
    }

    public static void setmTowerName(String mTowerName) {
        PowerApplication.mTowerName = mTowerName;
    }

    public static String getmTowerNumber() {
        return mTowerNumber;
    }

    public static void setmTowerNumber(String mTowerNumber) {
        PowerApplication.mTowerNumber = mTowerNumber;
    }

    public static List<ImageItem> getmDataList() {
        return mDataList;
    }

    public static void setmDataList(List<ImageItem> mDataList) {
        PowerApplication.mDataList = mDataList;
    }

    public static List<ImageItem> getCurrentDataList() {
        return currentDataList;
    }

    public static void setCurrentDataList(List<ImageItem> currentDataList) {
        PowerApplication.currentDataList = currentDataList;
    }

    public static HashMap<String, ImageItem> getSelectedImgs() {
        return selectedImgs;
    }

    public static void setSelectedImgs(HashMap<String, ImageItem> selectedImgs) {
        PowerApplication.selectedImgs = selectedImgs;
    }

    public static List<ImageItem> getImageList() {
        return imageList;
    }

    public static void setImageList(List<ImageItem> imageList) {
        PowerApplication.imageList = imageList;
    }


    public static String getToken() {
        return token;
    }

    /**
     * 登录时获取设置全局token
     * 将token设置到拦截器请求头里面
     *
     * @param token 标识
     */
    public static void setToken(String token) {
        PowerApplication.token = token;
    }


    /**
     * 获取当前显示的activity
     *
     * @return activity
     */
    public String getRunningActivity() {
        android.app.ActivityManager activityManager = (android.app.ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = null;
        if (activityManager != null)
            runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getShortClassName();
        return runningActivity;
    }

    private static class SingletonHolder {
        private static final PowerApplication mSingleton = new PowerApplication();

    }

    public static PowerApplication getPowerApplication() {
        return SingletonHolder.mSingleton;
    }

    public static String getStringResource(int resourceId) {
        return SingletonHolder.mSingleton.getResources().getString(resourceId);
    }

    public static DisplayImageOptions imageLoaderOptions = new DisplayImageOptions.Builder()//
            .showImageOnLoading(R.drawable.ic_default_image)         //设置图片在下载期间显示的图片
            .showImageForEmptyUri(R.drawable.ic_default_image)       //设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.drawable.ic_default_image)            //设置图片加载/解码过程中错误时候显示的图片
            .cacheInMemory(true)                                //设置下载的图片是否缓存在内存中
            .cacheOnDisk(true)                                  //设置下载的图片是否缓存在SD卡中
            .build();
}
