package com.glens.jksd.activity.activity_repair;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.fragment.RepairTaskFragments.RepairGroundFragment;
import com.glens.jksd.fragment.RepairTaskFragments.RepairInformationFragment;
import com.glens.jksd.fragment.RepairTaskFragments.RepairRecordFragment;
import com.glens.jksd.fragment.RepairTaskFragments.RepairTaskBillFragment;
import com.glens.jksd.fragment.RepairTaskFragments.RepairTaskCheckFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 检修任务item
 */
public class RepairTaskItemActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = RepairTaskItemActivity.class.getSimpleName();
    @Bind(R.id.ll_rg_container)
    RelativeLayout llRgContainer;
    @Bind(R.id.rb_repair_task_bill)
    RadioButton rbRepairTaskBill;
    @Bind(R.id.rb_repair_task_check)
    RadioButton rbRepairTaskCheck;
    @Bind(R.id.rb_repair_records)
    RadioButton rbRepairRecords;
    @Bind(R.id.rb_repair_ground_manage)
    RadioButton rbRepairGroundManage;
    @Bind(R.id.rb_repair_information)
    RadioButton rbRepairInformation;
    @Bind(R.id.rg_repair)
    RadioGroup rgRepair;
    @Bind(R.id.tv_bill)
    TextView tvBill;
    @Bind(R.id.tv_check)
    TextView tvCheck;
    @Bind(R.id.tv_records)
    TextView tvRecords;
    @Bind(R.id.tv_ground)
    TextView tvGround;
    @Bind(R.id.tv_information)
    TextView tvInformation;

    private Fragment mCurrentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_task_item);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setTitleText(R.string.repair_task);
        getLlBasetitleBack().setOnClickListener(this);
        selectRepairTaskBillFragment();
        rbRepairTaskBill.setChecked(true);
        ButterKnife.bind(this);

    }



    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    private void changeTextColor(boolean b, boolean b1, boolean b2, boolean b3, boolean b4) {
        tvBill.setBackgroundColor(ContextCompat.getColor(this, b ? R.color.title_theme : R.color.grey_dialog));
        tvCheck.setBackgroundColor(ContextCompat.getColor(this, b1 ? R.color.title_theme : R.color.grey_dialog));
        tvRecords.setBackgroundColor(ContextCompat.getColor(this, b2 ? R.color.title_theme : R.color.grey_dialog));
        tvGround.setBackgroundColor(ContextCompat.getColor(this, b3 ? R.color.title_theme : R.color.grey_dialog));
        tvInformation.setBackgroundColor(ContextCompat.getColor(this, b4 ? R.color.title_theme : R.color.grey_dialog));
    }

    @OnClick({R.id.rb_repair_task_bill, R.id.rb_repair_task_check, R.id.rb_repair_records, R.id.rb_repair_ground_manage, R.id.rb_repair_information})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_repair_task_bill:
                selectRepairTaskBillFragment();
                break;
            case R.id.rb_repair_task_check:
                changeTextColor(false, true, false, false, false);
                switchFragment(new RepairTaskCheckFragment()).commit();
                break;
            case R.id.rb_repair_records:
                changeTextColor(false, false, true, false, false);
                switchFragment(new RepairRecordFragment()).commit();
                break;
            case R.id.rb_repair_ground_manage:
                changeTextColor(false, false, false, true, false);
                switchFragment(new RepairGroundFragment()).commit();
                break;
            case R.id.rb_repair_information:
                changeTextColor(false, false, false, false, true);
                switchFragment(new RepairInformationFragment()).commit();
                break;

        }
    }


    private void selectRepairTaskBillFragment() {
        changeTextColor(true, false, false, false, false);
        Fragment fragment = new RepairTaskBillFragment();
        switchFragment(fragment).commit();
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
