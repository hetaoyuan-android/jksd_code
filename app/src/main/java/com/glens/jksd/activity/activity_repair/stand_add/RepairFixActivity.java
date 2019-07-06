package com.glens.jksd.activity.activity_repair.stand_add;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 新增消缺工作
 *
 */
public class RepairFixActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_fix);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void initView() {
        setTitleText(R.string.repair_fix_task);
        getLlBasetitleBack().setOnClickListener(this);
        mRepairTaskStandardText.setText(R.string.repair_check_submit);
        ButterKnife.bind(this);
    }

}
