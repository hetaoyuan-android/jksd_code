package com.glens.jksd.activity.activity_repair;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.PowerApplication;
import com.glens.jksd.R;
import com.glens.jksd.activity.activity_repair.stand_add.RepairAddGroundActivity;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.UpRepairPhotoBean;
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
 * 领用拍照
 */
public class RepairGroundPhotoActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, BaseRepairView {
    public static final String TAG = RepairAddGroundActivity.class.getSimpleName();
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;
    @Bind(R.id.grid_view)
    MyGridView mGridView;
    private ArrayList<String> mPathList;
    private ArrayList<ImageItem> mImageList;
    private ImagePublishAdapter mImageListAdapter;
    private static final int REQUEST_CHECK_PIC = 113;
    private UpRepairPhotoBean photoBean;
    private RepairTowerDetailPresenter mPresenter;
    private boolean isShowDelete = false;
    private String mTaskCode;
    private String picCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_ground_photo);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.grid_view:
                BaseEvent event = new BaseEvent();
                event.setEventType(RepairConstantUtils.NET_POST_DETECTION_IMAGE);
                ImagePickUtils.promptSelectImgDialog(event, position, this, mImageList, REQUEST_CHECK_PIC, 4, R.id.fy_check);
                PowerApplication.setCurrentDataList(mImageList);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        mTaskCode = PreferenceUtils.getString(this, "taskCode", "");
        mPresenter = new RepairTowerDetailPresenter(RepairGroundPhotoActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(RepairGroundPhotoActivity.this);
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


    @Override
    public void onClick(View v) {
        finish();
    }

    private void initView() {
        mImageList = new ArrayList<>();
        mPathList = new ArrayList<>();
        setTitleText("领用");
        getLlBasetitleBack().setOnClickListener(this);
        mTaskCode = PreferenceUtils.getString(this, "taskCode", "");
        mRepairTaskStandardText.setText(R.string.repair_check_submit);
        ButterKnife.bind(this);

        initNetData();
        initImageData();
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
//                        lineBean = (EquipmentBean.RecordsBean) Objects.requireNonNull(data.getExtras()).getSerializable(RepairConstantUtils.LINEBEAN);
//                        towerBean = (EquipmentBean.RecordsBean.TowerBean) Objects.requireNonNull(data.getExtras()).getSerializable(RepairConstantUtils.TOWERBEAN);
//
//                        mLineId = Objects.requireNonNull(lineBean.getLineId());
//                        lineName = Objects.requireNonNull(lineBean.getLineName());
//                        lineNumber = Objects.requireNonNull(towerBean.getTowerNo());
//                        powerLevel = Objects.requireNonNull(lineBean.getLineVol());
//                        tvCheckLine.setText(lineName);
//                        tvLineLevel.setText(String.valueOf(powerLevel + "KV"));
                    }
                }
                break;
            case RepairConstantUtils.START_TOWER:
                if (resultCode == RepairConstantUtils.SELECT_EQUIPMENT_RESULT) {
                    if (data != null) {
//                        mSingleTowerBean = (TowerBean.RecordsBean) Objects.requireNonNull(data.getExtras()).getSerializable(RepairConstantUtils.TOWERBEAN);
//
//                        tvCheckLineStart.setText(String.valueOf(Objects.requireNonNull(mSingleTowerBean).getTowerNo() + "杆"));
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
                mPresenter.upGroundPhoto(PowerApplication.getmDataList().get(0).sourcePath, mTaskCode, RepairConstantUtils.REPAIR_ONE, "测试");
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
                    mPresenter.deleteGroundPhoto(picCode);
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
        } else {
            dismissSvpDilog(0);
            ToastUtils.showToastSafe(this, resultMsg);
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


    @OnClick({R.id.repair_task_standard_ry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.repair_task_standard_ry:
                if (mPresenter.checkGroundPhoto(this, mTaskCode,picCode)) {
                    mPresenter.addGroundMout(getDataMap());
                }
                break;
        }
    }

    private HashMap<String, Object> getDataMap() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("taskCode",mTaskCode);
        map.put("picCodes",picCode);
        return map;
    }
}
