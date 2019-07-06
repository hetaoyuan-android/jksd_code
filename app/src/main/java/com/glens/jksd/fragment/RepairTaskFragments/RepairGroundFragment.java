package com.glens.jksd.fragment.RepairTaskFragments;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_repair.stand_add.RepairAddGroundActivity;
import com.glens.jksd.activity.activity_repair.stand_detail.GroundDetailActivity;
import com.glens.jksd.adapter.RepairTaskAdapter.RepairGroundAdapter;
import com.glens.jksd.base.BaseFragment;
import com.glens.jksd.bean.repair_task_bean.RepairGroundBean;
import com.glens.jksd.network.presenter.RepairTowerDetailPresenter;
import com.glens.jksd.network.view.BaseRepairView;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 接地线管理
 */
public class RepairGroundFragment extends BaseFragment implements BaseRepairView {
    public static final String TAG = RepairGroundFragment.class.getSimpleName();
    @Bind(R.id.repair_ground_RecyclerView)
    RecyclerView mRepairGroundRecyclerView;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;
    @Bind(R.id.ly_time)
    LinearLayout mLyTime;
    @Bind(R.id.tv_standard_start_time)
    TextView tvStandardStartTime;
    @Bind(R.id.tv_standard_end_time)
    TextView tvStandardEndTime;
    @Bind(R.id.view)
    View mView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.ry_bottom)
    RelativeLayout ryBottom;

    private List<RepairGroundBean.RecordsBean> mTaskList;
    private String taskCode;
    private RepairTowerDetailPresenter mPresenter;
    private RepairGroundBean repairTowerListBean;
    private RepairGroundAdapter groundAdapter;


    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_repair_ground, null);
        ButterKnife.bind(this, view);
        mRepairTaskStandardText.setText(getString(R.string.repair_ground_manage_add));
        mLyTime.setVisibility(View.GONE);
        mView.setVisibility(View.GONE);
        setButtonDrawable(mRepairTaskStandardText, 30, 30);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        mTaskList = new ArrayList<>();
        initRepairCheckView();
        initNetData();
    }

    private void setButtonDrawable(TextView textView, int width, int height) {
        Drawable drawable = getResources().getDrawable(R.drawable.repair_ground_lead_icon);
        drawable.setBounds(0, 0, width, height);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        textView.setCompoundDrawables(drawable, null, null, null);//只放左边
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        taskCode = PreferenceUtils.getString(getActivity(), "taskCode", "");
        Log.e(TAG, "taskCode " + taskCode);
        mPresenter = new RepairTowerDetailPresenter(getActivity());
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        if (taskCode != null) {
            mPresenter.groundList(taskCode);
        }
    }

    /**
     * 初始化 Recycler配置
     *
     */
    private void initRepairCheckView() {
        groundAdapter = new RepairGroundAdapter(mTaskList, mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRepairGroundRecyclerView.setLayoutManager(linearLayoutManager);

        groundAdapter.setOnItemClickListener((v, bean) -> {
            RepairGroundBean.RecordsBean repairTaskListBean = (RepairGroundBean.RecordsBean) bean;
            changeToRepairTaskItemActivity(repairTaskListBean.getGroudWireType());
        });
    }

    private void changeToRepairTaskItemActivity(int groudWireType) {
//        Intent intent = new Intent(getActivity(), GroundDetailActivity.class);
        Intent intent = new Intent(getActivity(), GroundDetailActivity.class);
        intent.putExtra(RepairConstantUtils.TASKTYPE, groudWireType);
        startActivity(intent);
    }


    private void changeToRepairGroundItemActivity(int groudWireType) {
        Intent repairIntent = new Intent(getActivity(), RepairAddGroundActivity.class);
        repairIntent.putExtra(RepairConstantUtils.TASKTYPE, groudWireType);
        startActivity(repairIntent);
    }




    @OnClick(R.id.repair_task_standard_ry)
    public void onViewClicked() {
        changeToRepairGroundItemActivity(RepairConstantUtils.REPAIR_ONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        repairTowerListBean = (RepairGroundBean) bean;
        if (groundAdapter != null && repairTowerListBean.getRecords() != null) {
            mRepairGroundRecyclerView.setAdapter(groundAdapter);
            groundAdapter.setData(repairTowerListBean.getRecords());
        }
    }

    @Override
    public void showDialog(String message) {
        showSvpDilog(getActivity(), message, false, null, null);
    }

    @Override
    public void hideDialog() {
        dismissSvpDialog(RepairConstantUtils.DIALOG_TIME);
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onError(String result) {
        Log.e(TAG, "请求结果 " + result);
        ToastUtils.showToastSafe(getActivity(), result);
    }


}
