package com.glens.jksd.activity.activity_detect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.detection.infrared.InfraredMainAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.deteect.InfraraedListBean;
import com.glens.jksd.network.view.detection.GroundResistancePresenter;
import com.glens.jksd.network.view.detection.GroundResistanceSearchView;
import com.glens.jksd.upimg.ToastUtil;
import com.glens.jksd.utils.ConstantViewUtils;
import com.glens.jksd.utils.LogUtil;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.view.CustomPopWindow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 检测管理红外测温任务
 */
public class DetectionManagerInfraredActivity extends BaseActivity implements View.OnClickListener, GroundResistanceSearchView {

    public static final String TAG = DetectionManagerInfraredActivity.class.getSimpleName();

    @Bind(R.id.rv_main_infrared)
    RecyclerView mRvMainInfrared;
    @Bind(R.id.repair_task_search)
    EditText mInfraredSearch;
    @Bind(R.id.tv_infrared_bottom_text)
    TextView tvInfraredBottomText;
    @Bind(R.id.detection_main_search)
    TextView detectionMainSearch;
    @Bind(R.id.tv_infrared_process_state)
    TextView tvInfraredProcessState;
    @Bind(R.id.ly_infrared)
    LinearLayout lyInfrared;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private InfraraedListBean mInfraraedListBean;
    private GroundResistancePresenter mPresenter;
    private InfraredMainAdapter mAdapter;

    private String mTaskCode;
    private int page = 1;
    private int mTotalPages; // 总页数
    private int rows = 10;

    private String searchContent;
    private Handler mHandler;
    private int taskType = 0;

    Runnable runnable = () -> startSearch(searchContent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_manager_infrared);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        setTitleText(R.string.detect_infrared_title);
        getLlBasetitleBack().setOnClickListener(this);
//        mGoDispose.setOnClickListener(this);
        SpannableString spannableString = new SpannableString("请输入电压、线路、杆号等搜索");
        mInfraredSearch.setHint(spannableString);
        mTaskCode = getIntent().getStringExtra(RepairConstantUtils.INFRARED_TASK_CODE);
        Log.i(TAG, "" + mTaskCode);
        //RecyclerView的设置
        mRvMainInfrared.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new InfraredMainAdapter(mInfraraedListBean, this);
        //点击事件
        mAdapter.setOnItemClickListener((v, bean) -> {
            InfraraedListBean.RecordsBean recordsBean = (InfraraedListBean.RecordsBean) bean;
            Log.d(TAG, "changeToRepairTaskItemActivity: " + recordsBean.getRecodeCode());
            String title = Integer.parseInt(recordsBean.getLineVol()) / 1000 + "kV" + recordsBean.getLineName() + recordsBean.getTowerNo();
            goInfraredDetailListInfo(this, recordsBean.getRecodeCode(), title);
        });

        // 搜索
        mInfraredSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                detectionMainSearch.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // 滑动到底部不自动刷新
        refreshLayout.setEnableAutoLoadMore(false);
        //取消内容不满一页时开启上拉加载功能
//        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        // 下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getInfraredList("", "", "", "", page, rows, mTaskCode);
            }
        });
        // 上拉刷新
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (page < mTotalPages) {
                    ++page;
                    mPresenter.getInfraredList("", "", "", "", page, rows, mTaskCode);
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });

    }

    /**
     * 初始化绑定presenter
     */
    private void initData() {
        mHandler = new Handler();
        mPresenter = new GroundResistancePresenter(this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(DetectionManagerInfraredActivity.this);
        mPresenter.getInfraredList("", "", "", "", page, rows, mTaskCode);
    }


    private void openPopWindowInfraredProcessState() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_rb_detection_ground_process_statet, null);
        CustomPopWindow popWindow = ConstantViewUtils.openPopUpWindow(this, lyInfrared, view);
        mPresenter.infraredDepartProcessState(view, popWindow, this);
    }


    @OnClick({R.id.tv_infrared_process_state, R.id.detection_main_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 处理状态
            case R.id.tv_infrared_process_state:
                openPopWindowInfraredProcessState();
                break;
            // 搜索
            case R.id.detection_main_search:
                searchContent = mInfraredSearch.getText().toString().trim();
                mHandler.post(runnable);
            default:
                break;
        }
    }


    @Override
    public void onSuccess(Object bean, String resultMsg) {
        Log.e(TAG, "获取数据" + resultMsg);
        mInfraraedListBean = (InfraraedListBean) bean;
        if (mAdapter != null && mInfraraedListBean.getRecords() != null) {
            refreshLayout.finishRefresh(true);//结束刷新
            refreshLayout.finishLoadMore(true); //结束加载
            mRvMainInfrared.setAdapter(mAdapter);
            mAdapter.setData(mInfraraedListBean);
            if (!TextUtils.isEmpty(String.valueOf(mInfraraedListBean.getPages()))) {
                mTotalPages = mInfraraedListBean.getPages();
                if (mTotalPages == 1) {
                    refreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
                    refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
                }
            } else {
                mTotalPages = 1;
            }
        }
        if (resultMsg.equals("未找到数据！")) {
            ToastUtil.makeShortText(this, "暂无匹配内容，请重新输入搜索内容");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getInfraredList("", "", "", "", page, rows, mTaskCode);
    }

    @Override
    public void showDialog(String message) {
        showSvpDilog(this, message, false, null, null);
    }

    @Override
    public void hideDialog() {
        dismissSvpDilog(RepairConstantUtils.DIALOG_TIME);
    }

    @Override
    public void setGroundBottomText(int total, int handleCnt, int untreatedCnt) {
        tvInfraredBottomText.setText("共" + total + "条记录,未处理" + untreatedCnt + "个,已处理" + handleCnt + "个");
    }

    @Override
    public void setTaskType(int type) {
        taskType = type;
    }

    @Override
    public int geTaskType() {
        return taskType;
    }

    @Override
    public void setTaskCode(String taskCode) {
        mTaskCode = taskCode;
    }

    @Override
    public String getTaskCode() {
        return mTaskCode;
    }

    @Override
    public void onError(String result) {
        refreshLayout.finishRefresh(false);//结束刷新（刷新失败）
        refreshLayout.finishLoadMore(false); //结束加载（加载失败）
        ToastUtils.showToastSafe(this, result);
        Log.e(TAG, result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_basetitle_back:
                finish();
                break;
            default:
                break;
        }
    }


    // 红外测温详情页的跳转
    public void goInfraredDetailListInfo(Context context, String recodeCode, String title) {
        Intent intent = new Intent(context, InfraredDetailActivity.class);
        intent.putExtra("recodeCode", recodeCode);
        intent.putExtra("infraredTitle", title);
        context.startActivity(intent);
    }

    //主搜索
    private void startSearch(String lineName) {
        mPresenter.getInfraredList("", lineName, "", "", page, rows, mTaskCode);
    }
}


