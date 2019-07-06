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
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.detection.ground.GroundResistanceMainAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.deteect.GroundResistanceListBean;
import com.glens.jksd.network.view.detection.GroundResistancePresenter;
import com.glens.jksd.network.view.detection.GroundResistanceSearchView;
import com.glens.jksd.upimg.ToastUtil;
import com.glens.jksd.utils.ConstantViewUtils;
import com.glens.jksd.utils.LogUtil;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.view.CustomPopWindow;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 检测管理  接地电阻 主页面
 */
public class DetecionManagerGroundResistanceActivity extends BaseActivity implements View.OnClickListener, GroundResistanceSearchView {

    public static final String TAG = DetecionManagerGroundResistanceActivity.class.getSimpleName();

    @Bind(R.id.repair_task_search)
    EditText mGroundSearch;
    @Bind(R.id.rv_main_ground_resistance)
    RecyclerView mRecyclerViewMainGroundResistance;
    @Bind(R.id.tv_ground_bottom_display)
    TextView tvGroundBottomDisplay;
    @Bind(R.id.detection_main_search)
    TextView detectionMainSearch;
    @Bind(R.id.tv_ground_process_state)
    TextView tvGroundProcessState;
    @Bind(R.id.rl_ground_select)
    RelativeLayout rlGroundSelect;
    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private GroundResistanceMainAdapter mMainGroundAdapter;
    private GroundResistanceListBean mGroundResistanceListBean;
    private GroundResistancePresenter mGroundResistancePresenter;

    private String detectionTaskCode;
    private int mPage = 1;
    private int mRows = 10;
    public int taskType = 0;//当前选择的任务类型
    private String searchContent = "";
    private Handler mHandler;
    private int mTotalPages;

    Runnable runnable = () -> startSearch(searchContent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detecion_manager_ground_resistance);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mGroundResistancePresenter = new GroundResistancePresenter(DetecionManagerGroundResistanceActivity.this);
        mGroundResistancePresenter.onCreate();
        mGroundResistancePresenter.BindPresentView(DetecionManagerGroundResistanceActivity.this);
        mGroundResistancePresenter.getGroundResistanceList("", "", "", "", mPage, mRows, detectionTaskCode);
        mHandler = new Handler();
        mGroundSearch.addTextChangedListener(new TextWatcher() {
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
    }

    private void initView() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        detectionTaskCode = extras.getString(RepairConstantUtils.INFRARED_TASK_CODE);
        Log.e(TAG, "接地电阻的任务编码：" + detectionTaskCode);
        setTitleText("检测管理接地电阻任务");
        getLlBasetitleBack().setOnClickListener(this);
        SpannableString spannableString = new SpannableString("请输入电压、线路、杆号等搜索");
        mGroundSearch.setHint(spannableString);

        //RecyclerView的设置
        mRecyclerViewMainGroundResistance.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mMainGroundAdapter = new GroundResistanceMainAdapter(mGroundResistanceListBean, this);
        //点击事件
        mMainGroundAdapter.setOnItemClickListener((v, bean) -> {
            GroundResistanceListBean.RecordsBean recordsBean = (GroundResistanceListBean.RecordsBean) bean;
            Log.d(TAG, "changeToRepairTaskItemActivity: " + recordsBean.getRecodeCode());
            String title = Integer.parseInt(recordsBean.getLineVol()) / 1000 + "kV" + recordsBean.getLineName() + recordsBean.getTowerNo();
            goGroundDetailListInfo(this, recordsBean.getRecodeCode(), title);
        });

        // 滑动到底部不自动刷新
        refreshLayout.setEnableAutoLoadMore(false);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                mGroundResistancePresenter.getGroundResistanceList("", "", "", "", mPage, mRows, detectionTaskCode);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mPage < mTotalPages) {
                    ++mPage;
                    LogUtil.e("mPage:" + mPage);
                    mGroundResistancePresenter.getGroundResistanceList("", "", "", "", mPage, mRows, detectionTaskCode);
                } else {
                    //全部加载完成,没有数据了调用此方法
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }

            }
        });

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

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        Log.e(TAG, "获取数据" + resultMsg);
        refreshLayout.finishRefresh(true);//结束刷新
        refreshLayout.finishLoadMore(true); //结束加载
        mGroundResistanceListBean = (GroundResistanceListBean) bean;
        if (mMainGroundAdapter != null && mGroundResistanceListBean.getRecords() != null) {
            mRecyclerViewMainGroundResistance.setAdapter(mMainGroundAdapter);
            mMainGroundAdapter.setData(mGroundResistanceListBean);
            if (mGroundResistanceListBean.getPages() != null) {
                mTotalPages = Integer.parseInt(mGroundResistanceListBean.getPages());
                if (mTotalPages == 1) {
                    refreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
                    refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
                }
            }
            LogUtil.e("mTotalPages" + mTotalPages);
        }

//        if (mPage < mTotalPages) {
//            ++mPage;
//        } else {
//            //全部加载完成,没有数据了调用此方法
//            refreshLayout.finishLoadMoreWithNoMoreData();
//        }

        if (resultMsg.equals("未找到数据！")) {
            ToastUtil.makeShortText(this, "暂无匹配内容，请重新输入搜索内容");
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        mGroundResistancePresenter.getGroundResistanceList("", "", "", "", mPage, mRows, detectionTaskCode);
    }

    @Override
    public void showDialog(String message) {
        showSvpDilog(this, message, false, null, null);
    }

    @Override
    public void hideDialog() {
        dismissSvpDilog(RepairConstantUtils.DIALOG_TIME);
    }

    /**
     * 设置接地电阻bottom的记录数目
     *
     * @param total
     * @param handleCnt
     * @param untreatedCnt
     */
    @Override
    public void setGroundBottomText(int total, int handleCnt, int untreatedCnt) {
        tvGroundBottomDisplay.setText("共" + total + "条记录,未处理" + untreatedCnt + "个,已处理" + handleCnt + "个");
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
        detectionTaskCode = taskCode;
    }

    @Override
    public String getTaskCode() {
        return detectionTaskCode;
    }

    @Override
    public void onError(String result) {
        Log.e(TAG, "请求结果 " + result);
        refreshLayout.finishRefresh(false);//结束刷新（刷新失败）
        refreshLayout.finishLoadMore(false); //结束加载（加载失败）
    }


    // 接地电阻详情页的跳转
    public void goGroundDetailListInfo(Context context, String recodeCode, String title) {
        Intent intent = new Intent(context, GroundResistanceDetailActivity.class);
        intent.putExtra("recodeCode", recodeCode);
        intent.putExtra("groundTitle", title);
        context.startActivity(intent);
    }

    @OnClick({R.id.tv_ground_process_state, R.id.detection_main_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 处理状态
            case R.id.tv_ground_process_state:
                openPopWindowProcessState();
                break;
            // 搜索
            case R.id.detection_main_search:
                searchContent = mGroundSearch.getText().toString().trim();
                mHandler.post(runnable);
                break;
            default:
                break;
        }
    }

    private void openPopWindowProcessState() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_rb_detection_ground_process_statet, null);
        CustomPopWindow popWindow = ConstantViewUtils.openPopUpWindow(this, rlGroundSelect, view);
        mGroundResistancePresenter.groundProcessState(view, popWindow, DetecionManagerGroundResistanceActivity.this);
    }

    //主搜索
    private void startSearch(String lineName) {
        mGroundResistancePresenter.getGroundResistanceList("", lineName, "", "", mPage, mRows, detectionTaskCode);
    }

}
