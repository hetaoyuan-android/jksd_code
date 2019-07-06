package com.glens.jksd.fragment.TaskFragment;


import android.util.Log;
import android.view.View;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * 基本台账界面
 */
public class TaskBillFragment extends BaseFragment {
    public static final String TAG = TaskBillFragment.class.getSimpleName();



    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_task_bill, null);
        ButterKnife.bind(this, view);
        Log.e(TAG, "TaskBillFragment");
        return view;
    }

}
