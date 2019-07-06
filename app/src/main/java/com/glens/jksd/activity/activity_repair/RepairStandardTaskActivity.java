package com.glens.jksd.activity.activity_repair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_repair.stand_add.RepairTowerAddActivity;
import com.glens.jksd.activity.activity_repair.stand_detail.RepairTowerDetailActivity;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.fragment.RepairTaskFragments.standtask.AddDefectFragment;
import com.glens.jksd.fragment.RepairTaskFragments.standtask.InsulatorCleanFragment;
import com.glens.jksd.fragment.RepairTaskFragments.standtask.LineCheckFragment;
import com.glens.jksd.fragment.RepairTaskFragments.standtask.RepairTowerCheckFragment;
import com.glens.jksd.fragment.RepairTaskFragments.standtask.RtvSparyFragment;
import com.glens.jksd.fragment.RepairTaskFragments.standtask.WaterTestFragment;
import com.glens.jksd.utils.RepairConstantUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 标准化作业内容
 * 根据不同的点击rb参数加载列表，
 */
public class RepairStandardTaskActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = RepairStandardTaskActivity.class.getSimpleName();
    @Bind(R.id.rb_standard_tower_check)
    RadioButton mRbStandardTowerCheck;
    @Bind(R.id.rb_standard_task_defect)
    RadioButton mRbStandardTaskDefect;
    @Bind(R.id.rb_standard_clean)
    RadioButton mRbStandardClean;
    @Bind(R.id.rb_standard_line_check)
    RadioButton mRbStandardLineCheck;
    @Bind(R.id.rb_standard_tower_spray)
    RadioButton mRbStandardTowerSpray;
    @Bind(R.id.rg_repair_standard)
    RadioGroup mRgRepairStandard;

    private int listType = RepairConstantUtils.REPAIR_ONE;
    private Fragment mCurrentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_standard_task);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void initView() {
        setTitleText(R.string.repair_standard_task);
        getLlBasetitleBack().setOnClickListener(this);
        mRgRepairStandard.check(R.id.rb_standard_tower_check);
        selectRepairTowerCheckFragment();
    }

    @OnClick({R.id.rb_standard_tower_check, R.id.rb_standard_task_defect, R.id.rb_standard_clean,
            R.id.rb_standard_line_check, R.id.rb_standard_tower_spray,R.id.rb_standard_water})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_standard_tower_check:
                selectRepairTowerCheckFragment();
                break;
            case R.id.rb_standard_task_defect:
                switchFragment(new AddDefectFragment()).commit();
                break;
            case R.id.rb_standard_clean:
                switchFragment(new InsulatorCleanFragment()).commit();
                break;
            case R.id.rb_standard_line_check:
                switchFragment(new LineCheckFragment()).commit();
                break;
            case R.id.rb_standard_tower_spray:
                switchFragment(new RtvSparyFragment()).commit();
                break;
            case R.id.rb_standard_water:
                switchFragment(new WaterTestFragment()).commit();
                break;
        }
    }

    private void changeToTowerAdd() {
        Intent intent = new Intent(this, RepairTowerAddActivity.class);
        startActivity(intent);
    }


    private void changeToRepairTaskItemActivity(String checkRecordCode) {
        Intent intent = new Intent(this, RepairTowerDetailActivity.class);
        intent.putExtra(RepairConstantUtils.TASKCODE, checkRecordCode);
        startActivity(intent);
    }


    private void selectRepairTowerCheckFragment() {
        Fragment fragment = new RepairTowerCheckFragment();
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
