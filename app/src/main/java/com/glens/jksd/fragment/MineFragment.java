package com.glens.jksd.fragment;

import android.view.View;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseFragment;


public class MineFragment extends BaseFragment{

    private static final String TAG = MineFragment.class.getSimpleName();


    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_mine, null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

    }



}
