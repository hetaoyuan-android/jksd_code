package com.glens.jksd.network;

import android.content.Context;

import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.bean.DetectionCountBean;
import com.glens.jksd.bean.LoginBean;
import com.glens.jksd.bean.RepairAddTowerCheckBean;
import com.glens.jksd.bean.RepairBillBean;
import com.glens.jksd.bean.RepairListBean;
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

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * 该类用来管理RetrofitApiService中对应的各种API接口，
 * 当做Retrofit和presenter中的桥梁，Activity就不用直接和retrofit打交道了
 */
public class DataManager {
    private RetrofitApiService mRetrofitService;
    private volatile static DataManager instance;

    private DataManager(Context context) {
        this.mRetrofitService = RetrofitUtil.getInstance(context).getRetrofitApiService();
    }

    //由于该对象会被频繁调用，采用单例模式，下面是一种线程安全模式的单例写法
    public static DataManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager(context);
                }
            }
        }
        return instance;
    }

    //将retrofit的业务方法映射到DataManager中，以后统一用该类来调用业务方法
    public Observable<UserInfo> getUserInfo(int uid) {
        return mRetrofitService.getUserInfo(uid);
    }

    public Observable<AutoSingleResponse<LoginBean>> login(HashMap<String, String> map) {
        return mRetrofitService.login(map);
    }

    /**
     * 检修列表
     *
     * @param taskName 选填
     * @param taskTime 选填
     * @param taskType 选填 0:全部 1：电阻 2：测温 3：测量
     * @param page     页数 必填
     * @param rows     条数 必填
     */
    public Observable<AutoSingleResponse<ResistanceBean>> detectionList(String taskName, String taskTime, int taskType, int page, int rows) {
        return mRetrofitService.detectionList(taskName, taskTime, taskType, page, rows);
    }

    /**
     * 检测列表底部数量
     *
     * @param taskName 选填
     * @param taskTime 选填
     * @param taskType 选填
     */
    public Observable<AutoSingleResponse<DetectionCountBean>> detectionCount(String taskName, String taskTime, int taskType) {
        return mRetrofitService.detectionCountList(taskName, taskTime, taskType);
    }

    /**
     * 接地电阻列表页
     *
     * @param lineVol
     * @param lineName
     * @param isInspected
     * @param towerNo
     * @param page        必填
     * @param rows        必填
     * @param taskCode    必填
     */
    public Observable<AutoSingleResponse<GroundResistanceListBean>> getGroundResistanceList(String lineVol, String lineName, String isInspected, String towerNo, int page, int rows, String taskCode) {
        return mRetrofitService.getGroundResistanceList(lineVol, lineName, isInspected, towerNo, page, rows, taskCode);
    }

    /**
     *  接地电阻详情列表页
     */
    public Observable<AutoSingleResponse<GroundResistanceDetailBean>> getGroundResistanceDetailList(String recodeCode) {
        return mRetrofitService.getGroundResistanceDetailList(recodeCode);
    }

    /**
     *  新增接地电阻
     */
    public Observable<AutoSingleResponse<AddGrounResistanceBean>> addGroundResistance(HashMap<String, Object> map) {
        return mRetrofitService.addGroundResistance(map);
    }

    /**
     *  新增接地电阻
     */
    public Observable<AutoSingleResponse<AddGrounResistanceBean>> addSoundRecord(HashMap<String, Object> map) {
        return mRetrofitService.addSoundRecord(map);
    }

    /**
     *  新增检修资料
     */
    public Observable<AutoSingleResponse<AddGrounResistanceBean>> addInformationRecord(HashMap<String, Object> map) {
        return mRetrofitService.addInformationRecord(map);
    }

    /**
     *  新增接地电阻带上传图片
     */
    public Observable<AutoSingleResponse<AddGrounResistanceBean>> addGroundResistanceAndImage(HashMap<String, Object> map, RequestBody file) {
        return mRetrofitService.addGroundResistanceAndImage(map, file);
    }



    /**
     * 红外测温列表
     *
     * @param taskCode 必填
     * @param page     必填
     * @param rows     必填
     */
    public Observable<AutoSingleResponse<InfraraedListBean>> getInfraredList(String lineVol, String lineName, String isInspected, String towerNo, int page, int rows, String taskCode) {
        return mRetrofitService.getInfraredList(lineVol, lineName, isInspected, towerNo, page, rows, taskCode);
    }

    /**
     *  红外测温详情页
     */
    public Observable<AutoSingleResponse<InfraredDetailBean>> getInfraredDetailList(String recodeCode) {
        return mRetrofitService.getInfraredDetailList(recodeCode);
    }

    /**
     *  新增红外测温
     */
    public Observable<AutoSingleResponse<AddGrounResistanceBean>> addInfrared(HashMap<String, Object> map) {
        return mRetrofitService.addInfrared(map);
    }


    /**
     * 交跨测量主页
     */
    public Observable<AutoSingleResponse<PayMeasureListBean>> getPayMeasureList(String lineVol, String lineName, String isInspected, Integer crossType, String exeUnitId, String startTowerNo, String endTowerNo, String taskCode, int page, int rows) {
        return mRetrofitService.getPayMeasureList(lineVol, lineName, isInspected, crossType, exeUnitId, startTowerNo, endTowerNo, taskCode, page, rows);
    }

    /**
     *  交跨测量详情页
     */
    public Observable<AutoSingleResponse<PayMeasureDetailBean>> getPayMeasureDetailList(String recodeCode) {
        return mRetrofitService.getPayMeasureDetailList(recodeCode);
    }

    /**
     *  新增交跨测量
     */
    public Observable<AutoSingleResponse<AddGrounResistanceBean>> addPayMeasure(HashMap<String, Object> map) {
        return mRetrofitService.addPayMeasure(map);
    }


    /**
     * 检测上传照片
     *
     * @param file    必填
     * @param picType 必填
     * @param remarks 必填
     */
    public Observable<ResponseBody> uploadImageOffLone(String file, String recodeCode, String taskCode, String picType, String remarks) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return mRetrofitService.uploadImageOffLone(requestFile, recodeCode, taskCode, picType, remarks);
    }

    /**
     * 检修列表
     * String taskName,String startDate,String endDate,
     * int overhaulType, @Query("createDeptId") String createDeptId, @Query("page") int page,
     * int rows, String taskContent, int isPowerCut,String powerOutage)
     */
    public Observable<AutoSingleResponse<RepairListBean>> checkList(String taskName, String startDate, String endDate, int overhaulType, String createDeptId, int page, int rows) {
        return mRetrofitService.checkList(taskName, startDate, endDate, overhaulType, createDeptId, page, rows);
    }


    public Observable<AutoSingleResponse<RepairBillBean>> repairBill(String taskCode) {
        return mRetrofitService.repairBill(taskCode);
    }

    public Observable<AutoSingleResponse<RepairTowerListBean>> repairTower(String startDate, String endDate, String taskCode) {
        return mRetrofitService.repairTower(startDate, endDate, taskCode);
    }

    public Observable<AutoSingleResponse<RepairTowerListBean>> repairTaskList(String startDate, String endDate, String taskCode) {
        return mRetrofitService.repairTaskList(startDate, endDate, taskCode);
    }

    public Observable<AutoSingleResponse<RepairTowerListBean>> insulatorClearList(String startDate, String endDate, String taskCode) {
        return mRetrofitService.insulatorClearList(startDate, endDate, taskCode);
    }

    public Observable<AutoSingleResponse<RepairTowerListBean>> traceCheckList(String startDate, String endDate, String taskCode) {
        return mRetrofitService.traceCheckList(startDate, endDate, taskCode);
    }

    public Observable<AutoSingleResponse<RepairTowerListBean>> rtvSprayList(String startDate, String endDate, String taskCode) {
        return mRetrofitService.rtvSprayList(startDate, endDate, taskCode);
    }

    public Observable<AutoSingleResponse<RepairTowerListBean>> hydrophobicTestList(String startDate, String endDate, String taskCode) {
        return mRetrofitService.hydrophobicTestList(startDate, endDate, taskCode);
    }

    public Observable<AutoSingleResponse<RepairTowerListBean>> siteSurveyList( String taskCode) {
        return mRetrofitService.siteSurveyList( taskCode);
    }

    public Observable<AutoSingleResponse<RepairTowerListBean>> soundList( String taskCode) {
        return mRetrofitService.soundList( taskCode);
    }

    public Observable<AutoSingleResponse<RepairInformationBean>> informationList(String taskCode) {
        return mRetrofitService.informationList( taskCode);
    }

    public Observable<AutoSingleResponse<RepairGroundBean>> groundList(String taskCode) {
        return mRetrofitService.groundList(taskCode);
    }

    public Observable<AutoSingleResponse<EquipmentBean>> equipmentList(String taskCode, int selectType) {
        return mRetrofitService.equipmentList(taskCode,selectType);
    }


    public Observable<AutoSingleResponse<RepairTowerDetailBean>> repairTowerDetail(String checkRecordCode) {
        return mRetrofitService.repairTowerDetail(checkRecordCode);
    }

    public Observable<AutoSingleResponse<DefectDetailBean>> repairTaskDetail(String checkRecordCode) {
        return mRetrofitService.repairTaskDetail(checkRecordCode);
    }
    public Observable<AutoSingleResponse<InsulatorDetailBean>> insulatorDetail(String checkRecordCode) {
        return mRetrofitService.insulatorDetail(checkRecordCode);
    }

    public Observable<AutoSingleResponse<InformationDetailBean>> informationDetail(String checkRecordCode) {
        return mRetrofitService.informationDetail(checkRecordCode);
    }

    public Observable<AutoSingleResponse<GroundDetailBean>> groundrDetail(String checkRecordCode) {
        return mRetrofitService.groundrDetail(checkRecordCode);
    }

    public Observable<AutoSingleResponse<LineCheckBean>> traceCheckDetail(String checkRecordCode) {
        return mRetrofitService.traceCheckDetail(checkRecordCode);
    }
    public Observable<AutoSingleResponse<RtvSprayDetailBean>> sprayDetail(String checkRecordCode) {
        return mRetrofitService.sprayDetail(checkRecordCode);
    }

    public Observable<AutoSingleResponse<WaterTestDetailBean>> waterTestDetail(String checkRecordCode) {
        return mRetrofitService.waterTestDetail(checkRecordCode);
    }

    public Observable<AutoSingleResponse<RepairTaskDetailBean>> siteSurveyDetail(String checkRecordCode) {
        return mRetrofitService.siteSurveyDetail(checkRecordCode);
    }

    public Observable<AutoSingleResponse<RecordDetailBean>> repairSoundDetail(String checkRecordCode) {
        return mRetrofitService.repairSoundDetail(checkRecordCode);
    }

    public Observable<AutoSingleResponse<UpRepairPhotoBean>> upTowerPhoto(RequestBody file, String taskCode, int picType, String remarks) {
        return mRetrofitService.upTowerPhoto(file, taskCode, picType, remarks);
    }

    public Observable<AutoSingleResponse<UpRepairPhotoBean>> upInformationPhoto(RequestBody file, String taskCode, int picType, String remarks) {
        return mRetrofitService.upInformationPhoto(file, taskCode, picType, remarks);
    }

    public Observable<AutoSingleResponse<UpRepairPhotoBean>> upSoundAudio(RequestBody file, String taskCode, String remarks) {
        return mRetrofitService.upSoundAudio(file, taskCode, remarks);
    }

    public Observable<AutoSingleResponse<UpRepairPhotoBean>> upInformationAudio(RequestBody file, String taskCode, String remarks) {
        return mRetrofitService.upInformationAudio(file, taskCode,remarks);
    }

    public Observable<AutoSingleResponse<UpRepairPhotoBean>> upInsulatorPhoto(RequestBody file, String taskCode, int picType, String remarks) {
        return mRetrofitService.upInsulatorPhoto(file, taskCode, picType, remarks);
    }


    public Observable<AutoSingleResponse<UpRepairPhotoBean>> upSprayPhoto(RequestBody file, String taskCode, int picType, String remarks) {
        return mRetrofitService.upSprayPhoto(file, taskCode, picType, remarks);
    }

    public Observable<AutoSingleResponse<UpRepairPhotoBean>> upGroundPhoto(RequestBody file, String taskCode, int picType, String remarks) {
        return mRetrofitService.upGroundPhoto(file, taskCode, picType, remarks);
    }

    public Observable<AutoSingleResponse<UpRepairPhotoBean>> upWaterTestPhoto(RequestBody file, String taskCode, String remarks) {
        return mRetrofitService.upWaterTestPhoto(file, taskCode, remarks);
    }

    public Observable<AutoSingleResponse<UpRepairPhotoBean>> upSiteSurveyPhoto(RequestBody file, String taskCode, String remarks) {
        return mRetrofitService.upSiteSurveyPhoto(file, taskCode, remarks);
    }

    public Observable<AutoSingleResponse<UpRepairPhotoBean>> upLineCheckPhoto(RequestBody file, String taskCode, String remarks) {
        return mRetrofitService.upLineCheckPhoto(file, taskCode, remarks);
    }

    public Observable<AutoSingleResponse<UpRepairPhotoBean>> upWorkRecordPhoto(RequestBody file, String taskCode, String remarks) {
        return mRetrofitService.upWorkRecordPhoto(file, taskCode, remarks);
    }

    public Observable<AutoSingleResponse<Object>> deleteTowerPhoto(String picCode) {
        return mRetrofitService.deleteTowerPhoto(picCode);
    }

    public Observable<AutoSingleResponse<Object>> deleteLineCheckPhoto(String picCode) {
        return mRetrofitService.deleteLineCheckPhoto(picCode);
    }

    public Observable<AutoSingleResponse<Object>> deleteWaterTestPhoto(String picCode) {
        return mRetrofitService.deleteWaterTestPhoto(picCode);
    }

    public Observable<AutoSingleResponse<Object>> deleteSoundPicture(String picCode) {
        return mRetrofitService.deleteSoundPicture(picCode);
    }

    public Observable<AutoSingleResponse<Object>> deleteInformationPicture(String picCode) {
        return mRetrofitService.deleteInformationPicture(picCode);
    }

    public Observable<AutoSingleResponse<Object>> deleteSound(String audioCode) {
        return mRetrofitService.deleteSound(audioCode);
    }

    public Observable<AutoSingleResponse<Object>> deleteInformationSound(String audioCode) {
        return mRetrofitService.deleteInformationSound(audioCode);
    }


    public Observable<AutoSingleResponse<Object>> deleteSprayPhoto(String picCode) {
        return mRetrofitService.deleteSprayPhoto(picCode);
    }

    public Observable<AutoSingleResponse<Object>> deleteSitePhoto(String picCode) {
        return mRetrofitService.deleteSitePhoto(picCode);
    }


    public Observable<AutoSingleResponse<Object>> deleteGroundPhoto(String picCode) {
        return mRetrofitService.deleteGroundPhoto(picCode);
    }


    public Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addTowerCheck(HashMap<String, Object> map) {
        return mRetrofitService.addTowerCheck(map);
    }
    public Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addLineCheck(HashMap<String, Object> map) {
        return mRetrofitService.addLineCheck(map);
    }

    public Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addWaterTest(HashMap<String, Object> map) {
        return mRetrofitService.addWaterTest(map);
    }

    public Observable<AutoSingleResponse<TowerBean>> lineCheckList(String taskCode, int selectType, String lineId) {
        return mRetrofitService.lineCheckList(taskCode,selectType,lineId);
    }


    public Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addInsulatorClear(HashMap<String, Object> map) {
        return mRetrofitService.addInsulatorClear(map);
    }

    public Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addSiteSurvey(HashMap<String, Object> map) {
        return mRetrofitService.addSiteSurvey(map);
    }

    public Observable<AutoSingleResponse<RepairAddTowerCheckBean>> updateGroundCheck(HashMap<String, Object> map) {
        return mRetrofitService.updateGroundCheck(map);
    }
    public Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addGroundMout(HashMap<String, Object> map) {
        return mRetrofitService.addGroundMout(map);
    }

    public Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addSpray(HashMap<String, Object> map) {
        return mRetrofitService.addSpray(map);
    }

    public Observable<ResponseBody> test(HashMap<String, String> map) {
        return mRetrofitService.test();
    }
}
