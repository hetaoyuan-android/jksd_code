package com.glens.jksd.network.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.glens.jksd.activity.activity_repair.RepairSelectActivity;
import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.RepairAddTowerCheckBean;
import com.glens.jksd.bean.RepairTowerDetailBean;
import com.glens.jksd.bean.RepairTowerListBean;
import com.glens.jksd.bean.UpRepairPhotoBean;
import com.glens.jksd.bean.repair_bean.DefectDetailBean;
import com.glens.jksd.bean.repair_bean.GroundDetailBean;
import com.glens.jksd.bean.repair_bean.InformationDetailBean;
import com.glens.jksd.bean.repair_bean.InsulatorDetailBean;
import com.glens.jksd.bean.repair_bean.LineCheckBean;
import com.glens.jksd.bean.repair_bean.RecordDetailBean;
import com.glens.jksd.bean.repair_bean.RepairTaskDetailBean;
import com.glens.jksd.bean.repair_bean.RtvSprayDetailBean;
import com.glens.jksd.bean.repair_bean.WaterTestDetailBean;
import com.glens.jksd.bean.repair_task_bean.RepairGroundBean;
import com.glens.jksd.bean.repair_task_bean.RepairInformationBean;
import com.glens.jksd.network.BasePresenter;
import com.glens.jksd.network.InterManage;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.BaseRepairView;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;

import java.util.HashMap;

import rx.Subscriber;

/**
 * Created by wkc on 2019/6/11.
 */
public class RepairTowerDetailPresenter extends BasePresenter {
    private Context mContext;
    private BaseRepairView mRepairView;

    public RepairTowerDetailPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        super.BindPresentView(presentView);
        mRepairView = (BaseRepairView) presentView;
    }

    public void repairTowerDetail(String checkRecordCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().repairTowerDetail(this, checkRecordCode, new Subscriber<AutoSingleResponse<RepairTowerDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RepairTowerDetailBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void upTowerPhoto(String imagePath, String taskCode, int picType, String remarks) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upTowerPhoto(this, imagePath, taskCode, picType, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void deleteTowerPhoto(String picCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().deleteTowerPhoto(this, picCode, new Subscriber<AutoSingleResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<Object> userInfo) {
                Log.e("deleteTowerPhoto", userInfo.toString());
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo, userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void repairTaskDetail(String checkRecordCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().repairTaskDetail(this, checkRecordCode, new Subscriber<AutoSingleResponse<DefectDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<DefectDetailBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void insulatorDetail(String checkRecordCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().insulatorDetail(this, checkRecordCode, new Subscriber<AutoSingleResponse<InsulatorDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<InsulatorDetailBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void informationDetail(String checkRecordCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().informationDetail(this, checkRecordCode, new Subscriber<AutoSingleResponse<InformationDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<InformationDetailBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }


    public void traceCheckDetail(String checkRecordCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().traceCheckDetail(this, checkRecordCode, new Subscriber<AutoSingleResponse<LineCheckBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<LineCheckBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void sprayDetail(String checkRecordCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().sprayDetail(this, checkRecordCode, new Subscriber<AutoSingleResponse<RtvSprayDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RtvSprayDetailBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void waterTestDetail(String checkRecordCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().waterTestDetail(this, checkRecordCode, new Subscriber<AutoSingleResponse<WaterTestDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<WaterTestDetailBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void siteSurveyDetail(String checkRecordCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().siteSurveyDetail(this, checkRecordCode, new Subscriber<AutoSingleResponse<RepairTaskDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RepairTaskDetailBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void addSiteSurvey(HashMap<String, Object> map) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addSiteSurvey(this, map, new Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RepairAddTowerCheckBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void updateGroundCheck(HashMap<String, Object> map) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().updateGroundCheck(this, map, new Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RepairAddTowerCheckBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }


    public void addGroundMout(HashMap<String, Object> map) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addGroundMout(this, map, new Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RepairAddTowerCheckBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }



    public void upSiteSurveyPhoto(String imagePath, String taskCode, String remarks) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upSiteSurveyPhoto(this, imagePath, taskCode, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void deleteSitePhoto(String picCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().deleteSitePhoto(this, picCode, new Subscriber<AutoSingleResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<Object> userInfo) {
                Log.e("deleteTowerPhoto", userInfo.toString());
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo, userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void deleteGroundPhoto(String picCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().deleteGroundPhoto(this, picCode, new Subscriber<AutoSingleResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<Object> userInfo) {
                Log.e("deleteTowerPhoto", userInfo.toString());
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo, RepairConstantUtils.UP_PIC);
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }


    public void soundList(String taskCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().soundList(this, taskCode, new Subscriber<AutoSingleResponse<RepairTowerListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
            }

            @Override
            public void onNext(AutoSingleResponse<RepairTowerListBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void repairSoundDetail(String recordCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().repairSoundDetail(this, recordCode, new Subscriber<AutoSingleResponse<RecordDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<RecordDetailBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void groundList(String taskCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().groundList(this, taskCode, new Subscriber<AutoSingleResponse<RepairGroundBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
            }

            @Override
            public void onNext(AutoSingleResponse<RepairGroundBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void groundrDetail(String checkRecordCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().groundrDetail(this, checkRecordCode, new Subscriber<AutoSingleResponse<GroundDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<GroundDetailBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void informationList( String taskCode) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().informationList(this,taskCode, new Subscriber<AutoSingleResponse<RepairInformationBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
            }

            @Override
            public void onNext(AutoSingleResponse<RepairInformationBean> userInfo) {
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
                }
            }
        });
    }

    public void upGroundPhoto(String imagePath, String taskCode, int picType, String remarks) {
        mRepairView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().upGroundPhoto(this, imagePath, taskCode, picType, remarks, new Subscriber<AutoSingleResponse<UpRepairPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepairView.hideDialog();
                mRepairView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<UpRepairPhotoBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairView.onSuccess(userInfo.getData(), RepairConstantUtils.UP_PIC);
                } else {
                    mRepairView.showDialog(userInfo.getMsg());
                    mRepairView.hideDialog();
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

    public boolean checkGroundPhoto(BaseActivity activity, String mTaskCode, String picCode) {
        if (mTaskCode != null) {
            if(picCode!=null){

                return true;
            }else{
                ToastUtils.showToastSafe(activity,"请上传照片");
                return false;
            }
        }else {
            ToastUtils.showToastSafe(activity,"mTaskCode为空");
            return false;
        }
    }
}
