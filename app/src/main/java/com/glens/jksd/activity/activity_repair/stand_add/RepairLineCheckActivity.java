package com.glens.jksd.activity.activity_repair.stand_add;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.glens.jksd.PowerApplication;
import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.UpRepairPhotoBean;
import com.glens.jksd.bean.repair_bean.EquipmentBean;
import com.glens.jksd.bean.repair_bean.TowerBean;
import com.glens.jksd.network.presenter.RepairGetMapPresenter;
import com.glens.jksd.network.view.RepairTowerAddView;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.utils.baseEvent.BaseEvent;
import com.glens.jksd.utils.imagePick.adapter.ImagePublishAdapter;
import com.glens.jksd.utils.imagePick.model.ImageItem;
import com.glens.jksd.utils.imagePick.util.ImagePickUtils;
import com.glens.jksd.utils.imagePick.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增走线检查
 */
public class RepairLineCheckActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, RepairTowerAddView {
    public static final String TAG = RepairLineCheckActivity.class.getSimpleName();
    private boolean isShowDelete = false;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.tv_check_line)
    TextView tvCheckLine;
    @Bind(R.id.tv_check_line_start)
    TextView tvCheckLineStart;
    @Bind(R.id.tv_check_line_end)
    TextView tvCheckLineEnd;
    @Bind(R.id.tv_line_level)
    TextView tvLineLevel;
    @Bind(R.id.spinner_insulator)
    Spinner mSpinnerInsulator;
    @Bind(R.id.tv_check_locate)
    EditText mEtCheckLocate;
    @Bind(R.id.tv_repair_content_title)
    TextView tvRepairContentTitle;
    @Bind(R.id.et_repair_content)
    EditText etRepairContent;
    @Bind(R.id.gridview)
    MyGridView mGridView;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout repairTaskStandardRy;

    private static final int REQUEST_CHECK_PIC = 112;
    private UpRepairPhotoBean photoBean;
    private String picCode;
    private ImagePublishAdapter mImageListAdapter;
    private RepairGetMapPresenter mPresenter;
    private String mTaskCode;
    private ArrayList<String> mPathList;
    private ArrayList<ImageItem> mImageList;//中转list
    private String selectText;
    private ArrayAdapter<String> adapter;
    private List<String> selectPhase;
    private String mLineId;
    private String lineName;
    private String lineNumber;
    private String powerLevel;
    private EquipmentBean.RecordsBean lineBean;
    private EquipmentBean.RecordsBean.TowerBean towerBean;
    private String towerStart;
    private String towerEnd;
    private TowerBean.RecordsBean mSingleTowerBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_line_check);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        mImageList = new ArrayList<>();
        mPathList = new ArrayList<>();
        setTitleText(R.string.repair_line_check);
        getLlBasetitleBack().setOnClickListener(this);
        mRepairTaskStandardText.setText(R.string.repair_check_submit);
        initImageData();
        initNetData();
        setSpinnerData();
    }


    private void initImageData() {
        mGridView.setHaveScrollbar(false);
        mGridView.setOnItemClickListener(this);
        mGridView.setOnItemLongClickListener((parent, view, position, id) -> {
            if (isShowDelete) {
                isShowDelete = false;
            } else {
                isShowDelete = true;
            }
            mImageListAdapter.setShowDelete(isShowDelete);
            return true;
        });
        mImageListAdapter = new ImagePublishAdapter(this, mImageList, 4);
        mGridView.setAdapter(mImageListAdapter);
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        mTaskCode = PreferenceUtils.getString(this, "taskCode", "");
        mPresenter = new RepairGetMapPresenter(RepairLineCheckActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(RepairLineCheckActivity.this);

    }

    private void setSpinnerData() {
        selectPhase = new ArrayList<>();
        selectPhase.add("A");
        selectPhase.add("B");
        selectPhase.add("C");
        adapter = new ArrayAdapter<>(this, R.layout.item_spinner, selectPhase);
        mSpinnerInsulator.setAdapter(adapter);
        mSpinnerInsulator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectText = (String) mSpinnerInsulator.getSelectedItem();
                int selectPosition = mSpinnerInsulator.getSelectedItemPosition();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gridview:
                BaseEvent event = new BaseEvent();
                event.setEventType(RepairConstantUtils.NET_POST_DETECTION_IMAGE);
                ImagePickUtils.promptSelectImgDialog(event, position, RepairLineCheckActivity.this, mImageList, REQUEST_CHECK_PIC, 4, R.id.fy_line_check);
                PowerApplication.setCurrentDataList(mImageList);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }


    @OnClick({R.id.tv_check_line, R.id.tv_check_line_start, R.id.tv_check_line_end, R.id.repair_task_standard_ry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_check_line:
                mPresenter.changeToSelect(mTaskCode, RepairConstantUtils.LINE_INSULATOR, RepairLineCheckActivity.this);
                break;
            case R.id.tv_check_line_start:
                mPresenter.checkToSelectPowerList(mLineId, mTaskCode,RepairConstantUtils.TOWER_SINGLE,
                        RepairConstantUtils.START_TOWER, RepairLineCheckActivity.this);
                break;
            case R.id.tv_check_line_end:
                mPresenter.checkToSelectPowerList(mLineId, mTaskCode, RepairConstantUtils.TOWER_SINGLE,
                        RepairConstantUtils.END_TOWER,RepairLineCheckActivity.this);
                break;
            case R.id.repair_task_standard_ry:
                towerStart = tvCheckLineStart.getText().toString().trim();
                towerEnd = tvCheckLineEnd.getText().toString().trim();
                if (mPresenter.checkLineDataSet(this, towerStart, towerEnd, lineName, lineNumber, selectText, mEtCheckLocate)) {
                    mPresenter.addLineCheck();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "requestCode=" + requestCode + " resultCode=" + resultCode);
        switch (requestCode) {
            case REQUEST_CHECK_PIC:
                if (mImageList.size() < 4 && resultCode == -1 && !TextUtils.isEmpty(ImagePickUtils.getImagePath())) {
                    ImageItem item = new ImageItem();
                    item.sourcePath = ImagePickUtils.getImagePath();
                    mImageList.add(item);
                    mImageListAdapter.setData(mImageList);
                }
                break;
            case RepairConstantUtils.SELECT_EQUIPMENT:
                if (resultCode == RepairConstantUtils.SELECT_EQUIPMENT_RESULT) {
                    if (data != null) {
                        lineBean = (EquipmentBean.RecordsBean) Objects.requireNonNull(data.getExtras()).getSerializable(RepairConstantUtils.LINEBEAN);
                        towerBean = (EquipmentBean.RecordsBean.TowerBean) Objects.requireNonNull(data.getExtras()).getSerializable(RepairConstantUtils.TOWERBEAN);

                        mLineId = Objects.requireNonNull(lineBean.getLineId());
                        lineName = Objects.requireNonNull(lineBean.getLineName());
                        lineNumber = Objects.requireNonNull(towerBean.getTowerNo());
                        powerLevel = Objects.requireNonNull(lineBean.getLineVol());
                        tvCheckLine.setText(lineName);
                        tvLineLevel.setText(String.valueOf(powerLevel + "KV"));
                    }
                }
                break;
            case RepairConstantUtils.START_TOWER:
                if (resultCode == RepairConstantUtils.SELECT_EQUIPMENT_RESULT) {
                    if (data != null) {
                        mSingleTowerBean = (TowerBean.RecordsBean) Objects.requireNonNull(data.getExtras()).getSerializable(RepairConstantUtils.TOWERBEAN);

                        tvCheckLineStart.setText(String.valueOf(Objects.requireNonNull(mSingleTowerBean).getTowerNo() + "杆"));
                    }
                }
                break;
            case RepairConstantUtils.END_TOWER:
                if (resultCode == RepairConstantUtils.SELECT_EQUIPMENT_RESULT) {
                    if (data != null) {
                        mSingleTowerBean = (TowerBean.RecordsBean) Objects.requireNonNull(data.getExtras()).getSerializable(RepairConstantUtils.TOWERBEAN);

                        tvCheckLineEnd.setText(String.valueOf(Objects.requireNonNull(mSingleTowerBean).getTowerNo() + "杆"));
                    }
                }
                break;
        }
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        super.onEventMainThread(event);
        switch (event.getEventType()) {
            case RepairConstantUtils.NET_POST_DETECTION_IMAGE:
                //更新图片
                mImageList.addAll(PowerApplication.getmDataList());
                mImageListAdapter.setData(mImageList);
                Log.e("路径", Objects.requireNonNull(PowerApplication.getmDataList().get(0)).sourcePath);
                mPresenter.upLineCheckPhoto(PowerApplication.getmDataList().get(0).sourcePath, mTaskCode, "测试");
                break;

            case RepairConstantUtils.NET_OMCONSTRUCTION_PHASEMATERIAL_SUCCESS:
                finish();
                break;

            case RepairConstantUtils.NET_OMCOMPLETION_PHASEMATERIAL_SUCCESS:
                finish();
                break;
            case RepairConstantUtils.DELETE_IMAGE_SUCCESS:
                if (!TextUtils.isEmpty(picCode)) {
                    isShowDelete = false;
                    mImageListAdapter.setShowDelete(false);
                    Log.e(TAG, "picCode " + picCode);
                    mPresenter.deleteLineCheckPhoto(picCode);
                }
                break;
            case RepairConstantUtils.DELETE_IMAGE:

                break;
            default:
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public HashMap<String, Object> getDataMap() {
        Log.e(TAG, "taskCode " + mTaskCode);
        HashMap<String, Object> map = new HashMap<>();

        map.put("lineName", lineName);
        map.put("lineId", lineBean.getLineId());
        map.put("towerId", towerBean.getTowerId());
        map.put("taskCode", mTaskCode);
        map.put("startTowerNo", towerStart);
        map.put("endTowerNo", towerEnd);
        map.put("startTowerId", tvCheckLineEnd.getText().toString().trim());
        map.put("endTowerId", tvCheckLineEnd.getText().toString().trim());
        map.put("towerNo", lineNumber);
        map.put("lineVol", lineBean.getLineVol());
        map.put("loc", mEtCheckLocate.getText().toString().trim());
        map.put("phase", mPresenter.getStringPhase(selectText));
        if (!TextUtils.isEmpty(etRepairContent.getText().toString().trim())) {
            map.put("textName", etRepairContent.getText().toString().trim());
        }
        if (picCode != null) {
            map.put("picCodes", picCode);
        }

        Log.e("传输数据", map.toString());
        return map;
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        Log.e(TAG, "获取数据" + resultMsg);
        if (TextUtils.equals(resultMsg, RepairConstantUtils.UP_PIC)) {
            photoBean = (UpRepairPhotoBean) bean;
            if (photoBean != null) {
                if (!TextUtils.isEmpty(picCode)) {
                    picCode = picCode + "," + photoBean.getRecords().get(0).getPicCode();
                } else {
                    picCode = photoBean.getRecords().get(0).getPicCode();
                }
            }
        } else {
            ToastUtils.showToastSafe(this, resultMsg);
            dismissSvpDilog(0);
            finish();
        }
    }

    public void onError(String result) {
        Log.e(TAG, "请求结果 " + result);
    }

    @Override
    public void showDialog(String message) {
        showSvpDilog(this, message, false, null, null);
    }

    @Override
    public void hideDialog() {
        dismissSvpDilog(RepairConstantUtils.DIALOG_TIME);
    }

}
