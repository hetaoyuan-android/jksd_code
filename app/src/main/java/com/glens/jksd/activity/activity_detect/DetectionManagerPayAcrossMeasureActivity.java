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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.detection.measure.PayMeasureListAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.deteect.PayMeasureListBean;
import com.glens.jksd.network.view.detection.GroundResistancePresenter;
import com.glens.jksd.network.view.detection.GroundResistanceSearchView;
import com.glens.jksd.upimg.ToastUtil;
import com.glens.jksd.utils.ConstantViewUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.view.CustomPopWindow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 交跨测量的主界面
 */
public class DetectionManagerPayAcrossMeasureActivity extends BaseActivity implements View.OnClickListener, GroundResistanceSearchView {

    public static final String TAG = DetectionManagerPayAcrossMeasureActivity.class.getSimpleName();


    @Bind(R.id.rv_main_pay_measure)
    RecyclerView mRvMainPayMeasure;
    @Bind(R.id.tv_measure_bottom_text)
    TextView tvMeasureBottomText;
    @Bind(R.id.repair_task_search)
    EditText repairTaskSearch;
    @Bind(R.id.detection_main_search)
    TextView detectionMainSearch;
    @Bind(R.id.tv_pay_dispose_state)
    TextView tvPayDisposeState;
    @Bind(R.id.tv_pay_type)
    TextView tvPayType;
    @Bind(R.id.rl_pay_measure_line)
    RelativeLayout rlPayMeasureLine;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private EditText mPayMeasure;

    private int mPage = 1;
    private int mTotalPages;
    private int mRows = 10;
    private String mTaskCode;
    private String searchContent;
    private Handler mHandler;
    public int taskType = 0;//当前选择的任务类型

    private PayMeasureListBean mPayMeasureListBean;
    private GroundResistancePresenter mGroundResistancePresenter;
    private PayMeasureListAdapter mAdapter;

    Runnable runnable = () -> startSearch(searchContent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_manager_pay_across_measure);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        setTitleText(R.string.detect_pay_across_measure);
        getLlBasetitleBack().setOnClickListener(this);
        mPayMeasure = findViewById(R.id.repair_task_search);
        SpannableString spannableString = new SpannableString("请输入线路名称搜索");
        mPayMeasure.setHint(spannableString);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mTaskCode = extras.getString(RepairConstantUtils.INFRARED_TASK_CODE);

        //RecyclerView的设置
        mRvMainPayMeasure.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new PayMeasureListAdapter(mPayMeasureListBean, this);

        //点击事件
        mAdapter.setOnItemClickListener((v, bean) -> {
            PayMeasureListBean.RecordsBean recordsBean = (PayMeasureListBean.RecordsBean) bean;
            Log.d(TAG, "changeToRepairTaskItemActivity: " + recordsBean.getRecodeCode());
            String title = Integer.parseInt(recordsBean.getLineVol()) / 1000 + "kV" + recordsBean.getLineName() + recordsBean.getStartTowerNo() + "-" + recordsBean.getEndTowerNo();
            goPayMeasureDetailListInfo(this, recordsBean.getRecodeCode(), title);
        });

        // 搜索
        repairTaskSearch.addTextChangedListener(new TextWatcher() {
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

        // 下拉、上拉刷新操作
        // 滑动到底部不自动刷新
        refreshLayout.setEnableAutoLoadMore(false);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                mGroundResistancePresenter.getPayMeasureList("", "", "", null, "", "", "", mTaskCode, mPage, mRows);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mPage < mTotalPages) {
                    ++mPage;
                    mGroundResistancePresenter.getPayMeasureList("", "", "", null, "", "", "", mTaskCode, mPage, mRows);
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });

    }

    private void initData() {
        mHandler = new Handler();
        mGroundResistancePresenter = new GroundResistancePresenter(DetectionManagerPayAcrossMeasureActivity.this);
        mGroundResistancePresenter.onCreate();
        mGroundResistancePresenter.BindPresentView(this);
        mGroundResistancePresenter.getPayMeasureList("", "", "", null, "", "", "", mTaskCode, mPage, mRows);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_basetitle_back:
                finish();
                break;
        }
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        Log.e(TAG, "获取数据" + resultMsg);
        mPayMeasureListBean = (PayMeasureListBean) bean;
        if (mAdapter != null && mPayMeasureListBean.getRecords() != null) {
            mRvMainPayMeasure.setAdapter(mAdapter);
            mAdapter.setData(mPayMeasureListBean);
            if (!TextUtils.isEmpty(String.valueOf(mPayMeasureListBean.getPages()))) {
                mTotalPages = mPayMeasureListBean.getPages();
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
        mGroundResistancePresenter.getPayMeasureList("", "", "", null, "", "", "", mTaskCode, mPage, mRows);

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
        tvMeasureBottomText.setText("共" + total + "条记录,未处理" + untreatedCnt + "个,已处理" + handleCnt + "个");
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

    }

    // 接地电阻详情页的跳转
    public void goPayMeasureDetailListInfo(Context context, String recodeCode, String title) {
        Intent intent = new Intent(context, PayMeasureDetailActivity.class);
        intent.putExtra("recodeCode", recodeCode);
        intent.putExtra("measureTitle", title);
        context.startActivity(intent);
    }

    @OnClick({R.id.detection_main_search, R.id.tv_pay_dispose_state, R.id.tv_pay_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 搜索
            case R.id.detection_main_search:
                searchContent = repairTaskSearch.getText().toString().trim();
                mHandler.post(runnable);
                break;
            // 处理状态
            case R.id.tv_pay_dispose_state:
                openPopWindowMeasureState();
                break;
            // 交跨类型
            case R.id.tv_pay_type:
                openPopWindowMeasureDepart();
                break;
        }
    }

    // 搜索
    private void startSearch(String lineName) {
        mGroundResistancePresenter.getPayMeasureList("", lineName, "", null, "", "", "", mTaskCode, mPage, mRows);
    }


    // 处理状态
    private void openPopWindowMeasureState() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_rb_detection_ground_process_statet, null);
        CustomPopWindow popWindow = ConstantViewUtils.openPopUpWindow(this, rlPayMeasureLine, view);
        mGroundResistancePresenter.MeasureProcessState(view, popWindow, DetectionManagerPayAcrossMeasureActivity.this);
    }

    private void openPopWindowMeasureDepart() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_rb_detection_measure_depart, null);
        CustomPopWindow popWindow = ConstantViewUtils.openPopUpWindow(this, rlPayMeasureLine, view);
        mGroundResistancePresenter.MeasureDepartProcessState(view, popWindow, DetectionManagerPayAcrossMeasureActivity.this);
    }
}
