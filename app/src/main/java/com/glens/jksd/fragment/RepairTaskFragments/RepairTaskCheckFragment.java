package com.glens.jksd.fragment.RepairTaskFragments;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_repair.stand_add.RepairAddCheckActivity;
import com.glens.jksd.activity.activity_repair.stand_detail.RepairTaskDetailActivity;
import com.glens.jksd.adapter.RepairTaskAdapter.RepairCheckAdapter;
import com.glens.jksd.base.BaseFragment;
import com.glens.jksd.bean.RepairTowerListBean;
import com.glens.jksd.network.presenter.RepairTowerCheckPresenter;
import com.glens.jksd.network.view.RepairTowerCheckView;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 现场勘测
 */
public class RepairTaskCheckFragment extends BaseFragment implements RepairTowerCheckView {
    public static final String TAG = RepairTaskCheckFragment.class.getSimpleName();
    @Bind(R.id.repair_task_check_RecyclerView)
    RecyclerView mRepairTaskCheckRecyclerView;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private RepairCheckAdapter repairCheckAdapter;
    private List<RepairTowerListBean.RecordsBean> mTaskList;
    private RepairTowerCheckPresenter mPresenter;
    private String mTaskCode;
    private RepairTowerListBean mRepairListBean;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_repair_task_check, null);
        ButterKnife.bind(this, view);
        mRepairTaskStandardText.setText(getResources().getText(R.string.repair_new_check));
        setButtonDrawable(mRepairTaskStandardText, 30, 30);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        initNetData();
        initRepairCheckView();
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        mTaskList = new ArrayList<>();
        mTaskCode = PreferenceUtils.getString(getActivity(), "taskCode", "");
        repairCheckAdapter = new RepairCheckAdapter(mTaskList, mContext);
        mPresenter = new RepairTowerCheckPresenter(getActivity());
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        mPresenter.siteSurveyList(mTaskCode);
    }


    /**
     * 初始化 Recycler配置
     */
    private void initRepairCheckView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRepairTaskCheckRecyclerView.setLayoutManager(linearLayoutManager);

        repairCheckAdapter.setOnItemClickListener((v, bean) -> {
            RepairTowerListBean.RecordsBean repairTaskListBean = (RepairTowerListBean.RecordsBean) bean;
            changeToRepairTaskItemActivity(repairTaskListBean.getRecordCode());
        });
    }

    private void changeToRepairTaskItemActivity(String checkRecordCode) {
        Intent intent = new Intent(getActivity(), RepairTaskDetailActivity.class);
        intent.putExtra(RepairConstantUtils.TASKCODE, checkRecordCode);
        startActivity(intent);
    }


    private void setButtonDrawable(TextView textView, int width, int height) {
        Drawable drawable = getResources().getDrawable(R.drawable.repair_check_icon);
        drawable.setBounds(0, 0, width, height);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        textView.setCompoundDrawables(drawable, null, null, null);//只放左边
    }


    @OnClick(R.id.repair_task_standard_ry)
    public void onViewClicked() {
        Intent repairIntent = new Intent(getActivity(), RepairAddCheckActivity.class);
        startActivity(repairIntent);
    }

    @Override
    public void onSuccess(RepairTowerListBean bean, String resultMsg) {
        mRepairListBean = bean;
        if (repairCheckAdapter != null && bean.getRecords() != null) {
            mRepairTaskCheckRecyclerView.setAdapter(repairCheckAdapter);
            repairCheckAdapter.setData(bean.getRecords());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.siteSurveyList(mTaskCode);
        } else {
            Log.e(TAG,"mpresenter为空");
        }
    }

    @Override
    public void setTimeTaskSearch(String startTime, String endTime) {

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
