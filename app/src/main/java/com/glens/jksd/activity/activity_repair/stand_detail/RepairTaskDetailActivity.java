package com.glens.jksd.activity.activity_repair.stand_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.RepairTaskAdapter.PhotoGridViewAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.repair_bean.RepairTaskDetailBean;
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
 * 现场查勘详情
 */
public class RepairTaskDetailActivity extends BaseActivity implements View.OnClickListener, BaseRepairView {
    public static final String TAG = RepairTaskDetailActivity.class.getSimpleName();
    @Bind(R.id.tv_line_select)
    TextView tvLineSelect;
    @Bind(R.id.tv_tower_number)
    TextView tvTowerNumber;
    @Bind(R.id.tv_check_department)
    TextView tvCheckDepartment;
    @Bind(R.id.tv_check_create)
    TextView tvCheckCreate;
    @Bind(R.id.tv_repair_content_title)
    TextView tvRepairContentTitle;
    @Bind(R.id.tv_record_time)
    TextView tvRecordTime;
    @Bind(R.id.tv_repair_pic_title)
    TextView tvRepairPicTitle;
    @Bind(R.id.grid_view)
    MyGridView mGridView;

    private RepairTowerDetailPresenter mPresenter;
    private String taskCode;
    private RepairTaskDetailBean mInsulatorDetailBean;
    private PhotoGridViewAdapter mPhotoAdapter;
    private ArrayList<String> mGroupList;//整理后的图片字段


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_task_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setTitleText("现场查勘");
        getLlBasetitleBack().setOnClickListener(this);
        initData();
        initNetData();

    }

    private void initData() {
        taskCode = getIntent().getStringExtra(RepairConstantUtils.TASKCODE);
        mGroupList = new ArrayList<>();
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {

        mPresenter = new RepairTowerDetailPresenter(RepairTaskDetailActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        mPresenter.siteSurveyDetail(taskCode);
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        mInsulatorDetailBean = (RepairTaskDetailBean) bean;
        Log.e(TAG, "获取数据" + bean.toString());
        if (mInsulatorDetailBean != null) {
            tvLineSelect.setText(mInsulatorDetailBean.getLineName() == null ? "" : mInsulatorDetailBean.getLineName());
            tvTowerNumber.setText(mInsulatorDetailBean.getTowerNo() == null ? "" : mInsulatorDetailBean.getTowerNo());
            tvCheckCreate.setText(mInsulatorDetailBean.getSurveyManName() == null ? "" : mInsulatorDetailBean.getSurveyManName());
            tvCheckDepartment.setText(mInsulatorDetailBean.getSurveyUnitName() == null ? "" : mInsulatorDetailBean.getSurveyUnitName());
            tvRecordTime.setText(mInsulatorDetailBean.getRecordTime() == null ? "" : mInsulatorDetailBean.getRecordTime());
            setImageList(mInsulatorDetailBean.getPicList());

        }
    }

    private void setImageList(List<RepairTaskDetailBean.PicListBean> picList) {
        for (RepairTaskDetailBean.PicListBean picListBean : picList) {
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
    public void onClick(View v) {
        finish();
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
