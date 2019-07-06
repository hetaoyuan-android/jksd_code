package com.glens.jksd.activity.activity_repair.stand_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.RepairTaskAdapter.PhotoAdapter;
import com.glens.jksd.adapter.RepairTaskAdapter.PhotoGridViewAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.repair_bean.InformationDetailBean;
import com.glens.jksd.network.presenter.RepairTowerDetailPresenter;
import com.glens.jksd.network.view.BaseRepairView;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.utils.imagePick.view.MyGridView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InformationDetailActivity extends BaseActivity implements View.OnClickListener, BaseRepairView {
    public static final String TAG = InformationDetailActivity.class.getSimpleName();
    @Bind(R.id.tv_information_number)
    TextView tvInformationNumber;
    @Bind(R.id.tv_information_time)
    TextView tvInformationTime;
    @Bind(R.id.tv_repair_content_title)
    TextView tvRepairContentTitle;
    @Bind(R.id.tv_record_number)
    TextView tvRecordNumber;
    @Bind(R.id.tv_repair_pic_title)
    TextView tvRepairPicTitle;
    @Bind(R.id.grid_view)
    MyGridView mGridView;

    private RepairTowerDetailPresenter mPresenter;
    private String taskCode;
    private InformationDetailBean mInsulatorDetailBean;
    private PhotoGridViewAdapter mPhotoAdapter;
    private ArrayList<String> mGroupList;//整理后的图片字段

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        mGroupList = new ArrayList<>();
        taskCode = getIntent().getStringExtra(RepairConstantUtils.DATUM_CODE);
        mPresenter = new RepairTowerDetailPresenter(InformationDetailActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        mPresenter.informationDetail(taskCode);
    }


    private void initView() {
        setTitleText("检修资料");
        getLlBasetitleBack().setOnClickListener(this);
        initNetData();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        mInsulatorDetailBean = (InformationDetailBean) bean;
        Log.e(TAG, "获取数据" + bean.toString());
        if (mInsulatorDetailBean != null) {
            tvInformationNumber.setText(mInsulatorDetailBean.getDatumCode() == null ? "" : mInsulatorDetailBean.getDatumCode());
            tvInformationTime.setText(mInsulatorDetailBean.getCreateTime() == null ? "" : mInsulatorDetailBean.getCreateTime());
            tvRecordNumber.setText(mInsulatorDetailBean.getCreatorId() == null ? "" : mInsulatorDetailBean.getCreatorId());

            mGridView.setAdapter(new PhotoAdapter(this, mInsulatorDetailBean.getPicList()));
        }
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
