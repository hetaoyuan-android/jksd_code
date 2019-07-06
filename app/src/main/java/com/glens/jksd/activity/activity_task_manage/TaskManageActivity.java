package com.glens.jksd.activity.activity_task_manage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.TaskManageAdapter.TaskMainAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.RepairTaskListBean;
import com.glens.jksd.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工程管理
 */
public class TaskManageActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.repair_task_search)
    EditText mRepairTaskSearch;
    @Bind(R.id.task_start_time)
    TextView mTaskStartTime;
    @Bind(R.id.task_end_time)
    TextView mTaskEndTime;
    @Bind(R.id.task_type)
    TextView mTaskType;
    @Bind(R.id.tv_task_size)
    TextView mTvTaskSize;
    @Bind(R.id.task_RecyclerView)
    RecyclerView mTaskRecyclerView;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;

    private TaskMainAdapter mAdapter;
    private List<RepairTaskListBean> mTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manage);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void initView() {
        setTitleText(R.string.task_manager);
        getLlBasetitleBack().setOnClickListener(this);
        mRepairTaskStandardText.setText("新增");
        setButtonDrawable(mRepairTaskStandardText);
        ButterKnife.bind(this);
    }

    private void initData() {
        mTaskList = new ArrayList<>();
        mTaskList.add(new RepairTaskListBean("98k", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M4A1", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M24", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("98k", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M4A1", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M24", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("98k", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M4A1", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M24", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("98k", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M4A1", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M24", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("98k", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M4A1", "装甲师", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M24", "装甲师", "检修任务1", "大修", "2019年4月"));
        initRepairTaskView(mTaskList);

    }

    /**
     * 初始化 Recycler配置
     *
     * @param title 标题
     */
    private void initRepairTaskView(List<RepairTaskListBean> title) {
        TaskMainAdapter taskTurnDownAdapter = new TaskMainAdapter(title, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTaskRecyclerView.setLayoutManager(linearLayoutManager);
        mTaskRecyclerView.setAdapter(taskTurnDownAdapter);
        taskTurnDownAdapter.setOnItemClickListener((v, bean) -> {
                RepairTaskListBean repairTaskListBean = (RepairTaskListBean) bean;
            ToastUtils.showToastSafe(this,((RepairTaskListBean) bean).getDepartment());
            changeToTaskItemActivity();
        });
    }

    private void changeToTaskItemActivity() {
        Intent intent = new Intent(this, TaskItemActivity.class);
        startActivity(intent);
    }


    @OnClick({R.id.task_start_time, R.id.task_end_time, R.id.task_type, R.id.repair_task_standard_ry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.task_start_time:
                break;
            case R.id.task_end_time:
                break;
            case R.id.task_type:
                break;
            case R.id.repair_task_standard_ry:
                break;
        }
    }

    private  void setButtonDrawable(TextView textView) {
        Drawable drawable = getResources().getDrawable(R.drawable.repair_add);
        drawable.setBounds(0, 0, 25, 25);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        textView.setCompoundDrawables(drawable, null, null, null);//只放左边
    }
}
