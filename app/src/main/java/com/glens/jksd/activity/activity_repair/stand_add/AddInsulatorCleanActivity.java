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
 * 新增绝缘子清扫
 */
public class AddInsulatorCleanActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, RepairTowerAddView {
    public static final String TAG = AddInsulatorCleanActivity.class.getSimpleName();
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout repairTaskStandardRy;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.et_repair_equipment)
    TextView etRepairEquipment;
    @Bind(R.id.et_power_level)
    TextView etPowerLevel;
    @Bind(R.id.tv_select_loc)
    EditText tvSelectLoc;
    @Bind(R.id.et_check_person)
    EditText etCheckPerson;
    @Bind(R.id.tv_repair_content_title)
    TextView tvRepairContentTitle;
    @Bind(R.id.et_repair_content)
    EditText etRepairContent;
    @Bind(R.id.gridview)
    MyGridView mGridView;
    @Bind(R.id.spinner_insulator)
    Spinner mSpinnerInsulator;
    @Bind(R.id.repair_clean_before)
    RadioButton repairCleanBefore;
    @Bind(R.id.repair_clean_after)
    RadioButton repairCleanAfter;
    @Bind(R.id.rg_repair_clean)
    RadioGroup rgRepairClean;
    @Bind(R.id.ry_insulator)
    RelativeLayout ryInsulator;

    private static final int REQUEST_CHECK_PIC = 111;
    private boolean isShowDelete = false;
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
    private String lineName;
    private String lineNumber;
    private String powerLevel;
    private String photoType = RepairConstantUtils.CLEAN_BEFORE;
    private HashMap<String, ArrayList<ImageItem>> totalMap;//存储不同类型照片数据
    private EquipmentBean.RecordsBean lineBean;
    private EquipmentBean.RecordsBean.TowerBean towerBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_insulator_clean);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 电压等级随杆塔数据自带  addInsulatorClear
     * 相别自选
     */

    private void initView() {
        setTitleText(R.string.repair_instruction_clean);
        getLlBasetitleBack().setOnClickListener(this);
        mRepairTaskStandardText.setText(R.string.repair_check_submit);
        ButterKnife.bind(this);
        rgRepairClean.check(R.id.repair_clean_before);
        mImageList = new ArrayList<>();
        mPathList = new ArrayList<>();
        selectPhase = new ArrayList<>();
        totalMap = new HashMap<>();
        selectPhase.add("A");
        selectPhase.add("B");
        selectPhase.add("C");

        initImageData();
        initNetData();
        setSpinnerData();
    }

    private void setSpinnerData() {
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

        // TODO: 2019/6/24 多条目选择图片功能
        /**
         *
         * 1、第一次初始化数据，设置默认item，切换项目时，存储之前的数据
         *
         */
//        rgRepairClean.setOnCheckedChangeListener((group, checkedId) -> {
//            switch (checkedId) {
//                case R.id.repair_clean_before:
//                    setPhotoData(RepairConstantUtils.CLEAN_BEFORE);
//                    break;
//                case R.id.repair_clean_after:
//                    setPhotoData(RepairConstantUtils.CLEAN_AFTER);
//                    break;
//
//            }
//        });

    }

    private void setPhotoData(String currentType) {
        keepPhotoList(photoType, mImageList);
        photoType = currentType;
        mImageList.clear();
        mImageList = totalMap.get(photoType);
        if (mImageListAdapter!=null){
            mImageListAdapter.setData(mImageList);
        }else {
            mImageListAdapter = new ImagePublishAdapter(this, mImageList, 4);
            mGridView.setAdapter(mImageListAdapter);
        }
    }

    //1、清扫前保存数据照片
    //2、清扫后清空照片
    //3、回到清扫前恢复照片

    private void keepPhotoList(String photoType, ArrayList<ImageItem> mImageList) {
        if (mImageList.size() > 0) {
            ArrayList<ImageItem> photoList = totalMap.get(photoType);
            if (photoList != null && photoList.size() > 0) {
                photoList.addAll(mImageList);//有数据，继续添加照片
            } else {
                totalMap.put(photoType, mImageList);//初次添加数据
            }
        }

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
        mPresenter = new RepairGetMapPresenter(AddInsulatorCleanActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(AddInsulatorCleanActivity.this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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

    @Override
    public HashMap<String, Object> getDataMap() {
        Log.e(TAG, "taskCode " + mTaskCode);
        HashMap<String, Object> map = new HashMap<>();

        map.put("lineName", lineName);
        map.put("lineId", lineBean.getLineId());
        map.put("towerId", towerBean.getTowerId());
        map.put("taskCode", mTaskCode);
        map.put("towerNo", lineNumber);
        map.put("lineVol", lineBean.getLineVol());
        map.put("loc", tvSelectLoc.getText().toString().trim());
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
                        etRepairEquipment.setText(String.valueOf(lineName + lineNumber + "杆"));
                        etPowerLevel.setText(String.valueOf(powerLevel + "KV"));
                    }
                }

                break;
        }
    }

    @Override
    public void onError(String result) {
        Log.e(TAG, "请求结果 " + result);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPathList.clear();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gridview:
                BaseEvent event = new BaseEvent();
                event.setEventType(RepairConstantUtils.NET_POST_DETECTION_IMAGE);
                ImagePickUtils.promptSelectImgDialog(event, position, AddInsulatorCleanActivity.this, mImageList, REQUEST_CHECK_PIC, 4, R.id.ry_insulator);
                PowerApplication.setCurrentDataList(mImageList);
                break;
            default:
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
                mPresenter.upInsulatorPhoto(PowerApplication.getmDataList().get(0).sourcePath, mTaskCode, 1, "测试");
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
//                    mPresenter.deleteSitePhoto(picCode);
                }
                break;
            case RepairConstantUtils.DELETE_IMAGE:

                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }


    @OnClick({R.id.et_repair_equipment, R.id.repair_task_standard_ry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_repair_equipment:
                mPresenter.changeToSelect(mTaskCode,RepairConstantUtils.LINE_INSULATOR,AddInsulatorCleanActivity.this);
                break;
            case R.id.repair_task_standard_ry:
                if (mPresenter.checkDataSet(this, lineName, lineNumber, selectText, tvSelectLoc)) {
                    mPresenter.addInsulatorClear();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
