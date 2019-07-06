package com.glens.jksd.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.glens.jksd.utils.imagePick.model.ImageItem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

/**
 * @author Administrator
 */
public class StringUtil {
    public static final int NUM_2 = 2;

    /**
     * 保留两位小数
     */
    public static String formatStringPrice(int price) {
        DecimalFormat df = new DecimalFormat("##0.00");
        return df.format(price * 0.01);
    }

    /**
     * 保留两位小数
     */
    public static String formatStringPrice(float price) {
        DecimalFormat df = new DecimalFormat("##0.00");
        return df.format(price * 0.01);
    }

    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format  如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断两个日期大小
     *
     * @param date1 日期格式YYYY-MM-DD
     * @param date2
     * @return 1:date1>date2 -1:date1<date2 0:date1=date2
     */
    public static int compareData(String date1, String date2) {
        String[] split1 = date1.split("-");
        String[] split2 = date2.split("-");
        if (Integer.parseInt(split1[0]) > Integer.parseInt(split2[0])) {
            return 1;
        } else if (Integer.parseInt(split1[0]) < Integer.parseInt(split2[0])) {
            return -1;
        } else {
            //年份相等
            if (Integer.parseInt(split1[1]) > Integer.parseInt(split2[1])) {
                return 1;
            } else if (Integer.parseInt(split1[1]) < Integer.parseInt(split2[1])) {
                return -1;
            } else {
                //月份相等
                if (Integer.parseInt(split1[NUM_2]) > Integer.parseInt(split2[NUM_2])) {
                    return 1;
                } else if (Integer.parseInt(split1[NUM_2]) < Integer.parseInt(split2[NUM_2])) {
                    return -1;
                } else {
                    //天份相等
                    return 0;
                }
            }
        }
    }

    /**
     * 判断两个日期大小
     *
     * @param date1 日期格式yyyy-MM-dd HH:mm
     * @param date2
     * @return 1:date1>date2 -1:date1<date2 0:date1=date2
     */
    public static int compareData1(String date1, String date2) {
        String date11 = date1.substring(0, 10);
        String date22 = date2.substring(0, 10);
        String[] split1 = date11.split("-");
        String[] split2 = date22.split("-");
        String hour1 = date1.substring(11, 13);
        String minute1 = date1.substring(14, 16);
        String hour2 = date2.substring(11, 13);
        String minute2 = date2.substring(14, 16);
        if (Integer.parseInt(split1[0]) > Integer.parseInt(split2[0])) {
            return 1;
        } else if (Integer.parseInt(split1[0]) < Integer.parseInt(split2[0])) {
            return -1;
        } else {
            //年份相等
            if (Integer.parseInt(split1[1]) > Integer.parseInt(split2[1])) {
                return 1;
            } else if (Integer.parseInt(split1[1]) < Integer.parseInt(split2[1])) {
                return -1;
            } else {
                //月份相等
                if (Integer.parseInt(split1[NUM_2]) > Integer.parseInt(split2[NUM_2])) {
                    return 1;
                } else if (Integer.parseInt(split1[NUM_2]) < Integer.parseInt(split2[NUM_2])) {
                    return -1;
                } else {
                    if (Integer.parseInt(hour1) > Integer.parseInt(hour2)) {
                        return 1;
                    } else if (Integer.parseInt(hour1) < Integer.parseInt(hour2)) {
                        return -1;
                    } else {
                        if (Integer.parseInt(minute1) > Integer.parseInt(minute2)) {
                            return 1;
                        } else if (Integer.parseInt(minute1) < Integer.parseInt(minute2)) {
                            return -1;
                        } else {
                            return 0;
                        }

                    }
                }
            }
        }
    }

