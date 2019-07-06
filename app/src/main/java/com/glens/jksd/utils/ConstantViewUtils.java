package com.glens.jksd.utils;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import com.glens.jksd.view.CustomPopWindow;

/**
 * Created by wkc on 2019/5/24.
 */
public class ConstantViewUtils {

    public static CustomPopWindow openPopUpWindow(Context context, View itemView, View layout){
        CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(context)
                .setView(layout)//显示的布局，还可以通过设置一个View
                .size(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) //设置显示的大小，不设置就默认包裹内容
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .create()//创建PopupWindow
                .showAsDropDown(itemView, 0, 0);//显示PopupWindow
        return popWindow;
    }
}
