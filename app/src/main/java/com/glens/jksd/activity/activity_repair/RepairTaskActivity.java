package com.glens.jksd.activity.activity_repair;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.RepairTaskAdapter.RepairTaskAdapter;
import com.glens.jksd.base.BaseSwipeRefreshActivity;
import com.glens.jksd.bean.RepairListBean;
import com.glens.jksd.network.presenter.RepairListPresenter;
import com.glens.jksd.network.view.RepairListView;
import com.glens.jksd.utils.ConstantViewUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.view.CustomPopWindow;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 检修任务主界面
 */
public class RepairTaskActivity extends BaseSwipeRefreshActivity implements View.OnClickListener, RepairListView {
    private static final String TAG = RepairTaskActivity.class.getSimpleName();
    //    @Bind(R.id.repair_task_search)
//    TextView mRepairSearch;
    @Bind(R.id.repair_task_start_time)
    TextView mRepairTaskStartTime;
    @Bind(R.id.repair_task_end_time)
    TextView mRepairTaskEndTime;
    @Bind(R.id.repair_task_type)
    TextView mRepairTaskType;
    @Bind(R.id.repair_task_department)
    TextView mRepairTaskDepartment;
    @Bind(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @Bind(R.id.view)
    View mView;
    @Bind(R.id.rv_refresh_layout)
    RecyclerView mRepairTaskRecyclerView;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.repair_task_ry)
    RelativeLayout repairTaskRy;
    @Bind(R.id.tv_repair_bottom_hint)
    TextView tvRepairBottomHint;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.repair_task_search)
    EditText mRepairTaskSearch;

    private RepairListBean mRepairListBean;
    private RepairListPresenter mPresenter;
    private RepairTaskAdapter mAdapter;
    private HashMap<String, Object> map;
    private Handler mHandler;
    private int mPage = 0;
    private int mPageSize = 10;
    private int mOverhaulType = 0;//当前选择的任务类型
    private String mStartDate = "";
    private String mEndData = "";
    private String mTaskName = "";
    private String mCreateDeptId = "";

    Runnable runnable = () -> startStandardSearch();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_task);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        mPresenter = new RepairListPresenter(RepairTaskActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(RepairTaskActivity.this);
        map.put("page", 0);
        map.put("rows", 10);
        mPresenter.getRepairListInfo(mTaskName, mStartDate, mEndData, mOverhaulType,
                mCreateDeptId, 0, 10, this);
    }

    private void initView() {
        setTitleText(R.string.repair_task);
        mAdapter = new RepairTaskAdapter(mRepairListBean, this);
        getLlBasetitleBack().setOnClickListener(this);
        ButterKnife.bind(this);
        initRefreshLayout();
    }

    /**
     * 初始化 Recycler配置
     */
    private void initRepairManageView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.rvRefreshLayout.setLayoutManager(linearLayoutManager);
        mAdapter.setOnItemClickListener((v, bean) -> {
            RepairListBean.RecordsBean recordsBean = (RepairListBean.RecordsBean) bean;
            Log.d(TAG, "changeToRepairTaskItemActivity: " + recordsBean.getTaskCode());
            mPresenter.changeToRepairTaskItemActivity(RepairTaskActivity.this, recordsBean.getTaskCode());
        });

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mPage = 0;
            mPresenter.getRepairListInfo(mTaskName, mStartDate, mEndData, mOverhaulType, mCreateDeptId, mPage, mPageSize, RepairTaskActivity.this);
        });

        mRepairTaskSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTaskName = mRepairTaskSearch.getText().toString();
                mHandler.postDelayed(runnable, 2000);
            }
        });
    }

    private void initData() {
        mHandler = new Handler();
        map = new HashMap<>();
        initRepairManageView();
        initNetData();
    }


    private void startStandardSearch() {
        mPresenter.getRepairListInfo(mTaskName, mStartDate, mEndData, mOverhaulType, mCreateDeptId, 0, 10, RepairTaskActivity.this);
    }

    private void openPopWindow(int layout) {
        View view = LayoutInflater.from(this).inflate(layout, null);
        CustomPopWindow popWindow = ConstantViewUtils.openPopUpWindow(this, mView, view);
        switch (layout) {
            case R.layout.item_rb_detection_three:
                mPresenter.handleLogic(view, "", "", "", "", popWindow, RepairTaskActivity.this);
                break;
            case R.layout.item_rb_repair_department:
//                mPresenter.handleLogic(view, "", "", "", "", popWindow, RepairTaskActivity.this);
                break;
        }

    }

    private void openPopThreeWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_rb_repair_three, null);
        CustomPopWindow popWindow = ConstantViewUtils.openPopUpWindow(this, mView, view);
        mPresenter.handleLogic(view, "", "", "", "", popWindow, RepairTaskActivity.this);
    }


    @Override
    protected void onRefreshStarted() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void changeToItemActivity() {

    }

    @Override
    public void onSuccess(RepairListBean bean, String resultMsg) {
        Log.e("检修数据消息", resultMsg);
        Log.e(TAG, "获取数据" + bean.toString());
        dismissSvpDilog(1000);
        mSwipeRefreshLayout.setRefreshing(false);
        mRepairListBean = bean;
        if (mAdapter != null && bean.getRecords() != null) {
            mRepairTaskRecyclerView.setAdapter(mAdapter);
            mAdapter.setData(bean);
        }
    }

    @Override
    public void setBottomViewText(int total, int overhaulCnt, int techTransCnt, int maintainCnt) {
        tvRepairBottomHint.setText(String.valueOf("共" + total + "条记录,大修" + overhaulCnt + "条，技改" + techTransCnt +
                "条,日常维护" + maintainCnt + "条"));
    }

    @Override
    public void setTimeTaskSearch(String startTime, String endTime) {
        if (startTime != null) {
            mStartDate = startTime;
            startStandardSearch();
        } else if (endTime != null) {
            mEndData = endTime;
            startStandardSearch();
        }
    }

    @Override
    public void setTaskType(int type) {
        mOverhaulType = type;
    }

    @Override
    public int geTaskType() {
        return mOverhaulType;
    }

    @Override
    public void onError(String result) {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showErrorView(Throwable throwable) {

    }

    @OnClick({R.id.repair_task_start_time, R.id.repair_task_end_time, R.id.repair_task_type, R.id.repair_task_department})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.repair_task_start_time:
                mPresenter.openTimerPicker(RepairConstantUtils.START_TIME, RepairTaskActivity.this);
                break;
            case R.id.repair_task_end_time:
                mPresenter.openTimerPicker(RepairConstantUtils.END_TIME, RepairTaskActivity.this);
                break;
            case R.id.repair_task_type:
                openPopThreeWindow();
                break;
            case R.id.repair_task_department:
                openPopWindow(R.layout.item_rb_repair_department);
                break;
        }
    }
}
