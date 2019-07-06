package com.glens.jksd.activity.activity_detect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.detection.GroundDetailShowImgAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.deteect.PayMeasureDetailBean;
import com.glens.jksd.bean.deteect.ShowImgUrlBean;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.detection.DetailPresenter;
import com.glens.jksd.network.view.detection.DetailView;
import com.glens.jksd.utils.PowerUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;
import com.lzy.imagepicker.view.GridSpacingItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PayMeasureDetailActivity extends BaseActivity implements View.OnClickListener, DetailView {

    private static final String TAG = GroundResistanceDetailActivity.class.getSimpleName();

    @Bind(R.id.tv_no_measure_name)
    TextView tvNoMeasureName;
    @Bind(R.id.tv_no_measure_time)
    TextView tvNoMeasureTime;
    @Bind(R.id.tv_no_measure_line_name)
    TextView tvNoMeasureLineName;
    @Bind(R.id.tv_no_measure_tower_num)
    TextView tvNoMeasureTowerNum;
    @Bind(R.id.iv_part_arrows)
    ImageView ivPartArrows;
    @Bind(R.id.et_no_measure_env)
    EditText etNoMeasureEnv;
    @Bind(R.id.tv_no_measure_distance)
    EditText tvNoMeasureDistance;
    @Bind(R.id.et_describe_content)
    EditText etDescribeContent;
    @Bind(R.id.spinner_pay_measure_department_show)
    Spinner spinnerPayMeasureDepartmentShow;
    @Bind(R.id.recyclerView_pay_measure_detail)
    RecyclerView recyclerViewPayMeasureDetail;

    private String recodeCode;
    private DetailPresenter mPresenter;
    private PayMeasureDetailBean mBean;
    private String payMeasureTitle;

    private Spinner mSpinner;
    private ArrayAdapter<String> adapter;
    private String[] ctype = new String[]{"道路", "铁路", "重要通道 ", "水系 "};
    private int mSelectDartPosition;


    private GroundDetailShowImgAdapter imgAdapter;
    private ArrayList<ShowImgUrlBean> imgUrlList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_measure);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        recodeCode = extras.getString("recodeCode");
        payMeasureTitle = extras.getString("measureTitle");
        Log.i("recodeCode", "" + recodeCode);
        setTitleText(payMeasureTitle);
        getLlBasetitleBack().setOnClickListener(this);
        mSpinner = findViewById(R.id.spinner_pay_measure_department_show);
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
        mPresenter.BindPresentView((PresentView) PayMeasureDetailActivity.this);
        if (!TextUtils.isEmpty(recodeCode)) {
            mPresenter.getPayMeasureDetailList(recodeCode);
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
        mBean = (PayMeasureDetailBean) bean;
        if (mBean != null) {
            tvNoMeasureName.setText(mBean.getDetectionTask().getExecutorName() == null ? "" : mBean.getDetectionTask().getExecutorName());
            tvNoMeasureTime.setText(mBean.getCreateTime() == null ? "" : mBean.getCreateTime());
            tvNoMeasureLineName.setText(mBean.getLineName() == null ? "" : mBean.getLineName());
            tvNoMeasureTowerNum.setText((mBean.getStartTowerNo() + "-" + mBean.getEndTowerNo()) == null ? "" : (mBean.getStartTowerNo() + "-" + mBean.getEndTowerNo()));
            etNoMeasureEnv.setText(mBean.getAmbientTemperature() == null ? "" : mBean.getAmbientTemperature());
            mSpinner.setSelection(mBean.getCrossType() - 1);
            Log.e("getAmbientTemperature", "" + mBean.getAmbientTemperature());
            tvNoMeasureDistance.setText(mBean.getCrossDis() == null ? "" : mBean.getCrossDis());
            etDescribeContent.setText(mBean.getSiteDesc() == null ? "" : mBean.getSiteDesc());

            for (int i = 0; i < mBean.getPicList().size(); i++) {
                ShowImgUrlBean showImgUrlBean = new ShowImgUrlBean();
                showImgUrlBean.setImageUrl(PowerUtils.mBasePhoto + "system/getFileStream?path=" + mBean.getPicList().get(i).getPicUrl());
                imgUrlList.add(showImgUrlBean);
            }
            recyclerViewPayMeasureDetail.setHasFixedSize(true);
            recyclerViewPayMeasureDetail.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));

            recyclerViewPayMeasureDetail.addItemDecoration(new GridSpacingItemDecoration(3, 20, false));
            imgAdapter = new GroundDetailShowImgAdapter(this, imgUrlList);
            recyclerViewPayMeasureDetail.setAdapter(imgAdapter);
            imgAdapter.setOnItemClickListener(new GroundDetailShowImgAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(String data) {
                    //跳转显示详细图片的Activity
                    Intent intent = new Intent(PayMeasureDetailActivity.this, ImageShowActivity.class);
                    intent.putExtra("data",data);
                    startActivity(intent);
                }
            });
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