    /**
     * 把String转化为double
     */
    public static double convertToDouble(String number, double defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(number);
        } catch (Exception e) {
            return defaultValue;
        }

    }

    public static String getnewdate(String date) throws ParseException {
        // 先将str转化成时间格式
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date response;
        response = sdf.parse(date);
        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sDate = sdf2.format(response);
        return sDate;
    }

    public static String getnewdate2(String date) throws ParseException {
        // 先将str转化成时间格式
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date response;
        response = sdf.parse(date);
        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = sdf2.format(response);
        return sDate;
    }

    public static String getnewdate3(long time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return sdf.format(calendar.getTime());
    }

    public static String getnewdate4(String date) throws ParseException {
        // 先将str转化成时间格式
        DateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        Date response;
        response = sdf.parse(date);
        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf2.format(response);
        return sDate;
    }

    public static String getnewdate5(String date) throws ParseException {
        // 先将str转化成时间格式
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date response;
        response = sdf.parse(date);
        DateFormat sdf2 = new SimpleDateFormat("yyyy-M-d");
        String sDate = sdf2.format(response);
        return sDate;
    }

    /**
     * 获得该月第一天再前一个星期
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        cal.add(Calendar.DAY_OF_MONTH, -6);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获得该月最后一天再后两个星期
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.add(Calendar.DAY_OF_MONTH, 13);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    public static String isEmpty(String content) {
        if (TextUtils.isEmpty(content)) {
            return "";
        } else {
            return content;
        }
    }

    /**
     * double转String,保留小数点后两位
     *
     * @param num
     * @return
     */
    public static String floatToString(float num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }

    public static boolean isInsideTwoDays(String once) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse(once);
            Date d2 = new Date(System.currentTimeMillis());//你也可以获取当前时间
            long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            Log.e("Time-->", "" + days + "天" + hours + "小时" + minutes + "分");
            if (days >= 1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 生产随机字母
     *
     * @param length
     * @return
     */
    public static String getItemName(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 判断手机格式是否正确
     *
     * @param mobiles
     * @return 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     */
    public static boolean isMobileNO(String mobiles) {
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][345789]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    public static <T> ArrayList<ArrayList<T>> getJson(String json, Class<T> cls) {
        ArrayList<ArrayList<T>> returnList = new ArrayList<>();
        ArrayList<String> list = getJsonList(json, String.class);
        for (String a : list) {
            ArrayList<T> secondList = getJsonList(a, cls);
            returnList.add(secondList);
        }
        return returnList;
    }

    public static <T> ArrayList<T> getJsonList(String json, Class<T> cls) {
        ArrayList<T> list = new ArrayList<>();
        try {
            ArrayList<T> data = (ArrayList<T>) JSON.parseArray(json, cls);
            if (data != null) {
                list.addAll(data);
            }
        } catch (Exception e) {

        }
        return list;
    }


    /**
     * 条码校验
     * @param cardNo
     * @return
     */
    public static boolean matchLuhn(String cardNo) {
        try {
            int[] cardNoArr = new int[cardNo.length()];
            for (int i = 0; i < cardNo.length(); i++) {
                cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
            }
            for (int i = cardNoArr.length - 2; i >= 0; i -= 2) {
                cardNoArr[i] <<= 1;
                cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
            }
            int sum = 0;
            for (int i = 0; i < cardNoArr.length; i++) {
                sum += cardNoArr[i];
            }
            return sum % 10 == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(System.currentTimeMillis());
    }

//   public static String getIMEI_1(Context context){
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        return  tm != null ? tm.getDeviceId() : null;
//    }
        /**     * IMEI 2号     * @param context     * @return     */
        public static String getIMEI_2(Context context){
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Class clazz = tm.getClass();
            try {
                Method getImei = clazz.getDeclaredMethod("getImei",int.class);
                return getImei.invoke(tm,1).toString();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return  null;    }

    /** 将格林威治时间格式转为指定的时间格式 */
    public static String GTMToLocal(String GTMDate) {

        int tIndex = GTMDate.indexOf("T");
        String dateTemp = GTMDate.substring(0, tIndex);
        String timeTemp = GTMDate.substring(tIndex + 1, GTMDate.length() - 6);
        String convertString = dateTemp + " " + timeTemp;

        SimpleDateFormat format;
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date result_date;
        long result_time = 0;

        if (null == GTMDate) {
            return GTMDate;
        } else {
            try {
                format.setTimeZone(TimeZone.getTimeZone("GMT00:00"));
                result_date = format.parse(convertString);
                result_time = result_date.getTime();
                format.setTimeZone(TimeZone.getDefault());
                return format.format(result_time);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return GTMDate;

    }

    /**
     * 给时间加上几个小时
     * @param day 当前时间 格式：yyyy-MM-dd HH:mm:ss
     * @param hour 需要加的时间
     * @return
     */
    public static String addDateMinut(String day, int hour){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }

    public static String changeSecond(int second) {
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second % 3600;
        if (second > 3600) {
            h = second / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
        }
        return h + ":" + d + ":" + s + "";
    }

    public static String getPath(ImageItem item) {
        String newPath = "";
        if (TextUtils.isEmpty(item.thumbnailPath)) {
            newPath = item.sourcePath;
        } else {
            newPath = item.thumbnailPath;
        }
        return "file://" + newPath;
    }


    public static List removeDuplicateImageById(List<ImageItem> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (!TextUtils.isEmpty(list.get(j).imageId) && !TextUtils.isEmpty(list.get(i).imageId)) {
                    if (TextUtils.equals(list.get(j).imageId, list.get(i).imageId)) {
                        list.remove(j);
                    }
                }
            }
        }
        return list;
    }
}
