package com.glens.jksd.activity.activity_repair.stand_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.RepairTaskAdapter.PhotoGridViewAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.RepairTowerDetailBean;
import com.glens.jksd.network.presenter.RepairTowerDetailPresenter;
import com.glens.jksd.network.view.BaseRepairView;
import com.glens.jksd.utils.PowerUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.utils.imagePick.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 登杆检查详情
 */
public class RepairTowerDetailActivity extends BaseActivity implements View.OnClickListener, BaseRepairView {
    public static final String TAG = RepairTowerDetailActivity.class.getSimpleName();
    @Bind(R.id.tv_line_select)
    TextView mTvLineSelect;
    @Bind(R.id.tv_tower_number)
    TextView mTvTowerNumber;
    @Bind(R.id.tv_tower_create)
    TextView mTvTowerCreate;
    @Bind(R.id.tv_tower_time)
    TextView mTvTowerTime;
    @Bind(R.id.tv_repair_content_title)
    TextView mTvRepairContentTitle;
    @Bind(R.id.et_repair_content)
    TextView mEtRepairContent;
    @Bind(R.id.tv_repair_pic_title)
    TextView mTvRepairPicTitle;
    @Bind(R.id.grid_view)
    MyGridView mGridView;

    private RepairTowerDetailPresenter mPresenter;
    private RepairTowerDetailBean mRepairTowerDetailBean;
    private String taskCode;
    private PhotoGridViewAdapter mPhotoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_tower_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setTitleText(R.string.tower_check_detail);
        getLlBasetitleBack().setOnClickListener(this);
        initNetData();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        taskCode = getIntent().getStringExtra(RepairConstantUtils.TASKCODE);
        mPresenter = new RepairTowerDetailPresenter(RepairTowerDetailActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        mPresenter.repairTowerDetail(taskCode);
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        mRepairTowerDetailBean = (RepairTowerDetailBean) bean;
        Log.e(TAG, "获取数据" + bean.toString());
        if (mRepairTowerDetailBean != null) {
            mTvLineSelect.setText(mRepairTowerDetailBean.getLineName() == null ? "" : mRepairTowerDetailBean.getLineName());
            mTvTowerNumber.setText(mRepairTowerDetailBean.getTowerNo() == null ? "" : mRepairTowerDetailBean.getTowerNo());
            mTvTowerCreate.setText(mRepairTowerDetailBean.getCreateByName() == null ? "" : mRepairTowerDetailBean.getCreateByName());
            mTvTowerTime.setText(mRepairTowerDetailBean.getCreateTime() == null ? "" : mRepairTowerDetailBean.getCreateTime());
            mEtRepairContent.setText(mRepairTowerDetailBean.getTextName() == null ? "" : mRepairTowerDetailBean.getTextName());
            setImageList(mRepairTowerDetailBean.getPicList());
        }
    }

    private void setImageList(List<RepairTowerDetailBean.PicListBean> picList) {
        ArrayList<String> mGroupList = new ArrayList<>();

        for (RepairTowerDetailBean.PicListBean picListBean : picList) {
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
