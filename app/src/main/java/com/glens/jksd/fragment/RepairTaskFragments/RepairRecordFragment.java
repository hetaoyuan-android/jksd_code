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
import com.glens.jksd.activity.activity_repair.stand_add.RepairAddRecordActivity;
import com.glens.jksd.activity.activity_repair.stand_detail.RecordDetailActivity;
import com.glens.jksd.adapter.RepairTaskAdapter.RepairRecordAdapter;
import com.glens.jksd.base.BaseFragment;
import com.glens.jksd.bean.RepairTowerListBean;
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
 * 开收工录音
 */
public class RepairRecordFragment extends BaseFragment implements BaseRepairView {

    public static final String TAG = RepairTaskBillFragment.class.getSimpleName();
    @Bind(R.id.repair_record_RecyclerView)
    RecyclerView mRepairRecordRecyclerView;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private List<RepairTowerListBean.RecordsBean> mTaskList;
    private String taskCode;
    private RepairTowerDetailPresenter mPresenter;
    private RepairTowerListBean repairTowerListBean;
    private RepairRecordAdapter repairRecordAdapter;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_repair_record, null);
        ButterKnife.bind(this, view);
        mRepairTaskStandardText.setText(getString(R.string.repair_record_add));
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
            mPresenter.soundList(taskCode);
        }
    }


    /**
     * 初始化 Recycler配置
     *
     */
    private void initRepairCheckView() {
         repairRecordAdapter = new RepairRecordAdapter(mTaskList, mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRepairRecordRecyclerView.setLayoutManager(linearLayoutManager);

        repairRecordAdapter.setOnItemClickListener((v, bean) -> {
            RepairTowerListBean.RecordsBean repairTaskListBean = (RepairTowerListBean.RecordsBean) bean;
            changeToRepairTaskItemActivity(repairTaskListBean.getRecordCode());
        });
    }

    private void changeToRepairTaskItemActivity(String checkRecordCode) {
        Intent intent = new Intent(getActivity(), RecordDetailActivity.class);
        intent.putExtra(RepairConstantUtils.TASKCODE, checkRecordCode);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setButtonDrawable(TextView textView, int width, int height) {
        Drawable drawable = getResources().getDrawable(R.drawable.repair_record_white);
        drawable.setBounds(0, 0, width, height);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        textView.setCompoundDrawables(drawable, null, null, null);//只放左边
    }

    private void changeToRepairRecordActivity() {
        Intent repairIntent = new Intent(getActivity(), RepairAddRecordActivity.class);
//        repairIntent.putExtra("参数", department == null ? "领用" : department);
        startActivity(repairIntent);
    }

    @OnClick(R.id.repair_task_standard_ry)
    public void onViewClicked() {
        changeToRepairRecordActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter!=null){
            mPresenter.soundList(taskCode);
        }
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        repairTowerListBean = (RepairTowerListBean) bean;
        if (repairRecordAdapter != null && repairTowerListBean.getRecords() != null) {
            mRepairRecordRecyclerView.setAdapter(repairRecordAdapter);
            repairRecordAdapter.setData(repairTowerListBean.getRecords());
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
