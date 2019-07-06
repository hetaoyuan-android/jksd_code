package com.glens.jksd.network;

import android.content.Context;
import android.util.Log;

import com.glens.jksd.PowerApplication;
import com.glens.jksd.R;
import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.bean.RepairAddTowerCheckBean;
import com.glens.jksd.bean.RepairTowerDetailBean;
import com.glens.jksd.bean.RepairTowerListBean;
import com.glens.jksd.bean.ResistanceBean;
import com.glens.jksd.bean.UpRepairPhotoBean;
import com.glens.jksd.bean.deteect.AddGrounResistanceBean;
import com.glens.jksd.bean.deteect.GroundResistanceDetailBean;
import com.glens.jksd.bean.deteect.GroundResistanceListBean;
import com.glens.jksd.bean.deteect.InfraraedListBean;
import com.glens.jksd.bean.deteect.InfraredDetailBean;
import com.glens.jksd.bean.deteect.PayMeasureDetailBean;
import com.glens.jksd.bean.deteect.PayMeasureListBean;
import com.glens.jksd.bean.repair_bean.DefectDetailBean;
import com.glens.jksd.bean.repair_bean.EquipmentBean;
import com.glens.jksd.bean.repair_bean.GroundDetailBean;
import com.glens.jksd.bean.repair_bean.InformationDetailBean;
import com.glens.jksd.bean.repair_bean.InsulatorDetailBean;
import com.glens.jksd.bean.repair_bean.LineCheckBean;
import com.glens.jksd.bean.repair_bean.RecordDetailBean;
import com.glens.jksd.bean.repair_bean.RepairTaskDetailBean;
import com.glens.jksd.bean.repair_bean.RtvSprayDetailBean;
import com.glens.jksd.bean.repair_bean.TowerBean;
import com.glens.jksd.bean.repair_bean.WaterTestDetailBean;
import com.glens.jksd.bean.repair_task_bean.RepairGroundBean;
import com.glens.jksd.bean.repair_task_bean.RepairInformationBean;
import com.glens.jksd.utils.CommonUtils;
import com.glens.jksd.utils.ImageSizeUtil;
import com.glens.jksd.utils.RepairConstantUtils;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wkc on 2019/5/23.
 */
public class InterManage {

    private static class SingletonHolder {
        private static final InterManage mSingleton = new InterManage();

    }

    public static InterManage getInstance() {
        return InterManage.SingletonHolder.mSingleton;
    }


