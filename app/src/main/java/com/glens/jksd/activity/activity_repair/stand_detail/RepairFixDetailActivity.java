package com.glens.jksd.activity.activity_repair.stand_detail;

import android.os.Bundle;
import android.view.View;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * 消缺工作详情
 */
public class RepairFixDetailActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_fix_detail);
        initView();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void initView() {
        setTitleText(R.string.repair_fix_task_detail);
        getLlBasetitleBack().setOnClickListener(this);
        ButterKnife.bind(this);
    }

}
