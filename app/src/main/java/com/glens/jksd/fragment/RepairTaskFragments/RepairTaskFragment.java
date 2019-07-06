package com.glens.jksd.fragment.RepairTaskFragments;


import android.view.View;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseFragment;

/**
 * 检修任务二级页面
 */
public class RepairTaskFragment extends BaseFragment {


    public RepairTaskFragment() {
        // Required empty public constructor
    }



    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_repair_task, null);
        return view;
    }

}
