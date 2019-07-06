package com.glens.jksd.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.glens.jksd.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


/**
 * Created by dell on 2016/10/13.
 */

public class CommonUtils {
    private static String TAG = CommonUtils.class.getSimpleName();
    private static int statusBarHeight;

    /**
     * @param context
     * @param dpValue
     * @return int
     * @author zr
     * @date 2013-2-6
     * @method dip2px 方法
     * <p>方法说明:将dp转成相应密度的px</p>
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 将时间格式化成年月日
     *
     * @param time 时间
     * @return 年月日格式
     * @author zhangkx
     */
    public static String formatYYMMDDHHMMSS(long time) {
        Date date = new Date(time);
        SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        return formatDay.format(date);
    }

    /**
     * 将时间格式化成年月日
     *
     * @param date 时间
     * @return 年月日格式
     * @author zhangkx
     */
    public static String formatYYMMDDHHMMSS(Date date) {
        SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        return formatDay.format(date);
    }


    /**
     * 获取当前时间
     *
     * @return String类型格式化时间
     */
    public static String formatYYMMDDHHMMSS() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        return formatDay.format(date);
    }


    public static void ptMap(Map map) {
        for (Object obj : map.keySet()) {
            String key = (String) obj;
//            String value = (String) map.get(key);
//            System.out.println(key + "=" + value); , value
            Log.e(TAG,"打印map" + key);
        }

    }


    public static int getStatusBarHeight(Context context) {
        int statusBarHeight1 = context.getResources().getDimensionPixelSize(R.dimen.space_23);
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static void setFistViewPaddingTop(View view, Context context) {
        if (statusBarHeight == 0) {
            statusBarHeight = getStatusBarHeight(context);
        }
        view.setPadding(0, statusBarHeight, 0, 0);
    }


    @TargetApi(Build.VERSION_CODES.M)
    public static boolean canWrite(Context activity) {
        return Settings.System.canWrite(activity);// 检查是否被授予了WRITE_SETTINGS权限
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean canDrawOverlays(Context activity) {
        return Settings.canDrawOverlays(activity);//检查是否被授予了SYSTEM_ALERT_WINDOW权限
    }


//    public static void requestWriteSettings(Activity activity) {
//        if (Build.VERSION.SDK_INT >= 23 && !canWrite(activity)) {
//            startWriteSettings(activity);
//        }
//    }

//    private static void startWriteSettings(Activity activity) {
//        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
//        intent.setData(Uri.parse("package:" + activity.getPackageName()));
//        activity.startActivityForResult(intent, PermissionContacts.WRITE_SETTINGS);
//    }


//    public static boolean is_Network_Available(Context context) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager != null) {
//            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
//                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI || activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    public static boolean is_Network_Available(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI || activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 字符串时间转换为 long
     *
     * @param user_time
     * @return
     */
    public static long getStringTimeToLong(String user_time) {
        long l = 0;
        String msg = "yyyy-MM-dd HH:mm:ss";
        if (!TextUtils.isEmpty(user_time)) {
            if (user_time.length() == msg.length()) {
                SimpleDateFormat sdf = new SimpleDateFormat(msg);
                Date d;
                try {
                    d = sdf.parse(user_time);
                    l = d.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return l;
            }
        }
        return l;
    }

    /**
     * 字符串时间转换为 long
     *
     * @param user_time 字符串时间
     * @return long类型时间
     */
    public static long getStringTimeToLongDeletss(String user_time) {
        long l = 0;
        String msg = "yyyy-MM-dd HH:mm:ss";
        if (!TextUtils.isEmpty(user_time)) {
            if (user_time.length() == msg.length()) {
                SimpleDateFormat sdf = new SimpleDateFormat(msg);
                Date d;
                try {
                    d = sdf.parse(user_time);
                    long time = d.getTime();
                    long l1 = time / 60;
                    l = l1 * 60;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return l;
            }
        }
        return l;
    }

    /***
     * 获取当前日期距离过期时间的日期差值
     * @param endTime String
     * @return 差值字符串
     */
    public static String dateDiff(String endTime) {
        String strTime = null;
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = sd.format(curDate);
        try {
            // 距现在还差多少时间
//            diff = sd.parse(endTime).getTime()
//                    - sd.parse(str).getTime();
            //已过时间
            diff = sd.parse(str).getTime() - sd.parse(endTime).getTime();

            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            Log.e("测试时间","小时"+hour+"  分钟"+min+"  秒数"+sec);

            if (day > 0) {
                strTime = day + "天" + hour + "时";
            } else {

                if (hour >= 0) {
                    strTime = day + "天" + hour + "时" + min + "分"+ sec + "秒";

                } else {
                    if (sec >= 0) {
                        strTime = day + "天" + hour + "时" + min + "分" + sec + "秒";
                    } else {
                        strTime = "显示即将到期";
                    }
                }
            }

            return strTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

//    /**
//     * 设置图片透明度，置灰
//     * matrix.setSaturation(1); 正常状态
//     *
//     * @param view 不能直接写View
//     */
//    public static void setImageCm(SimpleDraweeView view, float sat) {
//        ColorMatrix matrix = new ColorMatrix();
//        matrix.setSaturation(sat);
//        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
//        view.setColorFilter(filter);
//    }

}
