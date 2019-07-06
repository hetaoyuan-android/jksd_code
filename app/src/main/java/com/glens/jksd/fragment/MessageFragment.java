package com.glens.jksd.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.glens.jksd.base.BaseFragment;


public class MessageFragment extends BaseFragment {


    private static final String TAG = MessageFragment.class.getSimpleName();//"MainFragment"
    private TextView textView;

    @Override
    protected View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        textView.setText("自定义页面");
    }
}
