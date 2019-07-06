package com.glens.jksd.utils;


import android.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/3/24 0024.
 */
public class StringUtils {
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
//            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Pattern p = Pattern.compile("[/\\:*?<>|\"\n\t]");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String replaceEdittext(String editable) {
        String dest = "";
        if (editable != null) {
            if (isContainChinese(editable) || isContainAZ(editable) || isContainNumber(editable) || isContainFH(editable)) {
                dest = editable;
            }
        }
        Log.e("StringUtils", "dest=" + dest);
        return dest;
    }

    public static boolean replaceEdittextBooelan(String editable) {
        if (editable != null) {
            for (int i = 0; i < editable.length(); i++) {
                String substring = editable.substring(i, i + 1);
                if (!(isContainChinese(substring) || isContainAZ(substring) || isContainNumber(substring) || isContainFH(substring))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isContainChinese(String str) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]*");
        Log.e("StringUtils", "isContainChinese   str=" + str + " 判断中文=" + pattern.matcher(str).matches());
        return pattern.matcher(str).matches();
    }

    public static boolean isContainAZ(String str) {
        Pattern pattern = Pattern.compile("[a-zA-Z]*");
        Log.e("StringUtils", "isContainChinese   str=" + str + "  判断字母=" + pattern.matcher(str).matches());
        return pattern.matcher(str).matches();
    }

    public static boolean isContainNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Log.e("StringUtils", "isContainChinese  str=" + str + "  判断数字=" + pattern.matcher(str).matches());
        return pattern.matcher(str).matches();
    }

    public static boolean isContainFH(String str) {

        String regEx = "[ ，。？！、：；……“”‘’《》｛｝ [  ] ～——/· ? !]";
        Pattern pattern = Pattern.compile(regEx);
        Log.e("StringUtils", "isContainChinese  str=" + str + " 判断符号=" + pattern.matcher(str).matches());
        return pattern.matcher(str).matches();
    }


    //    String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}d{1}-?d{8}$)|"
//            + "(^0[3-9] {1}d{2}-?d{7,8}$)|"
//            + "(^0[1,2]{1}d{1}-?d{8}-(d{1,4})$)|"
//            + "(^0[3-9]{1}d{2}-? d{7,8}-(d{1,4})$))";
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        if (checkCellphone(phoneNumber) || checkTelephone(phoneNumber) || checkPhone(phoneNumber) || checkTelephone1(phoneNumber) || checkTelephone2(phoneNumber) || checkTelephone3(phoneNumber) || checkTelephone4(phoneNumber) || checkTelephone5(phoneNumber) || checkTelephone6(phoneNumber) || checkTelephone7(phoneNumber)) {
            isValid = true;
        }
        return isValid;
    }


    /**
     * 验证手机号码
     * <p>
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147、182
     * 联通号码段:130、131、132、136、185、186、145 176
     * 电信号码段:133、153、180、189、177
     *
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,2,5-9])|(17[6-7]))\\d{8}$";
        Log.e("StringUtils", "  checkCellphone");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellphone);
        return matcher.matches();
    }

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone(String telephone) {
        Log.e("StringUtils", "  checkTelephone");
//        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        String regex = "^(0[0-9]{2,3}/-)?([2-9][0-9]{6,7})+(/-[0-9]{1,4})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        Log.e("StringUtils", "  checkTelephone=" + matcher.matches());
        return matcher.matches();
    }

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone1(String telephone) {

//        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        String regex = "/^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        Log.e("StringUtils", "  checkTelephone1=" + matcher.matches());
        return matcher.matches();
    }


    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone2(String telephone) {
//        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        String regex = "^0[0-9]{2,3}-[0-9]{8}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        Log.e("StringUtils", "  checkTelephone2=" + matcher.matches());
        return matcher.matches();
    }

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone3(String telephone) {
//        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        String regex = "/^((\\d{3,4}\\-)|)\\d{7,8}(|([-\\u8f6c]{1}\\d{1,5}))$/";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        Log.e("StringUtils", "  checkTelephone3=" + matcher.matches());
        return matcher.matches();
    }

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone4(String telephone) {
        String regex = "^((\\(\\d{3}\\))|(\\d{3}\\-))?13[0-9]\\d{8}|15[89]\\d{8}" +
                "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        Log.e("StringUtils", "  checkTelephone4=" + matcher.matches());
        return matcher.matches();
    }

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone5(String telephone) {

        String regex = "0\\d{2,3}-\\d{5,9}|0\\d{2,3}-\\d{5,9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        Log.e("StringUtils", "  checkTelephone5=" + matcher.matches());
        return matcher.matches();
    }

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone6(String telephone) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        Log.e("StringUtils", "  checkTelephone6=" + matcher.matches());
        return matcher.matches();
    }

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone7(String telephone) {
//        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        String regex = "^0[0-9]{2,3}[0-9]{8}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        Log.e("StringUtils", "  checkTelephone7=" + matcher.matches());
        return matcher.matches();
    }

    /**
     * 验证号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkPhone(String telephone) {
        String regex = "/^(0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8})|(400|800)([0-9\\\\-]{7,10})|(([0-9]{4}|[0-9]{3})(-| )?)?([0-9]{7,8})((-| |转)*([0-9]{1,4}))?$/";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        Log.e("StringUtils", "  checkPhone=" + matcher.matches());
        return matcher.matches();
    }

    /**
     * 验证密码
     *
     * @param pws
     * @return
     */
    public static boolean isSuccessPws(String pws) {
//        Pattern z1_ = Pattern.compile("^(?=.*?[a-z])(?=.*?[0-9])[a-zA-Z0-9_]{6,16}$");
        Pattern z1_ = Pattern.compile("^(?![A-Za-z]+$)(?![A-Z\\d]+$)(?![A-Z\\W]+$)(?![a-z\\d]+$)(?![a-z\\W]+$)(?![\\d\\W]+$)\\S{8,20}$");
//判断是否都成立，都包含返回true
        return z1_.matcher(pws).matches();
    }


    /**
     * 验证新密码
     *
     * @param psw
     * @return
     */
    public static boolean isSuccessNewPsw(String psw) {
        if (psw != null) {
            for (int i = 0; i < psw.length(); i++) {
                String substring = psw.substring(i, i + 1);
                // 中文             特殊符号
                if (isContainChinese(substring) || isContainFH(substring)) {
                    return false;
                }
            }
        }
        return true;
    }

}
