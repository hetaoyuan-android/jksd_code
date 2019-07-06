package com.glens.jksd.activity.activity_repair.stand_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.RepairTaskAdapter.PhotoGridViewAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.repair_bean.GroundDetailBean;
import com.glens.jksd.network.presenter.RepairTowerDetailPresenter;
import com.glens.jksd.network.view.BaseRepairView;
import com.glens.jksd.utils.PowerUtils;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.utils.imagePick.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GroundDetailActivity extends BaseActivity implements View.OnClickListener, BaseRepairView {
    public static final String TAG = GroundDetailActivity.class.getSimpleName();
    @Bind(R.id.tv_line_select)
    TextView tvLineSelect;
    @Bind(R.id.tv_tower_number)
    TextView tvTowerNumber;
    @Bind(R.id.tv_insulator_locate)
    TextView tvInsulatorLocate;
    @Bind(R.id.tv_insulator_create)
    TextView tvInsulatorCreate;
    @Bind(R.id.tv_repair_content_title)
    TextView tvRepairContentTitle;
    @Bind(R.id.tv_record_number)
    TextView tvRecordNumber;
    @Bind(R.id.tv_repair_pic_title)
    TextView tvRepairPicTitle;
    @Bind(R.id.grid_view)
    MyGridView mGridView;

    private RepairTowerDetailPresenter mPresenter;
    private String mTaskCode;
    private GroundDetailBean mInsulatorDetailBean;
    private PhotoGridViewAdapter mPhotoAdapter;
    private ArrayList<String> mGroupList;//整理后的图片字段

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground_detail);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
//        taskCode = getIntent().getStringExtra(RepairConstantUtils.TASKCODE);
        mTaskCode = PreferenceUtils.getString(this, "taskCode", "");
        mPresenter = new RepairTowerDetailPresenter(GroundDetailActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        mPresenter.groundrDetail(mTaskCode);
    }


    private void initView() {
        setTitleText("接地线管理");
        getLlBasetitleBack().setOnClickListener(this);
        initNetData();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        mInsulatorDetailBean = (GroundDetailBean) bean;
        Log.e(TAG, "获取数据" + bean.toString());
        if (mInsulatorDetailBean != null) {
            tvLineSelect.setText(mInsulatorDetailBean.getGroudWireName() == null ? "" : mInsulatorDetailBean.getGroudWireName());
            tvTowerNumber.setText(mInsulatorDetailBean.getRecordCode() == null ? "" : mInsulatorDetailBean.getRecordCode());
            tvInsulatorLocate.setText(mInsulatorDetailBean.getCreateTime() == null ? "" : mInsulatorDetailBean.getCheckTime());
            tvInsulatorCreate.setText(mInsulatorDetailBean.getDemolitionPerson() == null ? "" : mInsulatorDetailBean.getDemolitionPerson());
            tvRecordNumber.setText(mInsulatorDetailBean.getRecordCode() == null ? "" : mInsulatorDetailBean.getRecordCode());
            setImageList(mInsulatorDetailBean.getPicList());
        }
    }

    private void setImageList(List<GroundDetailBean.PicListBean> picList) {
        mGroupList = new ArrayList<>();
        for (GroundDetailBean.PicListBean picListBean : picList) {
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
