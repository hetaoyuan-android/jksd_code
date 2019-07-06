package com.glens.jksd.activity.activity_repair.stand_add;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_repair.RepairGroundPhotoActivity;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.network.view.BaseRepairView;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 新增接地线管理(4种状态)
 */
public class RepairAddGroundActivity extends BaseActivity implements View.OnClickListener, BaseRepairView {
    public static final String TAG = RepairAddGroundActivity.class.getSimpleName();
    @Bind(R.id.tv_ground_message)
    TextView mTvGroundMessage;
    @Bind(R.id.ry_repair_ground_add)
    RelativeLayout mRyRepairGroundAdd;
    @Bind(R.id.tv_repair_arrow_size)
    TextView mTvRepairArrowSize;
    @Bind(R.id.tv_repair_arrow_text)
    TextView mTvRepairArrowText;
    @Bind(R.id.tv_repair_arrow_two)
    TextView mTvRepairArrowTwo;
    @Bind(R.id.tv_repair_arrow_text_two)
    TextView mTvRepairArrowTextTwo;
    @Bind(R.id.tv_repair_arrow_three)
    TextView mTvRepairArrowThree;
    @Bind(R.id.tv_repair_arrow_text_three)
    TextView mTvRepairArrowTextThree;
    @Bind(R.id.tv_repair_arrow_four)
    TextView mTvRepairArrowFour;
    @Bind(R.id.tv_repair_arrow_text_four)
    TextView mTvRepairArrowTextFour;

    @Bind(R.id.tv_ground_accept_person)
    TextView mTvGroundAcceptPerson;
    @Bind(R.id.tv_ground_accept_time)
    TextView mTvGroundAcceptTime;
    @Bind(R.id.ly_ground_message)
    LinearLayout lyGroundMessage;
    @Bind(R.id.tv_repair_arrow_hangup)
    TextView tvRepairArrowHangup;
    @Bind(R.id.tv_repair_arrow_text_hangup)
    TextView tvRepairArrowTextHangup;
    @Bind(R.id.tv_repair_arrow_dismantle)
    TextView tvRepairArrowDismantle;
    @Bind(R.id.tv_repair_arrow_text_dismantle)
    TextView tvRepairArrowTextDismantle;
    @Bind(R.id.tv_repair_arrow_return)
    TextView tvRepairArrowReturn;
    @Bind(R.id.tv_repair_arrow_text_return)
    TextView tvRepairArrowTextReturn;


    private String mTaskCode;
    private int mGroundType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGroundType = getIntent().getIntExtra(RepairConstantUtils.TASKTYPE, -1);
        switch (mGroundType) {
            case RepairConstantUtils.REPAIR_ONE:
                setContentView(R.layout.activity_repair_add_ground);
                acceptFindViewById();
                break;
            case RepairConstantUtils.REPAIR_TWO:
                setContentView(R.layout.activity_ground_replace);
                setCheckConfigure();
                break;
            case RepairConstantUtils.REPAIR_THREE:
                setContentView(R.layout.activity_ground_replace);
                break;
            case RepairConstantUtils.REPAIR_FOUR:
                setContentView(R.layout.activity_ground_replace);
                break;
            case RepairConstantUtils.REPAIR_FIVE:
                setContentView(R.layout.activity_ground_replace);
                break;
            default:
                setContentView(R.layout.activity_repair_add_ground);
                break;

        }
        ButterKnife.bind(this);
        initView();
    }

    private void setCheckConfigure() {
        mTvGroundMessage.setText("去检查");
    }


    private void acceptFindViewById() {

        mRyRepairGroundAdd = findViewById(R.id.ry_repair_ground_add);
        mTvRepairArrowSize = findViewById(R.id.tv_repair_arrow_size);
        mTvRepairArrowText = findViewById(R.id.tv_repair_arrow_text);
        mTvRepairArrowTwo = findViewById(R.id.tv_repair_arrow_two);
        mTvRepairArrowTextTwo = findViewById(R.id.tv_repair_arrow_text_two);
        mTvGroundMessage = findViewById(R.id.tv_ground_message);
        mTvGroundMessage.setText("去领用");
        mTvRepairArrowText.setText("检查");
        mTvGroundMessage.setOnClickListener(v -> {
            Intent intent = new Intent(RepairAddGroundActivity.this, RepairGroundPhotoActivity.class);
            startActivity(intent);
        });
    }

    private void initView() {
        setTitleText(R.string.repair_ground_manage_add);
        mTaskCode = PreferenceUtils.getString(this, "taskCode", "");
        getLlBasetitleBack().setOnClickListener(this);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    //1、item点击四种布局状态传参
    //2、在一个接地线activity里面根据参数加载不同的布局
    //3、根据布局圆环展示点击事件页面

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        Log.e(TAG, "获取数据" + resultMsg);
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
        Log.e(TAG, "请求结果 " + result);
    }


}
