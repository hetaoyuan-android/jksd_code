package com.glens.jksd.activity.activity_repair.stand_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.RepairTaskAdapter.PhotoGridViewAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.repair_bean.WaterTestDetailBean;
import com.glens.jksd.network.presenter.RepairTowerDetailPresenter;
import com.glens.jksd.network.view.BaseRepairView;
import com.glens.jksd.utils.PowerUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.RepairDataUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.utils.imagePick.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 憎水性实验
 */
public class WaterTestDetailActivity extends BaseActivity implements View.OnClickListener, BaseRepairView {
    public static final String TAG = WaterTestDetailActivity.class.getSimpleName();
    @Bind(R.id.tv_line_name)
    TextView tvLineName;
    @Bind(R.id.tv_tower_number)
    TextView tvTowerNumber;
    @Bind(R.id.tv_task_number)
    TextView tvTaskNumber;
    @Bind(R.id.tv_phase)
    TextView tvPhase;
    @Bind(R.id.et_repair_content)
    TextView etRepairContent;
    @Bind(R.id.tv_repair_pic_title)
    TextView tvRepairPicTitle;
    @Bind(R.id.grid_view)
    MyGridView mGridView;

    private RepairTowerDetailPresenter mPresenter;
    private String taskCode;
    private WaterTestDetailBean mLineCheckBean;
    private PhotoGridViewAdapter mPhotoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spary_detail);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        taskCode = getIntent().getStringExtra(RepairConstantUtils.TASKCODE);
        mPresenter = new RepairTowerDetailPresenter(WaterTestDetailActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        mPresenter.waterTestDetail(taskCode);
    }


    private void initView() {
        setTitleText("憎水性实验");
        getLlBasetitleBack().setOnClickListener(this);
        initNetData();
    }

    @Override
    public void onClick(View v) {
        finish();
    }


    @Override
    public void onSuccess(Object bean, String resultMsg) {
        mLineCheckBean = (WaterTestDetailBean) bean;
        Log.e(TAG, "获取数据" + bean.toString());
        if (mLineCheckBean != null) {
            tvLineName.setText(mLineCheckBean.getLineName() == null ? "" : mLineCheckBean.getLineName());
            tvTowerNumber.setText(mLineCheckBean.getTowerNo() == null ? "" : mLineCheckBean.getTowerNo());
            tvTaskNumber.setText(mLineCheckBean.getTaskCode() == null ? "" : mLineCheckBean.getTaskCode());
            tvPhase.setText(mLineCheckBean.getPhase() == null ? "" : RepairDataUtils.getPhase(mLineCheckBean.getPhase()));
            etRepairContent.setText(mLineCheckBean.getTextName() == null ? "" : mLineCheckBean.getTextName());
            setImageList(mLineCheckBean.getPicList());
        }
    }

    private void setImageList(List<WaterTestDetailBean.PicListBean> picList) {
        ArrayList<String> mGroupList = new ArrayList<>();

        for (WaterTestDetailBean.PicListBean picListBean : picList) {
            if (picListBean.getPicUrl() != null) {
                mGroupList.add(PowerUtils.getmBasePhoto() + picListBean.getPicUrl());
            }
        }
        if (mPhotoAdapter == null) {
            mPhotoAdapter = new PhotoGridViewAdapter(this, mGroupList);
        }
        mGridView.setAdapter(mPhotoAdapter);
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
    public void onError(String result) {
        ToastUtils.showToastSafe(this, result);
    }

}
