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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.glens.jksd.PowerApplication;
import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.UpRepairPhotoBean;
import com.glens.jksd.bean.repair_bean.EquipmentBean;
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
 * 新增登杆检查
 */
public class RepairTowerAddActivity extends BaseActivity implements View.OnClickListener, RepairTowerAddView, AdapterView.OnItemClickListener {
    public static final String TAG = RepairTowerAddActivity.class.getSimpleName();
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.rg_repair_tower)
    RadioGroup mRgRepairTower;
    @Bind(R.id.tv_line_select)
    TextView mTvLineSelect;
    @Bind(R.id.tv_tower_select)
    TextView mTvTowerSelect;
    @Bind(R.id.tv_repair_content_title)
    TextView mTvRepairContentTitle;
    @Bind(R.id.et_repair_content)
    EditText mEtRepairContent;
    @Bind(R.id.repair_tower_one)
    RadioButton mRepairTowerOne;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;
    @Bind(R.id.gridview)
    MyGridView mGridView;
    @Bind(R.id.et_power_level)
    TextView etPowerLevel;
    @Bind(R.id.spinner_insulator)
    Spinner mSpinnerInsulator;
    @Bind(R.id.et_select_loc)
    EditText mEtSelectLoc;
    @Bind(R.id.ry_tower)
    RelativeLayout ryTower;


    private static final int REQUEST_TOWER_ADD_PIC = 1001;
    private static final int REQUEST_CHECK_PIC = 110;

    private RepairGetMapPresenter mPresenter;
    private String mTaskCode;
    private ArrayList<String> mPathList;
    private ArrayList<ImageItem> mImageList;
    private ImagePublishAdapter mImageListAdapter;
    private UpRepairPhotoBean photoBean;
    private String picCode;
    private boolean isShowDelete = false;
    private EquipmentBean.RecordsBean lineBean;
    private EquipmentBean.RecordsBean.TowerBean towerBean;
    private String lineName;
    private String lineNumber;
    private String powerLevel;
    private ArrayAdapter<String> adapter;
    private List<String> selectPhase;
    private String selectText = "A";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_tower_add);
        ButterKnife.bind(this);
        initView();
        initNetData();
        initImageData();
        setSpinner();
    }


    @Override
    public void onClick(View v) {
        finish();
    }

    private void initView() {
        mImageList = new ArrayList<>();
        mPathList = new ArrayList<>();
        setTitleText(R.string.repair_tower_check);
        getLlBasetitleBack().setOnClickListener(this);
        mRepairTaskStandardText.setText(R.string.repair_check_submit);
        ButterKnife.bind(this);
        mRgRepairTower.check(R.id.repair_tower_one);
    }

    private void setSpinner() {
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
        mPresenter = new RepairGetMapPresenter(RepairTowerAddActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(RepairTowerAddActivity.this);
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

                        lineName = Objects.requireNonNull(lineBean.getLineName());
                        lineNumber = Objects.requireNonNull(towerBean.getTowerNo());
                        powerLevel = Objects.requireNonNull(lineBean.getLineVol());
                        mTvLineSelect.setText(lineName);
                        mTvTowerSelect.setText(String.valueOf(lineNumber + "杆"));
                        etPowerLevel.setText(String.valueOf(powerLevel + "KV"));
                    }
                }

                break;
        }
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
        }else {
            ToastUtils.showToastSafe(this,resultMsg);
            dismissSvpDilog(0);
            finish();
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

    /**
     * 页面是否添加相别和电压等级选择框，接口参数需要
     * <p>
     * * lineId	必填	线路id
     * * lineName	必填	线路名称
     * * towerId	必填	杆塔id
     * * towerNo	必填	杆号
     * * taskCode	必填	任务编码
     * * lineVol	必填	电压等级
     * * phase	必填	相别 0:A 1:B 2:C
     * * textName	选填	内容描述
     * * picCodes	选填	上传照片编号(字符串多个用逗号分隔)
     */
    @Override
    public HashMap<String, Object> getDataMap() {
        Log.e(TAG, "taskCode " + mTaskCode);
        HashMap<String, Object> map = new HashMap<>();
        map.put("lineName", lineName);
        map.put("lineId", lineBean.getLineId());
        map.put("towerId", towerBean.getTowerId());
        map.put("taskCode", mTaskCode);
        map.put("towerNo", lineNumber);
        map.put("lineVol", powerLevel);
        map.put("phase", mPresenter.getStringPhase(selectText));
        map.put("textName", mEtRepairContent.getText().toString().trim());
        map.put("picCodes", picCode);
        return map;
    }

    @Override
    public void onError(String result) {
        Log.e(TAG, "请求结果 " + result);
    }

    @OnClick({R.id.tv_line_select, R.id.tv_tower_select, R.id.repair_task_standard_ry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_line_select:
                mPresenter.changeToSelect(mTaskCode, RepairConstantUtils.LINE_INSULATOR, RepairTowerAddActivity.this);
                break;
            case R.id.tv_tower_select:
                break;
            case R.id.repair_task_standard_ry:
                if (mPresenter.checkDataSet(this, lineName, lineNumber, selectText, mEtSelectLoc)) {
                    mPresenter.addTowerCheck();
                }

                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gridview:
                BaseEvent event = new BaseEvent();
                event.setEventType(RepairConstantUtils.NET_POST_DETECTION_IMAGE);
                ImagePickUtils.promptSelectImgDialog(event, position, this, mImageList, REQUEST_CHECK_PIC, 4, R.id.ry_tower);
                PowerApplication.setCurrentDataList(mImageList);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPathList.clear();
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
                mPresenter.upTowerPhoto(PowerApplication.getmDataList().get(0).sourcePath, mTaskCode, 1, "测试");
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
                    mPresenter.deleteTowerPhoto(picCode);
                }
                break;
            case RepairConstantUtils.DELETE_IMAGE:

                break;
            default:
                break;
        }
    }


//    private void deletePic() {
//        String picCode = PreferenceUtils.getString(this,"picCode","");
//        if (!TextUtils.isEmpty(picCode)){
//            Log.e(TAG, "删除结果 " + picCode);
//            isShowDelete = false;
//            mImageListAdapter.setShowDelete(false);
//            Log.e(TAG, "picCode " + picCode);
//            mPresenter.deleteTowerPhoto(picCode);
//        }
//    }
}
