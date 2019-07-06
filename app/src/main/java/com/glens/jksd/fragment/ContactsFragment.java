package com.glens.jksd.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.glens.jksd.base.BaseFragment;


public class ContactsFragment extends BaseFragment {


    private static final String TAG = ContactsFragment.class.getSimpleName();//"MainFragment"
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
        textView.setText("其他页面");
    }
}
