package com.glens.jksd.activity.activity_detect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.adapter.detection.GroundDetailShowImgAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.deteect.GroundResistanceDetailBean;
import com.glens.jksd.bean.deteect.ShowImgUrlBean;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.detection.DetailPresenter;
import com.glens.jksd.network.view.detection.DetailView;
import com.glens.jksd.pictureselector.ImagePickerAdapter;
import com.glens.jksd.utils.PowerUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.lzy.imagepicker.view.GridSpacingItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GroundResistanceDetailActivity extends BaseActivity implements View.OnClickListener, DetailView, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    private static final String TAG = GroundResistanceDetailActivity.class.getSimpleName();

    @Bind(R.id.tv_ground_no_name)
    TextView tvGroundNoName;
    @Bind(R.id.tv_groung_no_line_time)
    TextView tvGroungNoLineTime;
    @Bind(R.id.tv_groung_no_line_name)
    TextView tvGroungNoLineName;
    @Bind(R.id.tv_no_ground_tower_num)
    TextView tvNoGroundTowerNum;
    @Bind(R.id.et_ground_a)
    EditText etGroundA;
    @Bind(R.id.et_ground_b)
    EditText etGroundB;
    @Bind(R.id.et_ground_c)
    EditText etGroundC;
    @Bind(R.id.et_ground_d)
    EditText etGroundD;
    @Bind(R.id.et_describe_content)
    EditText etDescribeContent;
    @Bind(R.id.recyclerView_ground_detail)
    RecyclerView recyclerViewGroundDetail;

    private GroundDetailShowImgAdapter imgAdapter;
    private ArrayList<ShowImgUrlBean> imgUrlList = new ArrayList<>();


    private String recodeCode;
    private DetailPresenter mPresenter;
    private GroundResistanceDetailBean mBean;
    private String groundTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground_resistance_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    private void initView() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        recodeCode = extras.getString("recodeCode");
        groundTitle = extras.getString("groundTitle");
        Log.i("recodeCode", "" + recodeCode);
        setTitleText(groundTitle);
        getLlBasetitleBack().setOnClickListener(this);
    }

    private void initData() {
        mPresenter = new DetailPresenter(this);
        mPresenter.onCreate();
        mPresenter.BindPresentView((PresentView) GroundResistanceDetailActivity.this);
        if (!TextUtils.isEmpty(recodeCode)) {
            mPresenter.getGroundResistanceDetailList(recodeCode);
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
        mBean = (GroundResistanceDetailBean) bean;
        tvGroundNoName.setText(mBean.getDetectionTask().getExecutorName() == null ? "" : mBean.getDetectionTask().getExecutorName());
        tvGroungNoLineTime.setText(mBean.getCreateTime() == null ? "" : mBean.getCreateTime());
        tvGroungNoLineName.setText(mBean.getLineName() == null ? "" : mBean.getLineName());
        tvNoGroundTowerNum.setText(mBean.getTowerNo() == null ? "" : mBean.getTowerNo());
        etGroundA.setText(mBean.getErAValue() == null ? "" : mBean.getErAValue());
        etGroundB.setText(mBean.getErBValue() == null ? "" : mBean.getErBValue());
        etGroundC.setText(mBean.getErCValue() == null ? "" : mBean.getErCValue());
        etGroundD.setText(mBean.getErDValue() == null ? "" : mBean.getErDValue());
        etDescribeContent.setText(mBean.getSiteDesc() == null ? "" : mBean.getSiteDesc());

        for (int i = 0; i < mBean.getPicList().size(); i++) {
            ShowImgUrlBean showImgUrlBean = new ShowImgUrlBean();
            // showImgUrlBean.setImageUrl(PowerUtils.mBasePhoto +  mBean.getPicList().get(i).getPicUrl());
            showImgUrlBean.setImageUrl(PowerUtils.mBasePhoto + "system/getFileStream?path=" + mBean.getPicList().get(i).getPicUrl());
            Log.i("GroundsetImageUrl","" + PowerUtils.mBasePhoto + mBean.getPicList().get(i).getPicUrl());
            imgUrlList.add(showImgUrlBean);
        }
        recyclerViewGroundDetail.setHasFixedSize(true);
        recyclerViewGroundDetail.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        recyclerViewGroundDetail.addItemDecoration(new GridSpacingItemDecoration(3, 20, false));
        imgAdapter = new GroundDetailShowImgAdapter(this, imgUrlList);
        recyclerViewGroundDetail.setAdapter(imgAdapter);
        imgAdapter.setOnItemClickListener(new GroundDetailShowImgAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String data) {
                //跳转显示详细图片的Activity
                Intent intent = new Intent(GroundResistanceDetailActivity.this, ImageShowActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

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

    @Override
    public void onItemClick(View view, int position) {

    }
}
