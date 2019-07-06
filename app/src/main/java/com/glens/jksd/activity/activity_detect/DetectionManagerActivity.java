package com.glens.jksd.activity.activity_detect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.detection.DetectionListAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.ResistanceBean;
import com.glens.jksd.network.presenter.DetectionManagerPresenter;
import com.glens.jksd.network.view.DetectionManagerView;
import com.glens.jksd.utils.ConstantViewUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.view.CustomPopWindow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 检测管理页面的主页面
 */
public class DetectionManagerActivity extends BaseActivity implements View.OnClickListener, DetectionManagerView {
    public static final String TAG = DetectionManagerActivity.class.getSimpleName();
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.repair_task_search)
    EditText mRepairTaskSearch;
    @Bind(R.id.tv_send_time)
    TextView tvSendTime;
    @Bind(R.id.tv_task_type)
    TextView tvTaskType;
    @Bind(R.id.detection_line)
    View mDetectionLine;
    @Bind(R.id.tv_content_display)
    TextView tvContentDisplay;
    @Bind(R.id.detection_main_search)
    TextView detectionMainSearch;
    @Bind(R.id.rv_detection)
    RecyclerView mRvDetectionMain;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private DetectionManagerPresenter mDetectionManagerPresenter;
    private DetectionListAdapter detectionListAdapter;
    private ResistanceBean mResistanceBean;
    private int mPage = 1;
    private int mTotalPages;
    private int mPageSize = 10;
    public int taskType = 0;//当前选择的任务类型
    public String mTaskName = "";
    public String mTaskTime = "";
    private String task = "";
    private Handler mHandler;
    private List<String> mPathList;


    Runnable runnable = () -> startStandardSearch(0, task, mTaskTime);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_manager);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initData() {
        mDetectionManagerPresenter = new DetectionManagerPresenter(DetectionManagerActivity.this);
        mDetectionManagerPresenter.onCreate();
        mDetectionManagerPresenter.BindPresentView(DetectionManagerActivity.this);
        mDetectionManagerPresenter.getDetectionListInfo("", "", 0, mPage, mPageSize, DetectionManagerActivity.this);
        mHandler = new Handler();
        mPathList = new ArrayList<>();
        mRepairTaskSearch.addTextChangedListener(new TextWatcher() {
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
        setTitleText(R.string.detect_manager);
        getLlBasetitleBack().setOnClickListener(this);
        setImageRightVisibity(false);
        mRvDetectionMain.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        detectionListAdapter = new DetectionListAdapter(mResistanceBean, this);
        detectionListAdapter.setOnItemClickListener((v, bean) -> {
            Log.d(TAG, "changeToDetectionItemActivity: ");
            ResistanceBean.RecordBean recordsBean = (ResistanceBean.RecordBean) bean;
            changeToDetectionItemActivity(recordsBean);
        });
        // 滑动到底部不自动刷新
        refreshLayout.setEnableAutoLoadMore(false);
        //取消内容不满一页时开启上拉加载功能
//        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                mDetectionManagerPresenter.getDetectionListInfo("", "", 0, mPage, mPageSize, DetectionManagerActivity.this);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mPage < mTotalPages) {
                    ++mPage;
                    mDetectionManagerPresenter.getDetectionListInfo("", "", 0, mPage, mPageSize, DetectionManagerActivity.this);
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDetectionManagerPresenter.getDetectionListInfo(mTaskName, mTaskTime, taskType, mPage, mPageSize, DetectionManagerActivity.this);

    }

    private void changeToDetectionItemActivity(ResistanceBean.RecordBean recordsBean) {
        switch (recordsBean.getTaskType()) {
            // 接地电阻
            case RepairConstantUtils.DETECTION_GROUND_RESISTANCE:
                Intent intent = new Intent();
                intent.setClass(DetectionManagerActivity.this, DetecionManagerGroundResistanceActivity.class);
                intent.putExtra(RepairConstantUtils.INFRARED_TASK_CODE, recordsBean.getTaskCode());
                startActivity(intent);
                Log.e(TAG, "接地电阻任务编码" + recordsBean.getTaskCode());
                break;
            // 红外测温
            case RepairConstantUtils.DETECTION_INFRARDE:
                Intent intent1 = new Intent(DetectionManagerActivity.this, DetectionManagerInfraredActivity.class);
                intent1.putExtra(RepairConstantUtils.INFRARED_TASK_CODE, recordsBean.getTaskCode());
                Log.e(TAG, "红外测温任务编码" + recordsBean.getTaskCode());
                startActivity(intent1);
                break;
            // 交跨测量
            case RepairConstantUtils.DETECTION_PAY_ACROSS_MEASURE:
                Intent intent2 = new Intent();
                intent2.setClass(DetectionManagerActivity.this, DetectionManagerPayAcrossMeasureActivity.class);
                intent2.putExtra(RepairConstantUtils.INFRARED_TASK_CODE, recordsBean.getTaskCode());
                startActivity(intent2);
                Log.e(TAG, "红外测温任务编码" + recordsBean.getTaskCode());
                break;
            default:
                Log.e("DetectionListAdapter", "非正常工单参数类型");
                break;
        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }


    @OnClick({R.id.tv_send_time, R.id.tv_task_type, R.id.detection_main_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_time:
                mDetectionManagerPresenter.openTimrPicker(DetectionManagerActivity.this);
                break;
            case R.id.tv_task_type:
                openPopWindow();
//                mDetectionManagerPresenter.isStartRecord(this);//拍照
                break;
            // 搜索
            case R.id.detection_main_search:
                task = mRepairTaskSearch.getText().toString();
                mHandler.post(runnable);
                break;
        }
    }


    private void openPopWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_rb_detection_three, null);
        CustomPopWindow popWindow = ConstantViewUtils.openPopUpWindow(this, mDetectionLine, view);
        mDetectionManagerPresenter.handleLogic(view, popWindow, DetectionManagerActivity.this);
    }


    private void startStandardSearch(int taskType, String taskName, String mTaskTime) {
        mDetectionManagerPresenter.getDetectionListInfo(taskName, mTaskTime, taskType, 0, 10, DetectionManagerActivity.this);
    }


    @Override
    public void showToast(String info) {

    }

    @Override
    public void changeToItemActivity() {

    }

    @Override
    public void onSuccess(ResistanceBean bean, String resultMsg) {
        Log.e(TAG, "获取数据" + bean.toString());
        refreshLayout.finishRefresh(true);//结束刷新
        refreshLayout.finishLoadMore(true); //结束加载
        dismissSvpDilog(1000);
        mResistanceBean = bean;
        if (detectionListAdapter != null && bean.getRecord() != null) {
            mRvDetectionMain.setAdapter(detectionListAdapter);
            detectionListAdapter.setData(bean);
            if (!TextUtils.isEmpty(String.valueOf(mResistanceBean.getPages()))) {
                mTotalPages = mResistanceBean.getPages();
                if (mTotalPages == 1) {
                    refreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
                    refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
                }
            } else {
                mTotalPages = 1;
            }
        }
//        if (mPage < mTotalPages) {
//            ++mPage;
//        } else {
//            refreshLayout.finishLoadMoreWithNoMoreData();
//        }

    }

    @Override
    public void setBottomViewText(int total, int infrared, int measure, int resistance) {
        tvContentDisplay.setText(String.valueOf("共" + total + "条记录,红外测温" + infrared + "个,交跨测量" + measure +
                "个,接地电阻" + resistance + "个"));
    }

    @Override
    public void setTimeTaskSearch(String time) {
        mTaskTime = time;
        startStandardSearch(taskType, mTaskName, mTaskTime);
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
    public void onError(String result) {
        refreshLayout.finishRefresh(false);//结束刷新（刷新失败）
        refreshLayout.finishLoadMore(false); //结束加载（加载失败）
        ToastUtils.showToastSafe(this, result);
        Log.e(TAG, result);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "requestCode=" + requestCode + " resultCode=" + resultCode);
        if (requestCode == RepairConstantUtils.DETECTION_PHOTO_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // TODO: 2019/5/27 上传图片
                if (data != null) {

                } else {
                    Log.e(TAG, "data为空");
                }

            }
        }
    }
}
