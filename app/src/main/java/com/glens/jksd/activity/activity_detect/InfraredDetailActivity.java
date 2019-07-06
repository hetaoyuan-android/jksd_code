package com.glens.jksd.activity.activity_detect;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.deteect.InfraredDetailBean;
import com.glens.jksd.network.view.detection.DetailPresenter;
import com.glens.jksd.network.view.detection.DetailView;
import com.glens.jksd.utils.RepairConstantUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfraredDetailActivity extends BaseActivity implements View.OnClickListener, DetailView {

    private static final String TAG = InfraredDetailActivity.class.getSimpleName();


    @Bind(R.id.tv_infrared_detail_name)
    TextView tvInfraredDetailName;
    @Bind(R.id.tv_infrared_detail_time)
    TextView tvInfraredDetailTime;
    @Bind(R.id.tv_infrared_detail_line_name)
    TextView tvInfraredDetailLineName;
    @Bind(R.id.tv_infrared_detail_tower_num)
    TextView tvInfraredDetailTowerNum;
    @Bind(R.id.spinner_department)
    Spinner spinnerDepartment;
    @Bind(R.id.tv_infrared_detail_env)
    EditText tvInfraredDetailEnv;
    @Bind(R.id.tv_infrared_detail_normal)
    EditText tvInfraredDetailNormal;
    @Bind(R.id.tv_infrared_detail_high_temp)
    EditText tvInfraredDetailHighTemp;
    @Bind(R.id.et_describe_content)
    EditText etDescribeContent;

    private String recodeCode;
    private DetailPresenter mPresenter;
    private InfraredDetailBean mBean;

    private String infraredTitle;

    private Spinner mSpinner;
    private ArrayAdapter<String> adapter;
    private String[] ctype = new String[]{"引流板", "导线接续管", "地线接续管 ", "地线放电间隙 ", "悬垂线夹", "管母接头", "其它 "};
    private int mSelectDartPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infrared_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    private void initView() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        recodeCode = extras.getString("recodeCode");
        infraredTitle = extras.getString("infraredTitle");
        Log.i("recodeCode", "" + recodeCode);
        setTitleText(infraredTitle);
        getLlBasetitleBack().setOnClickListener(this);
        mSpinner = findViewById(R.id.spinner_department);
        adapter = new ArrayAdapter<>(this, R.layout.item_spinner, ctype);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String string = (String) mSpinner.getSelectedItem();
                mSelectDartPosition = mSpinner.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initData() {
        mPresenter = new DetailPresenter(this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        if (!TextUtils.isEmpty(recodeCode)) {
            mPresenter.getInfraredDetailList(recodeCode);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_basetitle_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        mBean = (InfraredDetailBean) bean;
        if (mBean != null) {
            tvInfraredDetailName.setText(mBean.getDetectionTask().getExecutorName() == null ? "" : mBean.getDetectionTask().getExecutorName());
            tvInfraredDetailTime.setText(mBean.getCreateTime() == null ? "" : mBean.getCreateTime());
            tvInfraredDetailLineName.setText(mBean.getLineName() == null ? "" : mBean.getLineName());
            tvInfraredDetailTowerNum.setText(mBean.getTowerNo() == null ? "" : mBean.getTowerNo());
            spinnerDepartment.setSelection(mBean.getParts() - 1 != 0 ? mBean.getParts() - 1 : 0);
            tvInfraredDetailEnv.setText(mBean.getNormalTemperature() == null ? "" : mBean.getNormalTemperature());
            tvInfraredDetailNormal.setText(mBean.getAmbientTemperature() == null ? "" : mBean.getAmbientTemperature());
            tvInfraredDetailHighTemp.setText(mBean.getMaxTemperature() == null ? "" : mBean.getMaxTemperature());
            etDescribeContent.setText(mBean.getSiteDesc() == null ? "" : mBean.getSiteDesc());
            Log.i("SiteDesc", "" + mBean.getSiteDesc());
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
        Log.e(TAG, "请求结果 " + result);
    }
}
