package com.glens.jksd.activity.activity_repair.stand_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.RepairTaskAdapter.PhotoGridViewAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.repair_bean.RtvSprayDetailBean;
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
 * RTV喷涂
 */
public class SprayDetailActivity extends BaseActivity implements View.OnClickListener, BaseRepairView {
    public static final String TAG = SprayDetailActivity.class.getSimpleName();
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
    private RtvSprayDetailBean mRepairTowerDetailBean;
    private String taskCode;
    private PhotoGridViewAdapter mPhotoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defect_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setTitleText("RTV喷涂");
        getLlBasetitleBack().setOnClickListener(this);
        initNetData();
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        taskCode = getIntent().getStringExtra(RepairConstantUtils.TASKCODE);
        mPresenter = new RepairTowerDetailPresenter(SprayDetailActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        mPresenter.sprayDetail(taskCode);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        mRepairTowerDetailBean = (RtvSprayDetailBean) bean;
        Log.e(TAG, "获取数据" + bean.toString());
        if (mRepairTowerDetailBean != null) {
            mTvLineSelect.setText(mRepairTowerDetailBean.getLineName() == null ? "" : mRepairTowerDetailBean.getLineName());
            mTvTowerNumber.setText(mRepairTowerDetailBean.getTowerNo() == null ? "" : mRepairTowerDetailBean.getTowerNo());
            mTvTowerCreate.setText(mRepairTowerDetailBean.getSprayManName() == null ? "" : mRepairTowerDetailBean.getSprayManName());
            mTvTowerTime.setText(mRepairTowerDetailBean.getSprayUnitName() == null ? "" : mRepairTowerDetailBean.getSprayUnitName());
            mEtRepairContent.setText(mRepairTowerDetailBean.getTextName() == null ? "" : mRepairTowerDetailBean.getTextName());
           setImageList(mRepairTowerDetailBean.getPicList());
        }
    }

    private void setImageList(List<RtvSprayDetailBean.PicListBean> picList) {
        ArrayList<String> mGroupList = new ArrayList<>();

        for (RtvSprayDetailBean.PicListBean picListBean : picList) {
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
