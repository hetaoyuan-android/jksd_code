package com.glens.jksd.activity.activity_task_manage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.fragment.TaskFragment.TaskBillFragment;
import com.glens.jksd.fragment.TaskFragment.TaskLogFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskItemActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.rb_task_bill)
    RadioButton mRbTaskBill;
    @Bind(R.id.rb_task_log)
    RadioButton mRbTaskLog;
    @Bind(R.id.rg_repair)
    RadioGroup mRgRepair;
    @Bind(R.id.tv_task_bill)
    TextView mTvTaskBill;
    @Bind(R.id.tv_task_record)
    TextView mTvTaskRecord;
    @Bind(R.id.ll_rg_container)
    RelativeLayout mLlRgContainer;
    @Bind(R.id.fl_repair_item)
    FrameLayout mFlRepairItem;

    private Fragment mCurrentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_item);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setTitleText(R.string.task_manager);
        getLlBasetitleBack().setOnClickListener(this);
        selectTaskBillFragment();
        mRbTaskBill.setChecked(true);
        ButterKnife.bind(this);
    }


    @Override
    public void onClick(View v) {
        finish();
    }

    @OnClick({R.id.rb_task_bill, R.id.rb_task_log})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_task_bill:
                changeTextColor(true,false);
                switchFragment(new TaskBillFragment()).commit();
                break;
            case R.id.rb_task_log:
                changeTextColor(false,true);
                switchFragment(new TaskLogFragment()).commit();
                break;
        }
    }

    private void selectTaskBillFragment() {
        changeTextColor(true, false);
        switchFragment(new TaskBillFragment()).commit();
    }

    private void changeTextColor(boolean b, boolean b1) {
        mTvTaskBill.setBackgroundColor(ContextCompat.getColor(this, b ? R.color.title_theme : R.color.grey_dialog));
        mTvTaskRecord.setBackgroundColor(ContextCompat.getColor(this, b1 ? R.color.title_theme : R.color.grey_dialog));
    }

    private FragmentTransaction switchFragment(Fragment targetFragment) {

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (mCurrentFragment != null) {
                transaction.hide(mCurrentFragment);
            }
            transaction.add(R.id.fl_repair_item, targetFragment, targetFragment.getClass().getName());

        } else {
            transaction
                    .hide(mCurrentFragment)
                    .show(targetFragment);
        }
        mCurrentFragment = targetFragment;
        return transaction;
    }

}