    // 接地电阻分页列表
    public void getGroundResistanceList(BasePresenter presenter, String lineVol, String lineName, String isInspected, String towerNo, int page, int rows, String taskCode, Subscriber<AutoSingleResponse<GroundResistanceListBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<GroundResistanceListBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).getGroundResistanceList(lineVol, lineName, isInspected, towerNo, page, rows, taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    // 接地电阻详情页
    public void getGroundResistanceDetailList(BasePresenter presenter, String recodeCode, Subscriber<AutoSingleResponse<GroundResistanceDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<GroundResistanceDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).getGroundResistanceDetailList(recodeCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    // 新增接地电阻
    public void addGroundResistance(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<AddGrounResistanceBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<AddGrounResistanceBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addGroundResistance(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    // 新增开收工录音
    public void addSoundRecord(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<AddGrounResistanceBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<AddGrounResistanceBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addSoundRecord(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    // 新增检修资料
    public void addInformationRecord(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<AddGrounResistanceBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<AddGrounResistanceBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addInformationRecord(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    // 新增接地电阻带图片
    public void addGroundResistanceAndImage(BasePresenter presenter,String path,HashMap<String, Object> map, Subscriber<AutoSingleResponse<AddGrounResistanceBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("图片大小",path+ ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<AddGrounResistanceBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).addGroundResistanceAndImage(map,requestFile).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }


    // 红外测温分页列表
    public void getInfraredList(BasePresenter presenter, String lineVol, String lineName, String isInspected, String towerNo, int page, int rows, String taskCode, Subscriber<AutoSingleResponse<InfraraedListBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<InfraraedListBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).getInfraredList(lineVol, lineName, isInspected, towerNo, page, rows, taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    // 红外测温详情页
    public void getInfraredDetailList(BasePresenter presenter, String recodeCode, Subscriber<AutoSingleResponse<InfraredDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<InfraredDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).getInfraredDetailList(recodeCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    // 新增红外测温
    public void addInfrared(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<AddGrounResistanceBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<AddGrounResistanceBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addInfrared(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    // 交跨测量的分页列表
    public void getPayMeasureList(BasePresenter presenter, String lineVol, String lineName, String isInspected, Integer crossType, String exeUnitId, String startTowerNo, String endTowerNo, String taskCode, int page, int rows, Subscriber<AutoSingleResponse<PayMeasureListBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<PayMeasureListBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).getPayMeasureList(lineVol, lineName, isInspected, crossType, exeUnitId, startTowerNo, endTowerNo, taskCode, page, rows).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    // 交跨测量详情页
    public void getPayMeasureDetailList(BasePresenter presenter, String recodeCode, Subscriber<AutoSingleResponse<PayMeasureDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<PayMeasureDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).getPayMeasureDetailList(recodeCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    // 新增交跨测量
    public void addPayMeasure(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<AddGrounResistanceBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<AddGrounResistanceBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addPayMeasure(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }


    public void getDetectionList(String taskName, String taskTime, int taskType, int page, int rows, Subscriber<AutoSingleResponse<ResistanceBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<ResistanceBean> s = new AutoSingleResponse<ResistanceBean>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).detectionList(taskName, taskTime, taskType, page, rows).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
//        super.mCompositeSubscription.add(subscriber);
    }

    public void getRepairTower(BasePresenter presenter, String startDate, String endDate, String taskCode, Subscriber<AutoSingleResponse<RepairTowerListBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairTowerListBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).repairTower(startDate, endDate, taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void getRepairTaskList(BasePresenter presenter, String startDate, String endDate, String taskCode, Subscriber<AutoSingleResponse<RepairTowerListBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairTowerListBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).repairTaskList(startDate, endDate, taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void insulatorClearList(BasePresenter presenter,String startDate,  String endDate, String taskCode, Subscriber<AutoSingleResponse<RepairTowerListBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairTowerListBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).insulatorClearList(startDate, endDate, taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void traceCheckList(BasePresenter presenter,String startDate,  String endDate, String taskCode, Subscriber<AutoSingleResponse<RepairTowerListBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairTowerListBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).traceCheckList(startDate, endDate, taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void rtvSprayList(BasePresenter presenter,String startDate,  String endDate, String taskCode, Subscriber<AutoSingleResponse<RepairTowerListBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairTowerListBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).rtvSprayList(startDate, endDate, taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void hydrophobicTestList(BasePresenter presenter,String startDate,  String endDate, String taskCode, Subscriber<AutoSingleResponse<RepairTowerListBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairTowerListBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).hydrophobicTestList(startDate, endDate, taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void siteSurveyList(BasePresenter presenter,String taskCode, Subscriber<AutoSingleResponse<RepairTowerListBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairTowerListBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).siteSurveyList(taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void soundList(BasePresenter presenter,String taskCode, Subscriber<AutoSingleResponse<RepairTowerListBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairTowerListBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).soundList(taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void informationList(BasePresenter presenter,String taskCode, Subscriber<AutoSingleResponse<RepairInformationBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairInformationBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).informationList(taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void groundList(BasePresenter presenter,String taskCode, Subscriber<AutoSingleResponse<RepairGroundBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairGroundBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).groundList(taskCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void equipmentList(BasePresenter presenter,String taskCode,int selectType, Subscriber<AutoSingleResponse<EquipmentBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<EquipmentBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).equipmentList(taskCode,selectType).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }


    public void infraredList(Context context, String lineVol, String lineName, int isInspected, String towerNo, String taskCode, int page, int rows, Subscriber<ResponseBody> subscriber) {
//        if (!CommonUtils.is_Network_Available(PowerApplication.getPowerApplication())) {
////            if (subscriber != null) {
////                AutoSingleResponse<ResistanceBean> s = new AutoSingleResponse<ResistanceBean>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
////                subscriber.onNext(s);
////            }
////            return;
//        }
//        super.mCompositeSubscription.add(DataManager.getInstance(context).infraredList(lineVol, lineName, isInspected, towerNo, taskCode, page, rows).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber));
    }

    public void repairTowerDetail(BasePresenter presenter, String checkRecordCode, Subscriber<AutoSingleResponse<RepairTowerDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairTowerDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).repairTowerDetail(checkRecordCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void repairTaskDetail(BasePresenter presenter,String checkRecordCode, Subscriber<AutoSingleResponse<DefectDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<DefectDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).repairTaskDetail(checkRecordCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void insulatorDetail(BasePresenter presenter,String checkRecordCode, Subscriber<AutoSingleResponse<InsulatorDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<InsulatorDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).insulatorDetail(checkRecordCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void informationDetail(BasePresenter presenter,String checkRecordCode, Subscriber<AutoSingleResponse<InformationDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<InformationDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).informationDetail(checkRecordCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void groundrDetail(BasePresenter presenter,String checkRecordCode, Subscriber<AutoSingleResponse<GroundDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<GroundDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).groundrDetail(checkRecordCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void traceCheckDetail(BasePresenter presenter,String checkRecordCode, Subscriber<AutoSingleResponse<LineCheckBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<LineCheckBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).traceCheckDetail(checkRecordCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void sprayDetail(BasePresenter presenter,String checkRecordCode, Subscriber<AutoSingleResponse<RtvSprayDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RtvSprayDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).sprayDetail(checkRecordCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void waterTestDetail(BasePresenter presenter,String checkRecordCode, Subscriber<AutoSingleResponse<WaterTestDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<WaterTestDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).waterTestDetail(checkRecordCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void siteSurveyDetail(BasePresenter presenter,String checkRecordCode, Subscriber<AutoSingleResponse<RepairTaskDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairTaskDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).siteSurveyDetail(checkRecordCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void repairSoundDetail(BasePresenter presenter,String checkRecordCode, Subscriber<AutoSingleResponse<RecordDetailBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RecordDetailBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).repairSoundDetail(checkRecordCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }


    public void upTowerPhoto(BasePresenter presenter, String path, String taskCode, int picType, String remarks, Subscriber<AutoSingleResponse<UpRepairPhotoBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("图片大小", path + ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<UpRepairPhotoBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).upTowerPhoto(requestFile, taskCode, picType, remarks).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void upInformationPhoto(BasePresenter presenter, String path, String taskCode, int picType, String remarks, Subscriber<AutoSingleResponse<UpRepairPhotoBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("图片大小", path + ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<UpRepairPhotoBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).upInformationPhoto(requestFile, taskCode, picType, remarks).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void upSoundAudio(BasePresenter presenter, String path, String taskCode, String remarks, Subscriber<AutoSingleResponse<UpRepairPhotoBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("音频大小", path + ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<UpRepairPhotoBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).upSoundAudio(requestFile, taskCode, remarks).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void upInformationAudio(BasePresenter presenter, String path, String taskCode, String remarks, Subscriber<AutoSingleResponse<UpRepairPhotoBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("音频大小", path + ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<UpRepairPhotoBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).upInformationAudio(requestFile, taskCode, remarks).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void upGroundPhoto(BasePresenter presenter, String path, String taskCode, int picType, String remarks, Subscriber<AutoSingleResponse<UpRepairPhotoBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("图片大小", path + ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<UpRepairPhotoBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).upGroundPhoto(requestFile, taskCode, picType, remarks).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void upWaterTestPhoto(BasePresenter presenter, String path, String taskCode,  String remarks, Subscriber<AutoSingleResponse<UpRepairPhotoBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("图片大小", path + ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<UpRepairPhotoBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).upWaterTestPhoto(requestFile, taskCode, remarks).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void upLineCheckPhoto(BasePresenter presenter, String path, String taskCode, String remarks, Subscriber<AutoSingleResponse<UpRepairPhotoBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("图片大小", path + ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<UpRepairPhotoBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).upLineCheckPhoto(requestFile, taskCode, remarks).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void upWorkRecordPhoto(BasePresenter presenter, String path, String taskCode, String remarks, Subscriber<AutoSingleResponse<UpRepairPhotoBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("图片大小", path + ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<UpRepairPhotoBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).upWorkRecordPhoto(requestFile, taskCode, remarks).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void upInsulatorPhoto(BasePresenter presenter, String path, String taskCode, int picType, String remarks, Subscriber<AutoSingleResponse<UpRepairPhotoBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("图片大小", path + ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<UpRepairPhotoBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).upInsulatorPhoto(requestFile, taskCode, picType, remarks).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void upSprayPhoto(BasePresenter presenter, String path, String taskCode, int picType, String remarks, Subscriber<AutoSingleResponse<UpRepairPhotoBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("图片大小", path + ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<UpRepairPhotoBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).upSprayPhoto(requestFile, taskCode, picType, remarks).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void upSiteSurveyPhoto(BasePresenter presenter,String path,String taskCode, String remarks, Subscriber<AutoSingleResponse<UpRepairPhotoBean>> subscriber) {
        File file = new File(path);
        try {
            Log.e("图片大小",path+ ImageSizeUtil.getFileSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<UpRepairPhotoBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        DataManager.getInstance(PowerApplication.getPowerApplication()).upSiteSurveyPhoto(requestFile,taskCode,remarks).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }


    public void deleteTowerPhoto(BasePresenter presenter,String picCode, Subscriber<AutoSingleResponse<Object>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<Object> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).deleteTowerPhoto(picCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void deleteLineCheckPhoto(BasePresenter presenter,String picCode, Subscriber<AutoSingleResponse<Object>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<Object> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).deleteLineCheckPhoto(picCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void deleteWaterTestPhoto(BasePresenter presenter,String picCode, Subscriber<AutoSingleResponse<Object>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<Object> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).deleteWaterTestPhoto(picCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void deleteSoundPicture(BasePresenter presenter,String picCode, Subscriber<AutoSingleResponse<Object>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<Object> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).deleteSoundPicture(picCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void deleteInformationPicture(BasePresenter presenter,String picCode, Subscriber<AutoSingleResponse<Object>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<Object> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).deleteInformationPicture(picCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void deleteSound(BasePresenter presenter,String audioCode, Subscriber<AutoSingleResponse<Object>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<Object> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).deleteSound(audioCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }
    public void deleteInformationSound(BasePresenter presenter,String audioCode, Subscriber<AutoSingleResponse<Object>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<Object> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).deleteInformationSound(audioCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void deleteSprayPhoto(BasePresenter presenter,String picCode, Subscriber<AutoSingleResponse<Object>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<Object> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).deleteSprayPhoto(picCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void deleteSitePhoto(BasePresenter presenter,String picCode, Subscriber<AutoSingleResponse<Object>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<Object> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).deleteSitePhoto(picCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void deleteGroundPhoto(BasePresenter presenter,String picCode, Subscriber<AutoSingleResponse<Object>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<Object> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).deleteGroundPhoto(picCode).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void addTowerCheck(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairAddTowerCheckBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addTowerCheck(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void addLineCheck(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairAddTowerCheckBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addLineCheck(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void addWaterTest(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairAddTowerCheckBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addWaterTest(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void lineCheckList(BasePresenter presenter, String taskCode,int selectType,String lineId, Subscriber<AutoSingleResponse<TowerBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<TowerBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).lineCheckList(taskCode,selectType,lineId).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void addInsulatorClear(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairAddTowerCheckBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addInsulatorClear(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void addSiteSurvey(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairAddTowerCheckBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addSiteSurvey(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void updateGroundCheck(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairAddTowerCheckBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).updateGroundCheck(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void addGroundMout(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairAddTowerCheckBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addGroundMout(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

    public void addSpray(BasePresenter presenter, HashMap<String, Object> map, Subscriber<AutoSingleResponse<RepairAddTowerCheckBean>> subscriber) {
        if (!CommonUtils.is_Network_Available(PowerApplication.mApplication)) {
            if (subscriber != null) {
                AutoSingleResponse<RepairAddTowerCheckBean> s = new AutoSingleResponse<>(RepairConstantUtils.NET_ERROR, PowerApplication.getStringResource(R.string.net_error));
                subscriber.onNext(s);
            }
            return;
        }
        DataManager.getInstance(PowerApplication.getPowerApplication()).addSpray(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        presenter.mCompositeSubscription.add(subscriber);
    }

}
