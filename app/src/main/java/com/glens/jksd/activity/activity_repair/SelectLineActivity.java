package com.glens.jksd.activity.activity_repair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_repair.stand_add.AddInsulatorCleanActivity;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.repair_bean.EquipmentBean;
import com.glens.jksd.bean.repair_bean.TowerBean;
import com.glens.jksd.network.presenter.RepairGetMapPresenter;
import com.glens.jksd.network.view.RepairTowerAddView;
import com.glens.jksd.utils.RepairConstantUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 选择线路或者杆塔
 */
public class SelectLineActivity extends BaseActivity implements View.OnClickListener, RepairTowerAddView {
    public static final String TAG = AddInsulatorCleanActivity.class.getSimpleName();
    @Bind(R.id.rv_refresh_line)
    ListView mListView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private String mTaskCode;
    private String mLineId;
    private String mSelectType;
    private int mTaskType;
    private RepairGetMapPresenter mPresenter;
    private ArrayList<String> mGroupList;
    private ArrayAdapter<String> adapter;
    private TowerBean mTowerBean;
    private EquipmentBean mEquipmentBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_equipment);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        mTaskCode = getIntent().getStringExtra(RepairConstantUtils.TASKCODE);
        mLineId = getIntent().getStringExtra(RepairConstantUtils.LINE_ID);
        mSelectType = getIntent().getStringExtra(RepairConstantUtils.SELECT_TYPE);

        mTaskType = getIntent().getIntExtra(RepairConstantUtils.TASKTYPE, -1);
        mPresenter = new RepairGetMapPresenter(SelectLineActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(SelectLineActivity.this);

        checkToCreate(mSelectType);

    }

    private void checkToCreate(String mSelectType) {
        switch (mSelectType){
            case RepairConstantUtils.LINE_SINGLE:
                mPresenter.equipmentList(mTaskCode, mTaskType);
                break;
            case RepairConstantUtils.TOWER_SINGLE:
                mPresenter.lineCheckList(mTaskCode, mTaskType,mLineId);
                break;
        }
    }


    private void initView() {
        setTitleText(R.string.repair_tower_select);
        mGroupList = new ArrayList<>();
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            checkSendData(position);
        });
        initNetData();
    }

    private void checkSendData(int position) {
        switch (mSelectType){
            case RepairConstantUtils.LINE_SINGLE:
                // TODO: 2019/6/24
                sendLineData(mEquipmentBean.getRecords().get(position));
                break;
            case RepairConstantUtils.TOWER_SINGLE:
                sendTowerBean(mTowerBean.getRecords().get(position));
                break;
        }
    }

    private void sendTowerBean(TowerBean.RecordsBean recordsBean) {
        Intent intent = new Intent();
        intent.putExtra(RepairConstantUtils.TOWERBEAN, recordsBean);
        setResult(RepairConstantUtils.SELECT_EQUIPMENT_RESULT, intent);
        finish();
    }

    private void sendLineData(EquipmentBean.RecordsBean recordsBean) {
        Intent intent = new Intent();
        intent.putExtra(RepairConstantUtils.LINEBEAN, recordsBean);
        setResult(RepairConstantUtils.SELECT_EQUIPMENT_RESULT, intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        Log.e(TAG, "获取数据" + resultMsg);
        
        switch (mSelectType){
            case RepairConstantUtils.LINE_SINGLE:
                mEquipmentBean = (EquipmentBean) bean;
                setLineData(mEquipmentBean.getRecords());
                break;
            case RepairConstantUtils.TOWER_SINGLE:
                mTowerBean = (TowerBean) bean;
                setListData(mTowerBean.getRecords());
                break;
        }
        if (adapter==null){
            adapter = new ArrayAdapter<>(this,R.layout.item_single_text,mGroupList);
        }
        mListView.setAdapter(adapter);
    }

    private void setLineData(List<EquipmentBean.RecordsBean> records) {
        if (records != null && records.size() > 0) {
            for (int i = 0; i < records.size(); i++) {
                EquipmentBean.RecordsBean item = records.get(i);
                mGroupList.add(item.getLineName());
            }
        }
    }
    

    private void setListData(List<TowerBean.RecordsBean> mTowerBean) {
        if (mTowerBean != null && mTowerBean.size() > 0) {
            for (int i = 0; i < mTowerBean.size(); i++) {
                TowerBean.RecordsBean item = mTowerBean.get(i);
                mGroupList.add(item.getTowerNo());
            }
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
    public HashMap<String, Object> getDataMap() {
        return null;
    }

    @Override
    public void onError(String result) {
        Log.e(TAG, "请求结果 " + result);
    }
}
