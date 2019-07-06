package com.glens.jksd.activity.activity_repair.stand_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.RepairTaskAdapter.PhotoGridViewAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.repair_bean.RecordDetailBean;
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

public class RecordDetailActivity extends BaseActivity implements View.OnClickListener, BaseRepairView {
    public static final String TAG = RecordDetailActivity.class.getSimpleName();
    @Bind(R.id.tv_record_name)
    TextView tvRecordName;
    @Bind(R.id.tv_task_number)
    TextView tvTaskNumber;
    @Bind(R.id.tv_record_type)
    TextView tvRecordType;
    @Bind(R.id.tv_record_person)
    TextView tvRecordPerson;
    @Bind(R.id.tv_repair_content_title)
    TextView tvRepairContentTitle;
    @Bind(R.id.tv_record_time)
    TextView tvRecordTime;
    @Bind(R.id.tv_repair_pic_title)
    TextView tvRepairPicTitle;

    @Bind(R.id.iv_repair_record_trumpet)
    ImageView ivRepairRecordTrumpet;
    @Bind(R.id.pb_repair_record)
    ProgressBar pbRepairRecord;
    @Bind(R.id.cm_repair_record)
    Chronometer cmRepairRecord;
    @Bind(R.id.iv_repair_record_delete)
    ImageView ivRepairRecordDelete;
    @Bind(R.id.ry_repair_record)
    RelativeLayout ryRepairRecord;
    @Bind(R.id.grid_view)
    MyGridView mGridView;
    private RepairTowerDetailPresenter mPresenter;
    private PhotoGridViewAdapter mPhotoAdapter;
    private String taskCode;
    private RecordDetailBean mInsulatorDetailBean;
    private ArrayList<String> mGroupList;//整理后的图片字段


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        setTitleText("开收工录音");
        getLlBasetitleBack().setOnClickListener(this);
        initNetData();

    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        mGroupList = new ArrayList<>();
        taskCode = getIntent().getStringExtra(RepairConstantUtils.TASKCODE);
        mPresenter = new RepairTowerDetailPresenter(RecordDetailActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        mPresenter.repairSoundDetail(taskCode);
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        mInsulatorDetailBean = (RecordDetailBean) bean;
        Log.e(TAG, "获取数据" + bean.toString());
        if (mInsulatorDetailBean != null) {
            tvRecordName.setText(mInsulatorDetailBean.getSoundName() == null ? "" : mInsulatorDetailBean.getSoundName());
            tvTaskNumber.setText(mInsulatorDetailBean.getTaskCode() == null ? "" : mInsulatorDetailBean.getTaskCode());
            tvRecordType.setText(getSoundType(mInsulatorDetailBean.getSoundType()));
            tvRecordPerson.setText(mInsulatorDetailBean.getSoundRecordorName() == null ? "" : mInsulatorDetailBean.getSoundRecordorName());
            tvRecordTime.setText(mInsulatorDetailBean.getCreateTime() == null ? "" : mInsulatorDetailBean.getCreateTime());
            setImageList(mInsulatorDetailBean.getPicList());
        }
    }

    private void setImageList(List<RecordDetailBean.PicListBean> picList) {
        for (RecordDetailBean.PicListBean picListBean : picList) {
            if (picListBean.getPicUrl() != null) {
                mGroupList.add(PowerUtils.getmBasePhoto() + picListBean.getPicUrl());
            }
        }
        if (mPhotoAdapter == null) {
            mPhotoAdapter = new PhotoGridViewAdapter(this, mGroupList);
        }
        mGridView.setAdapter(mPhotoAdapter);
    }

    private String getSoundType(int soundType) {
        String type;
        switch (soundType) {
            case RepairConstantUtils.START_WORK:
                type = RepairConstantUtils.START_WORK_TEXT;
                break;
            case RepairConstantUtils.DOWN_WORK:
                type = RepairConstantUtils.DOWN_WORK_TEXT;
                break;
            default:
                type = "";
                break;
        }
        return type;
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
