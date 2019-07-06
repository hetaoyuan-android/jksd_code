package com.glens.jksd.activity.activity_repair;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.glens.jksd.R;
import com.glens.jksd.adapter.RepairTaskAdapter.RepairTowerSelectAdapter;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.repair_bean.EquipmentBean;
import com.glens.jksd.network.presenter.RepairGetMapPresenter;
import com.glens.jksd.network.view.RepairTowerAddView;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.view.CustomExpandListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 线路杆塔选择
 */
public class RepairSelectActivity extends BaseActivity implements View.OnClickListener, RepairTowerAddView {
    public static final String TAG = RepairSelectActivity.class.getSimpleName();
    @Bind(R.id.lv_repair_tower)
    CustomExpandListView mLvRepairTower;

    private List<EquipmentBean.RecordsBean> mGroupList;
    private List<List<EquipmentBean.RecordsBean.TowerBean>> mChildMap;
    private RepairTowerSelectAdapter mAdapter;
    private String mTaskCode;
    private int mTaskType;
    private RepairGetMapPresenter mPresenter;
    private EquipmentBean mEquipmentBean;
    private String mTowerName;
    private String mTowerNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_select);
        ButterKnife.bind(this);
        initView();
    }
//
    @Override
    public void onClick(View v) {
        finish();
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        mTaskCode = getIntent().getStringExtra(RepairConstantUtils.TASKCODE);
        mTaskType = getIntent().getIntExtra(RepairConstantUtils.TASKTYPE, -1);
        mPresenter = new RepairGetMapPresenter(RepairSelectActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(RepairSelectActivity.this);
        mPresenter.equipmentList(mTaskCode, mTaskType);
        mLvRepairTower.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            mTowerName = mGroupList.get(groupPosition).getLineName();
            mTowerName = mGroupList.get(groupPosition).getLineId();
            mTowerNumber = mChildMap.get(groupPosition).get(childPosition).getTowerNo();
            Log.e(TAG,"线路名称 "+mTowerName + "线路id "+ mTowerNumber);
            sendData(mGroupList.get(groupPosition),mChildMap.get(groupPosition).get(childPosition));
            return false;
        });
    }

    private void sendData(EquipmentBean.RecordsBean recordsBean, EquipmentBean.RecordsBean.TowerBean towerBean) {
        Intent intent = new Intent();
        intent.putExtra(RepairConstantUtils.LINEBEAN, recordsBean);
        intent.putExtra(RepairConstantUtils.TOWERBEAN, towerBean);
        setResult(RepairConstantUtils.SELECT_EQUIPMENT_RESULT, intent);
        finish();
    }

    private void initView() {
        setTitleText(R.string.repair_tower_select);
        mGroupList = new ArrayList<>();
        mChildMap = new ArrayList<>();

        initNetData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        Log.e(TAG, "获取数据" + resultMsg);
        mEquipmentBean = (EquipmentBean) bean;
        setDataFormat(mEquipmentBean.getRecords());

    }

    private void setDataFormat(List<EquipmentBean.RecordsBean> records) {
        if (records != null && records.size() > 0) {
            for (int i = 0; i < records.size(); i++) {
                EquipmentBean.RecordsBean item = records.get(i);
                mGroupList.add(item);
                List<EquipmentBean.RecordsBean.TowerBean> itemList = item.getTower();
                mChildMap.add(itemList);
                Log.d("sda", "==dates" + mChildMap.toString());
            }
            mAdapter = new RepairTowerSelectAdapter(this,this, mGroupList, mChildMap);
            mLvRepairTower.setAdapter(mAdapter);
            mLvRepairTower.setGroupIndicator(null);

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
