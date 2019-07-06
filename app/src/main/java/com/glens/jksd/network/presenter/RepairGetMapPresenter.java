package com.glens.jksd.network.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.glens.jksd.activity.activity_repair.RepairSelectActivity;
import com.glens.jksd.activity.activity_repair.SelectLineActivity;
import com.glens.jksd.activity.activity_repair.stand_add.RepairAddInformationActivity;
import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.LoginBean;
import com.glens.jksd.bean.RepairAddTowerCheckBean;
import com.glens.jksd.bean.UpRepairPhotoBean;
import com.glens.jksd.bean.deteect.AddGrounResistanceBean;
import com.glens.jksd.bean.repair_bean.EquipmentBean;
import com.glens.jksd.bean.repair_bean.TowerBean;
import com.glens.jksd.network.BasePresenter;
import com.glens.jksd.network.InterManage;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.RepairTowerAddView;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import rx.Subscriber;

/**
 * Created by wkc on 2019/6/14.
 */
public class RepairGetMapPresenter extends BasePresenter {
    private Context mContext;
    private RepairTowerAddView mRepairTowerAddView;
    private LoginBean mUserInfo;

    public RepairGetMapPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        super.BindPresentView(presentView);
        mRepairTowerAddView = (RepairTowerAddView) presentView;
    }

    public void addTowerCheck() {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addTowerCheck(this, mRepairTowerAddView.getDataMap(), new Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RepairAddTowerCheckBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void addLineCheck() {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addLineCheck(this, mRepairTowerAddView.getDataMap(), new Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RepairAddTowerCheckBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void addWaterTest() {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addWaterTest(this, mRepairTowerAddView.getDataMap(), new Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RepairAddTowerCheckBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    /**
     * 提交开收工录音
     */
    public void addSoundRecord() {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addSoundRecord(this, mRepairTowerAddView.getDataMap(), new Subscriber<AutoSingleResponse<AddGrounResistanceBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<AddGrounResistanceBean> info) {
                mRepairTowerAddView.hideDialog();
                if (info.isResult()) {
                    mRepairTowerAddView.onSuccess(info.getData(), info.getMsg());
                    mRepairTowerAddView.getDataMap();
                } else {
                    mRepairTowerAddView.showDialog(info.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    /**
     * 新增检修资料
     */
    public void addInformationRecord() {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addInformationRecord(this, mRepairTowerAddView.getDataMap(), new Subscriber<AutoSingleResponse<AddGrounResistanceBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<AddGrounResistanceBean> info) {
                mRepairTowerAddView.hideDialog();
                if (info.isResult()) {
                    mRepairTowerAddView.onSuccess(info.getData(), info.getMsg());
                    mRepairTowerAddView.getDataMap();
                } else {
                    mRepairTowerAddView.showDialog(info.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }


    public void lineCheckList(String taskCode, int selectType, String lineId) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().lineCheckList(this, taskCode, selectType, lineId, new Subscriber<AutoSingleResponse<TowerBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<TowerBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }


    public void upTowerPhoto(String imagePath, String taskCode, int picType, String remarks) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upTowerPhoto(this, imagePath, taskCode, picType, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void upInformationPhoto(String imagePath, String taskCode, int picType, String remarks) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upInformationPhoto(this, imagePath, taskCode, picType, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }


    public void upSoundAudio(String audioPath, String taskCode, String remarks) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upSoundAudio(this, audioPath, taskCode, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_AUDIO);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void upInformationAudio(String audioPath, String taskCode, String remarks) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upInformationAudio(this, audioPath, taskCode, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_AUDIO);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }


    public void deleteLineCheckPhoto(String picCode) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().deleteLineCheckPhoto(this, picCode, new Subscriber<AutoSingleResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<Object> userInfo) {
                Log.e("deleteTowerPhoto", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void deleteSprayPhoto(String picCode) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().deleteSprayPhoto(this, picCode, new Subscriber<AutoSingleResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<Object> userInfo) {
                Log.e("deleteTowerPhoto", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }


    public void deleteTowerPhoto(String picCode) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().deleteTowerPhoto(this, picCode, new Subscriber<AutoSingleResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<Object> userInfo) {
                Log.e("deleteTowerPhoto", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }


    public void upLineCheckPhoto(String imagePath, String taskCode, String remarks) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upLineCheckPhoto(this, imagePath, taskCode, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void upWorkRecordPhoto(String imagePath, String taskCode, String remarks) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upWorkRecordPhoto(this, imagePath, taskCode, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }


    public void addInsulatorClear() {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addInsulatorClear(this, mRepairTowerAddView.getDataMap(), new Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RepairAddTowerCheckBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void addSpray() {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addSpray(this, mRepairTowerAddView.getDataMap(), new Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RepairAddTowerCheckBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }


    public void equipmentList(String taskCode, int selectType) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().equipmentList(this, taskCode, selectType, new Subscriber<AutoSingleResponse<EquipmentBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
            }

            @Override
            public void onNext(AutoSingleResponse<EquipmentBean> userInfo) {
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    /**
     * 绝缘子清扫提交检查参数
     */
    public boolean checkDataSet(BaseActivity activity, String lineName, String lineNumber, String selectText, EditText tvSelectLoc) {
        if (!TextUtils.isEmpty(lineName) && !TextUtils.isEmpty(lineNumber)) {
            if (!TextUtils.isEmpty(selectText)) {
                if (!TextUtils.isEmpty(tvSelectLoc.getText().toString().trim())) {
//                    if (!TextUtils.isEmpty(picCode)) {
//                        return true;
//                    } else {
//                        ToastUtils.showToastSafe(this, "请上传照片");
//                        return false;
//                    }
                    return true;
                } else {
                    ToastUtils.showToastSafe(activity, "请输入位置");
                    return false;
                }
//                return true;
            } else {
                ToastUtils.showToastSafe(activity, "请选择相别");
                return false;
            }
        } else {
            ToastUtils.showToastSafe(activity, "请选择设备名称");
            return false;
        }
    }

    /**
     * 走线检查提交检查参数
     */
    public boolean checkLineDataSet(BaseActivity activity, String lineName, String startTowerName, String endTowerName, String lineNumber, String selectText, EditText tvSelectLoc) {
        if (!TextUtils.isEmpty(lineName) && !TextUtils.isEmpty(lineNumber)) {
            if (!TextUtils.isEmpty(lineName) && !TextUtils.isEmpty(startTowerName)) {
                if (!TextUtils.isEmpty(lineName) && !TextUtils.isEmpty(endTowerName)) {
                    if (!TextUtils.isEmpty(selectText)) {
                        if (!TextUtils.isEmpty(tvSelectLoc.getText().toString().trim())) {
                            return true;
                        } else {
                            ToastUtils.showToastSafe(activity, "请输入位置");
                            return false;
                        }
//                return true;
                    } else {
                        ToastUtils.showToastSafe(activity, "请选择相别");
                        return false;
                    }
                } else {
                    ToastUtils.showToastSafe(activity, "请选择终点杆塔");
                    return false;
                }
            } else {
                ToastUtils.showToastSafe(activity, "请选择起点杆塔");
                return false;
            }
        } else {
            ToastUtils.showToastSafe(activity, "请选择线路");
            return false;
        }
    }

    /**
     * RTV喷涂提交检查参数
     */
    public boolean checkSprayDataSet(BaseActivity activity, String lineName, String lineNumber, String towerNumber, String selectText, EditText tvSelectLoc) {
        if (!TextUtils.isEmpty(lineName) && !TextUtils.isEmpty(lineNumber)) {
            if (!TextUtils.isEmpty(selectText)) {
                if (!TextUtils.isEmpty(towerNumber)) {
                    if (!TextUtils.isEmpty(tvSelectLoc.getText().toString().trim())) {
                        return true;
                    } else {
                        ToastUtils.showToastSafe(activity, "请输入位置");
                        return false;
                    }
                } else {
                    ToastUtils.showToastSafe(activity, "杆号为空");
                    return false;
                }
            } else {
                ToastUtils.showToastSafe(activity, "请选择相别");
                return false;
            }
        } else {
            ToastUtils.showToastSafe(activity, "请选择线路");
            return false;
        }
    }

    /**
     * 增水性实验参数
     */
    public boolean checkWaterTestData(BaseActivity activity, String lineName, String lineNumber, String towerNumber, String selectText, EditText tvSelectLoc, String textConclusion) {
        if (!TextUtils.isEmpty(lineName) && !TextUtils.isEmpty(lineNumber)) {
            if (!TextUtils.isEmpty(selectText)) {
                if (!TextUtils.isEmpty(towerNumber)) {
                    if (!TextUtils.isEmpty(tvSelectLoc.getText().toString().trim())) {
                        if (!TextUtils.isEmpty(textConclusion)) {
                            return true;
                        } else {
                            ToastUtils.showToastSafe(activity, "请输入实验结论");
                            return false;
                        }
                    } else {
                        ToastUtils.showToastSafe(activity, "请输入位置");
                        return false;
                    }
                } else {
                    ToastUtils.showToastSafe(activity, "杆号为空");
                    return false;
                }
            } else {
                ToastUtils.showToastSafe(activity, "请选择相别");
                return false;
            }
        } else {
            ToastUtils.showToastSafe(activity, "请选择线路");
            return false;
        }
    }

    public boolean checkRecordData(Context activity, String mTaskCode, int soundType, String soundRecordorId, String soundName) {
        if (!TextUtils.isEmpty(mTaskCode)) {
            if (soundType != 0) {
                if (!TextUtils.isEmpty(soundRecordorId)) {
                    if (!TextUtils.isEmpty(soundName)) {
                        return true;
                    } else {
                        ToastUtils.showToastSafe(activity, "请输入录音名称");
                        return false;
                    }
                } else {
                    ToastUtils.showToastSafe(activity, "录音人id为空");
                    return false;
                }
            } else {
                ToastUtils.showToastSafe(activity, "请选择录音类型");
                return false;
            }
        } else {
            ToastUtils.showToastSafe(activity, "mTaskCode为空");
            return false;
        }

    }

    public void upInsulatorPhoto(String imagePath, String taskCode, int picType, String remarks) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upInsulatorPhoto(this, imagePath, taskCode, picType, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void upSprayPhoto(String imagePath, String taskCode, int picType, String remarks) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upSprayPhoto(this, imagePath, taskCode, picType, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void upWaterTestPhoto(String imagePath, String taskCode, String remarks) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upWaterTestPhoto(this, imagePath, taskCode, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void deleteWaterTestPhoto(String picCode) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().deleteWaterTestPhoto(this, picCode, new Subscriber<AutoSingleResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<Object> userInfo) {
                Log.e("deleteTowerPhoto", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void deleteSoundPicture(String picCode) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().deleteSoundPicture(this, picCode, new Subscriber<AutoSingleResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<Object> userInfo) {
                Log.e("deleteTowerPhoto", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void deleteInformationPicture(String picCode) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().deleteInformationPicture(this, picCode, new Subscriber<AutoSingleResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<Object> userInfo) {
                Log.e("deleteTowerPhoto", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void deleteSound(String audioCode) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().deleteSound(this, audioCode, new Subscriber<AutoSingleResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<Object> userInfo) {
                Log.e("deleteTowerPhoto", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_AUDIO);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }

    public void deleteInformationSound(String audioCode) {
        mRepairTowerAddView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().deleteInformationSound(this, audioCode, new Subscriber<AutoSingleResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairTowerAddView.hideDialog();
                mRepairTowerAddView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<Object> userInfo) {
                Log.e("deleteTowerPhoto", userInfo.toString());
                mRepairTowerAddView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairTowerAddView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_AUDIO);
                } else {
                    mRepairTowerAddView.showDialog(userInfo.getMsg());
                    mRepairTowerAddView.hideDialog();
                }
            }
        });
    }


    public void changeToSelect(String mTaskCode, int repairTwo, BaseActivity activity) {
        Intent intent = new Intent(activity, RepairSelectActivity.class);
        intent.putExtra(RepairConstantUtils.TASKCODE, mTaskCode);
        intent.putExtra(RepairConstantUtils.TASKTYPE, repairTwo);
        activity.startActivityForResult(intent, RepairConstantUtils.SELECT_EQUIPMENT);
    }

    public void changeToSelectTower(String mTaskCode, int repairTwo, String lineId, String selectType, int requestCode, BaseActivity activity) {
        Intent intent = new Intent(activity, SelectLineActivity.class);
        intent.putExtra(RepairConstantUtils.TASKCODE, mTaskCode);
        intent.putExtra(RepairConstantUtils.TASKTYPE, repairTwo);
        intent.putExtra(RepairConstantUtils.LINE_ID, lineId);
        intent.putExtra(RepairConstantUtils.SELECT_TYPE, selectType);
        activity.startActivityForResult(intent, requestCode);
    }


    public void checkToSelectPowerList(String mLineId, String mTaskCode, String selectType, int requestCode, BaseActivity activity) {
        if (!TextUtils.isEmpty(mLineId)) {
            changeToSelectTower(mTaskCode, RepairConstantUtils.LINE_INSULATOR, mLineId, selectType, requestCode, activity);
        } else {
            ToastUtils.showToastSafe(activity, "请先选择线路");
        }
    }

    public String getStringPhase(String selectText) {
        String phase;
        switch (selectText) {
            case RepairConstantUtils.PHASEA:
                phase = "0";
                break;
            case RepairConstantUtils.PHASEB:
                phase = "1";
                break;
            case RepairConstantUtils.PHASEC:
                phase = "2";
                break;
            default:
                phase = "";
                break;
        }
        return phase;
    }

    public void upAudioData(int mCurrentItem, String TAG, String mTaskCode, Context context, String taskType) {
        String audioPath = null;
        switch (mCurrentItem) {
            case RepairConstantUtils.REPAIR_RECORD_ITEM_ONE:
                audioPath = PreferenceUtils.getString(context, RepairConstantUtils.REPAIR_RECORD_ONE, null);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_TWO:
                audioPath = PreferenceUtils.getString(context, RepairConstantUtils.REPAIR_RECORD_TWO, null);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_THREE:
                audioPath = PreferenceUtils.getString(context, RepairConstantUtils.REPAIR_RECORD_THREE, null);
                break;
            default:
                Log.e(TAG, "当前item数据错误");
                break;
        }
        if (!TextUtils.isEmpty(audioPath)) {
            Log.e(TAG, "当前presenter音频名称" + audioPath);
            switch (taskType) {
                case RepairConstantUtils.WORK_RECORD_TYPE:
                    upSoundAudio(audioPath, mTaskCode, "语音");
                    break;
                case RepairConstantUtils.INFORMATION_TYPE:
                    upInformationAudio(audioPath, mTaskCode, "检修");
                    break;
                default:
                    break;
            }
        } else {
            Log.e(TAG, "当前audio为空" + mCurrentItem);
        }

    }

    /**
     * 考虑乱序删除的情况
     *
     * @param soundType
     */
    public void checkDeleteSound(int soundType, HashMap<String, String> mAudioPathMap, String TAG, String taskType) {
        switch (soundType) {
            case RepairConstantUtils.REPAIR_RECORD_ITEM_ONE:
                checkDeleteAudio(mAudioPathMap.get(RepairConstantUtils.REPAIR_RECORD_ONE), TAG,taskType);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_TWO:
                checkDeleteAudio(mAudioPathMap.get(RepairConstantUtils.REPAIR_RECORD_TWO), TAG,taskType);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_THREE:
                checkDeleteAudio(mAudioPathMap.get(RepairConstantUtils.REPAIR_RECORD_THREE), TAG,taskType);
                break;
            default:
                Log.e(TAG, "soundType异常" + soundType);
                break;
        }
    }

    public void checkDeleteAudio(String audioCode, String TAG, String taskType) {
        if (!TextUtils.isEmpty(audioCode)) {
            switch (taskType) {
                case RepairConstantUtils.WORK_RECORD_TYPE:
                    deleteSound(audioCode);
                    break;
                case RepairConstantUtils.INFORMATION_TYPE:
                    deleteInformationSound(audioCode);
                    break;
                default:
                    Log.e(TAG, "taskType异常"+ taskType);
                    break;
            }
        } else {
            Log.e(TAG, "audioCode为空");
        }
    }

    public void keepDataPosition(String TAG, HashMap<String, String> mAudioPathMap, ArrayList<String> mAudioList) {
        switch (mAudioList.size()) {
            case RepairConstantUtils.REPAIR_ONE:
                mAudioPathMap.put(RepairConstantUtils.REPAIR_RECORD_ONE, mAudioList.get(0));
                break;
            case RepairConstantUtils.REPAIR_TWO:
                mAudioPathMap.put(RepairConstantUtils.REPAIR_RECORD_TWO, mAudioList.get(1));
                break;
            case RepairConstantUtils.REPAIR_THREE:
                mAudioPathMap.put(RepairConstantUtils.REPAIR_RECORD_THREE, mAudioList.get(2));
                break;
            default:
                Log.e(TAG, "音频路径数量异常" + mAudioList.size());
                break;
        }

    }

    public boolean checkInformationData(RepairAddInformationActivity activity, String mTaskCode, String picCode, String audioCode) {
        if (!TextUtils.isEmpty(mTaskCode)) {
            if (!TextUtils.isEmpty(picCode)) {
                if (!TextUtils.isEmpty(audioCode)) {
                    return true;
                } else {
                    ToastUtils.showToastSafe(activity, "请上传音频");
                    return false;
                }
            } else {
                ToastUtils.showToastSafe(activity, "请上传照片");
                return false;
            }
        } else {
            ToastUtils.showToastSafe(activity, "mTaskCode为空");
            return false;
        }
    }
}
