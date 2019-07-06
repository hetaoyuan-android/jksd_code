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
import com.glens.jksd.activity.activity_repair.stand_add.RepairAddInformationActivity;
import com.glens.jksd.activity.activity_repair.stand_detail.InformationDetailActivity;
import com.glens.jksd.adapter.RepairTaskAdapter.RepairInformationAdapter;
import com.glens.jksd.base.BaseFragment;
import com.glens.jksd.bean.repair_task_bean.RepairInformationBean;
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
 * 检修资料
 */
public class RepairInformationFragment extends BaseFragment implements BaseRepairView {
    public static final String TAG = RepairInformationFragment.class.getSimpleName();
    @Bind(R.id.repair_information_RecyclerView)
    RecyclerView mRepairInformationRecyclerView;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private RepairInformationAdapter repairCheckAdapter;
    private List<RepairInformationBean.RecordsBean> mTaskList;
    private RepairTowerDetailPresenter mPresenter;
    private String mTaskCode;
    private RepairInformationBean informationBean;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_repair_information, null);
        ButterKnife.bind(this, view);
        mRepairTaskStandardText.setText(getString(R.string.repair_information_add));
        setButtonDrawable(mRepairTaskStandardText, 30, 30);

        return view;
    }

    private void setButtonDrawable(TextView textView, int width, int height) {
        Drawable drawable = getResources().getDrawable(R.drawable.standard_work);
        drawable.setBounds(0, 0, width, height);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        textView.setCompoundDrawables(drawable, null, null, null);//只放左边
    }

    @Override
    protected void initData() {
        super.initData();
        mTaskList = new ArrayList<>();
        initNetData();
        initRepairCheckView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        mTaskList = new ArrayList<>();
        mTaskCode = PreferenceUtils.getString(getActivity(), "taskCode", "");
        repairCheckAdapter = new RepairInformationAdapter(mTaskList, mContext);
        mPresenter = new RepairTowerDetailPresenter(getActivity());
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        mPresenter.informationList(mTaskCode);
    }


    /**
     * 初始化 Recycler配置
     */
    private void initRepairCheckView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRepairInformationRecyclerView.setLayoutManager(linearLayoutManager);

        repairCheckAdapter.setOnItemClickListener((v, bean) -> {
            RepairInformationBean.RecordsBean repairTaskListBean = (RepairInformationBean.RecordsBean) bean;
            changeToRepairTaskItemActivity(repairTaskListBean.getDatumCode());
        });
    }

    private void changeToRepairTaskItemActivity(String checkRecordCode) {
        Intent intent = new Intent(getActivity(), InformationDetailActivity.class);
        Log.e(TAG,"检修code"+checkRecordCode);
        intent.putExtra(RepairConstantUtils.DATUM_CODE, checkRecordCode);
        startActivity(intent);
    }


    @Override
    public void onSuccess(Object bean, String resultMsg) {
        informationBean = (RepairInformationBean) bean;
        if (repairCheckAdapter != null && informationBean.getRecords() != null) {
            mRepairInformationRecyclerView.setAdapter(repairCheckAdapter);
            repairCheckAdapter.setData(informationBean.getRecords());
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

    private void changeToRepairInformationActivity() {
        Intent repairIntent = new Intent(getActivity(), RepairAddInformationActivity.class);
        startActivity(repairIntent);
    }

    @OnClick(R.id.repair_task_standard_ry)
    public void onViewClicked() {
        changeToRepairInformationActivity();
    }
}
