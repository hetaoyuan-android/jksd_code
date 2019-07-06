package com.glens.jksd.activity.activity_repair.stand_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.repair_bean.DefectDetailBean;
import com.glens.jksd.network.presenter.RepairTowerDetailPresenter;
import com.glens.jksd.network.view.BaseRepairView;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消缺工作详情
 */
public class RepairDefectDetailActivity extends BaseActivity implements View.OnClickListener, BaseRepairView {
    public static final String TAG = RepairDefectDetailActivity.class.getSimpleName();
    @Bind(R.id.rb_repair_task_bill)
    RadioButton mRbRepairTaskBill;
    @Bind(R.id.rb_repair_task_check)
    RadioButton mRbRepairTaskCheck;
    @Bind(R.id.rg_repair)
    RadioGroup mRgRepair;
    @Bind(R.id.tv_line_select)
    TextView mTvPowerLevel;
    @Bind(R.id.tv_tower_number)
    TextView mTvLineName;
    @Bind(R.id.tv_tower_create)
    TextView mTvLineNumber;
    @Bind(R.id.tv_tower_time)
    TextView mTvContent;
    @Bind(R.id.tv_repair_content_title)
    TextView mTvRepairContentTitle;
    @Bind(R.id.tv_repair_content_title_text)
    TextView mTvRepairContentTitleText;
    @Bind(R.id.tv_repair_pic_title)
    TextView mTvRepairPicTitle;
//    @Bind(R.id.iv_image1)
//    ImageView mIvImage1;
//    @Bind(R.id.image_remove1)
//    FrameLayout mImageRemove1;
//    @Bind(R.id.ry_image_layout1)
//    RelativeLayout mRyImageLayout1;
//    @Bind(R.id.iv_image2)
//    ImageView mIvImage2;
//    @Bind(R.id.image_remove2)
//    FrameLayout mImageRemove2;
//    @Bind(R.id.image_show_layout2)
//    RelativeLayout mImageShowLayout2;
//    @Bind(R.id.iv_image3)
//    ImageView mIvImage3;
//    @Bind(R.id.image_remove3)
//    FrameLayout mImageRemove3;
//    @Bind(R.id.image_show_layout3)
//    RelativeLayout mImageShowLayout3;
//    @Bind(R.id.iv_image4)
//    ImageView mIvImage4;
//    @Bind(R.id.image_remove4)
//    FrameLayout mImageRemove4;
//    @Bind(R.id.image_show_layout4)
//    RelativeLayout mImageShowLayout4;
    @Bind(R.id.ly_defect_bill)
    LinearLayout mLyDefectBill;
    @Bind(R.id.tv_line_name)
    TextView mTvLine;
    @Bind(R.id.tv_tower_num)
    TextView mTvTowerNum;
    @Bind(R.id.tv_defect_number)
    TextView mTvDefectNumber;
    @Bind(R.id.tv_task_number)
    TextView mTvTaskNumber;
    @Bind(R.id.tv_record_number)
    TextView mTvRecordNumber;
    @Bind(R.id.ly_defect_fixed)
    LinearLayout mLyDefectFixed;
    private RepairTowerDetailPresenter mPresenter;
    private String taskCode;
    private DefectDetailBean mDefectDetailBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_defect_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setTitleText("消缺工作");
        getLlBasetitleBack().setOnClickListener(this);
        initNetData();
         mRbRepairTaskBill.setChecked(true);

    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        taskCode = getIntent().getStringExtra(RepairConstantUtils.TASKCODE);
        mPresenter = new RepairTowerDetailPresenter(RepairDefectDetailActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        mPresenter.repairTaskDetail(taskCode);
    }


    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        mDefectDetailBean = (DefectDetailBean) bean;
        Log.e(TAG, "获取数据" + bean.toString());
        if (mDefectDetailBean != null) {
            mTvPowerLevel.setText(mDefectDetailBean.getDefect().getDydj() == null ? "" : mDefectDetailBean.getDefect().getDydj());
            mTvLineName.setText(mDefectDetailBean.getDefect().getDzorxlmc() == null ? "" : mDefectDetailBean.getDefect().getDzorxlmc());
            mTvLineNumber.setText(mDefectDetailBean.getDefect().getTowerNo() == null ? "" : mDefectDetailBean.getDefect().getTowerNo());
            mTvContent.setText(mDefectDetailBean.getDefect().getQxnr() == null ? "" : mDefectDetailBean.getDefect().getQxnr());
            mTvRepairContentTitleText.setText(mDefectDetailBean.getTextName() == null ? "" : mDefectDetailBean.getTextName());
//            Glide.with(this).load(setImageUrl(mDefectDetailBean.getPicList(), 0)).into(mIvImage1);


            mTvLine.setText(mDefectDetailBean.getLineName() == null ? "" : mDefectDetailBean.getLineName());
            mTvTowerNum.setText(mDefectDetailBean.getTowerNo() == null ? "" : mDefectDetailBean.getTowerNo());
            mTvDefectNumber.setText(mDefectDetailBean.getDefectCode() == null ? "" : mDefectDetailBean.getDefectCode());
            mTvTaskNumber.setText(mDefectDetailBean.getTaskCode() == null ? "" : mDefectDetailBean.getTaskCode());
            mTvRecordNumber.setText(mDefectDetailBean.getRecordCode() == null ? "" : mDefectDetailBean.getRecordCode());
        }
    }

    private String setImageUrl(List<DefectDetailBean.PicListBean> picList, int position) {
        if (picList != null && picList.size() > 0) {
            return "http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201504/06/A7D85D839E613CA1DA829206903EAAFA.jpg";
        } else {

            return null;
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

    @OnClick({R.id.rb_repair_task_bill, R.id.rb_repair_task_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_repair_task_bill:
                mLyDefectBill.setVisibility(View.VISIBLE);
                mLyDefectFixed.setVisibility(View.GONE);
                break;
            case R.id.rb_repair_task_check:
                mLyDefectBill.setVisibility(View.GONE);
                mLyDefectFixed.setVisibility(View.VISIBLE);
                break;
        }
    }
}
