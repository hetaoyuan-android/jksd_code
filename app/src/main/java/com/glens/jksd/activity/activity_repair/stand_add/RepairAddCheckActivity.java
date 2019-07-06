package com.glens.jksd.activity.activity_repair.stand_add;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.PowerApplication;
import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.UpRepairPhotoBean;
import com.glens.jksd.bean.repair_bean.EquipmentBean;
import com.glens.jksd.network.presenter.RepairTowerDetailPresenter;
import com.glens.jksd.network.view.BaseRepairView;
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
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 新增现场查勘
 * 查勘人id是userCode
 */
public class RepairAddCheckActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, BaseRepairView {

    public static final String TAG = RepairAddCheckActivity.class.getSimpleName();
    @Bind(R.id.tv_repair_equipment)
    TextView mTvRepairEquipment;
    @Bind(R.id.tv_check_person)
    TextView mTvCheckPerson;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;
    @Bind(R.id.tv_repair_content_title)
    TextView mTvRepairContentTitle;
    @Bind(R.id.et_repair_content)
    EditText mEtRepairContent;
    @Bind(R.id.tv_repair_pic_title)
    TextView mTvRepairPicTitle;
    @Bind(R.id.gridview)
    MyGridView mGridView;

    private ArrayList<String> mPathList;
    private ArrayList<ImageItem> mImageList;
    private ImagePublishAdapter mImageListAdapter;
    private RepairTowerDetailPresenter mPresenter;
    private UpRepairPhotoBean photoBean;
    private String mTaskCode;
    private String picCode;
    private boolean isShowDelete = false;
    private static final int REQUEST_CHECK_PIC = 110;
    public static final int checkCode = 1002;
    private EquipmentBean.RecordsBean lineBean;
    private String lineName;
    private String lineNumber;
    private EquipmentBean.RecordsBean.TowerBean towerBean;
    private String mUserCode;
    private String mUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_add_check);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void initView() {
        setTitleText(R.string.repair_new_check);
        getLlBasetitleBack().setOnClickListener(this);
        mRepairTaskStandardText.setText(R.string.repair_check_submit);
        mTvRepairContentTitle.setText("安全措施");
        ButterKnife.bind(this);

        initData();
        initImageData();
        initNetData();
    }

    private void initData() {
        mImageList = new ArrayList<>();
        mPathList = new ArrayList<>();
        mTaskCode = PreferenceUtils.getString(this, "taskCode", "");
        mUserCode = PreferenceUtils.getString(this, RepairConstantUtils.USER_SAVE_CODE,"");
        mUserName = PreferenceUtils.getString(this, RepairConstantUtils.USER_SAVE_NAME,"");
        mTvCheckPerson.setText(mUserName);
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
        mPresenter = new RepairTowerDetailPresenter(RepairAddCheckActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(RepairAddCheckActivity.this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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
//                        powerLevel = Objects.requireNonNull(lineBean.getLineVol());
                        mTvRepairEquipment.setText(String.valueOf(lineName + lineNumber + "杆"));
//                        etPowerLevel.setText(String.valueOf(powerLevel + "KV"));
                    }
                }

                break;
        }
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
                ImagePickUtils.promptSelectImgDialog(event, position, this, mImageList, REQUEST_CHECK_PIC, 4, R.id.fy_check);
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
                mPresenter.upSiteSurveyPhoto(PowerApplication.getmDataList().get(0).sourcePath, mTaskCode, "测试");
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
                    mPresenter.deleteSitePhoto(picCode);
                }
                break;
            case RepairConstantUtils.DELETE_IMAGE:

                break;
            default:
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

    @Override
    public void onError(String result) {
        Log.e(TAG, "请求结果 " + result);
    }



    private HashMap<String, Object> getDataMap() {
        HashMap<String, Object> map = new HashMap<>();
        if (checkDataSet()) {
            map.put("taskCode", mTaskCode);
            map.put("lineId", lineBean.getLineId());
            map.put("towerId", towerBean.getTowerId());
            map.put("workContent", mEtRepairContent.getText().toString().trim());
            map.put("surveyManId", mUserCode);
            if (!TextUtils.isEmpty(picCode)) {
                map.put("picCodes", picCode);//选填
            }
            Log.e(TAG, "添加的数据源" + map.toString());
            return map;
        } else {
            return null;
        }

    }

    private boolean checkDataSet() {
        if (!TextUtils.isEmpty(mTvRepairEquipment.getText().toString().trim())) {
            if (!TextUtils.isEmpty(mUserCode)) {
                if (!TextUtils.isEmpty(mEtRepairContent.getText().toString().trim())) {
//                    if (!TextUtils.isEmpty(picCode)) {
//                        return true;
//                    } else {
//                        ToastUtils.showToastSafe(this, "请上传照片");
//                        return false;
//                    }
                    return true;
                } else {
                    ToastUtils.showToastSafe(this, "请输入安全措施");
                    return false;
                }
            } else {
                ToastUtils.showToastSafe(this, "查勘人id为空");
                return false;
            }
        } else {
            ToastUtils.showToastSafe(this, "请输入设备名称");
            return false;
        }
    }

    @OnClick({R.id.tv_repair_equipment, R.id.fy_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_repair_equipment:
                mPresenter.changeToSelect(mTaskCode,RepairConstantUtils.LINE_BASE, RepairAddCheckActivity.this);
                break;
            case R.id.fy_check:
                if (getDataMap() != null) {
                    mPresenter.addSiteSurvey(getDataMap());
                }
                break;
        }
    }
}
