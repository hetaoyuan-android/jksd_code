package com.glens.jksd.fragment.TaskFragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_task_manage.TaskLogAddActivity;
import com.glens.jksd.base.BaseFragment;
import com.glens.jksd.bean.RepairTaskListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工程管理日志界面
 */
public class TaskLogFragment extends BaseFragment {
    public static final String TAG = TaskLogFragment.class.getSimpleName();
    @Bind(R.id.tv_task_size)
    TextView mTvTaskSize;
    @Bind(R.id.task_RecyclerView)
    RecyclerView mTaskRecyclerView;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;

    private List<RepairTaskListBean> mTaskList = new ArrayList<>();

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_task_log, null);
        ButterKnife.bind(this, view);
        Log.e(TAG, "TaskLogFragment");
        mRepairTaskStandardText.setText(getResources().getText(R.string.task_log_add));
        setButtonDrawable(mRepairTaskStandardText);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        mTaskList = new ArrayList<>();
        mTaskList.add(new RepairTaskListBean("98k", "研发部", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M4A1", "行政部", "检修任务1", "大修", "2019年4月"));
        mTaskList.add(new RepairTaskListBean("M24", "后勤部", "检修任务1", "大修", "2019年4月"));
//        initTaskData(mTaskList);

    }

//    private void initTaskData(List<RepairTaskListBean> mTaskList) {
////        RepairCheckAdapter taskTurnDownAdapter = new RepairCheckAdapter(mTaskList, mContext);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mTaskRecyclerView.setLayoutManager(linearLayoutManager);
//        mTaskRecyclerView.setAdapter(taskTurnDownAdapter);
//        taskTurnDownAdapter.setOnItemClickListener((v, bean) -> {
////                RepairTaskListBean repairTaskListBean = (RepairTaskListBean) bean;
////            changeToRepairTaskItemActivity();
//            ToastUtils.showToastSafe(mContext, "Recycler配置");
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setButtonDrawable(TextView textView) {
        Drawable drawable = getResources().getDrawable(R.drawable.repair_add);
        drawable.setBounds(0, 0, 25, 25);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        textView.setCompoundDrawables(drawable, null, null, null);//只放左边
    }

    @OnClick(R.id.repair_task_standard_ry)
    public void onViewClicked() {
        changeToTaskLogDetail();
    }

    private void changeToTaskLogDetail() {
        Intent repairIntent = new Intent(getActivity(), TaskLogAddActivity.class);
        startActivity(repairIntent);
    }
}
