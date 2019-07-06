package com.glens.jksd.utils.imagePick.view;


import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.utils.UiUtils;

/**
 * @author sunyan
 */
public class SelectPicPopupWindow extends PopupWindow {
    private TextView tvTakePhoto, tvPickPhoto, tvCancel;
    private  View mMenuView;

    public SelectPicPopupWindow(Activity context, OnClickListener itemsOnClick) {
        super(context);
        mMenuView = LayoutInflater.from(context).inflate(R.layout.view_pop_select_pic, null, true);
        tvTakePhoto = (TextView) mMenuView.findViewById(R.id.btn_take_photo);
        tvPickPhoto = (TextView) mMenuView.findViewById(R.id.btn_pick_photo);
        tvCancel = (TextView) mMenuView.findViewById(R.id.btn_cancel);
        // 取消按钮
        tvCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });
        // 设置按钮监听
        tvPickPhoto.setOnClickListener(itemsOnClick);
        tvTakePhoto.setOnClickListener(itemsOnClick);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(UiUtils.getWindowsHeight(context)
                - context.getResources().getDimensionPixelSize(
                R.dimen.space_50)
                - UiUtils.getStateBarHeight(context));
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(new BitmapDrawable());
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

}