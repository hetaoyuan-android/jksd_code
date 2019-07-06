package com.glens.jksd.network;

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
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitApiService {

    @GET("userinfo")
    Observable<UserInfo> getUserInfo(@Query("uid") int uid);

    @Headers("Connection: close")
    @POST("olisServices/sm/login")
    @FormUrlEncoded
        //登陆
    Observable<AutoSingleResponse<LoginBean>> login(@FieldMap Map<String, String> map);

    @Headers("Connection: close")
    @GET("detection/detectionTaskList")
        //检测分页列表
    Observable<AutoSingleResponse<ResistanceBean>> detectionList(@Query("taskName") String taskName, @Query("taskTime") String taskTime,
                                                                 @Query("taskType") int taskType, @Query("page") int page, @Query("rows") int rows);

    @Headers("Connection: close")
    @GET("detection/detectionTaskCount")
        //检测分页底部数量
    Observable<AutoSingleResponse<DetectionCountBean>> detectionCountList(@Query("taskName") String taskName, @Query("taskTime") String taskTime,
                                                                          @Query("taskType") int taskType);

    // 接地电阻分页列表
    @Headers("Connection: close")
    @GET("detection/resistanceList")
    Observable<AutoSingleResponse<GroundResistanceListBean>> getGroundResistanceList(@Query("lineVol") String lineVol, @Query("lineName") String lineName, @Query("isInspected") String isInspected, @Query("towerNo") String towerNo, @Query("page") int page, @Query("rows") int rows, @Query("taskCode") String taskCode);

    // 接地电阻详情页
    @Headers("Connection: close")
    @GET("detection/resistanceMsg")
    Observable<AutoSingleResponse<GroundResistanceDetailBean>> getGroundResistanceDetailList(@Query("recodeCode") String recodeCode);

    // 新增接地电阻
    @Headers("Connection: close")
    @POST("detection/addResistance ")
    @FormUrlEncoded
    Observable<AutoSingleResponse<AddGrounResistanceBean>> addGroundResistance(@FieldMap HashMap<String, Object> map);

    // 新增开收工录音
    @Headers("Connection: close")
    @POST("overhaul/addSound")
    @FormUrlEncoded
    Observable<AutoSingleResponse<AddGrounResistanceBean>> addSoundRecord(@FieldMap HashMap<String, Object> map);

    // 新增检修资料
    @Headers("Connection: close")
    @POST("overhaul/addOverhaulDatum")
    @FormUrlEncoded
    Observable<AutoSingleResponse<AddGrounResistanceBean>> addInformationRecord(@FieldMap HashMap<String, Object> map);


    // 新增接地电阻带图片
    @Multipart
    @Headers("Connection: close")
    @POST("detection/addResistance ")
    Observable<AutoSingleResponse<AddGrounResistanceBean>> addGroundResistanceAndImage(@FieldMap HashMap<String, Object> map, @Part("file\"; filename=\"image.jpg") RequestBody file);

    //红外测温记录列表
    @Headers("Connection: close")
    @GET("detection/temperatureRecordList")
    Observable<AutoSingleResponse<InfraraedListBean>> getInfraredList(@Query("lineVol") String lineVol, @Query("lineName") String lineName, @Query("isInspected") String isInspected,
                                                                      @Query("towerNo") String towerNo, @Query("page") int page, @Query("rows") int rows, @Query("taskCode") String taskCode);

    // 红外测温详情页
    @Headers("Connection: close")
    @GET("detection/temperatureRecordMsg")
    Observable<AutoSingleResponse<InfraredDetailBean>> getInfraredDetailList(@Query("recodeCode") String recodeCode);


     // 新增红外测温
    @Headers("Connection: close")
    @POST("detection/addTemperatureRecord")
    @FormUrlEncoded
    Observable<AutoSingleResponse<AddGrounResistanceBean>> addInfrared(@FieldMap HashMap<String, Object> map);

    // 交跨测量记录列表
    @Headers("Connection: close")
    @GET("detection/measureRecordList")
    Observable<AutoSingleResponse<PayMeasureListBean>> getPayMeasureList(@Query("lineVol") String lineVol, @Query("lineName") String lineName, @Query("isInspected") String isInspected,
                                                                         @Query("crossType") Integer crossType, @Query("exeUnitId") String exeUnitId, @Query("startTowerNo") String startTowerNo, @Query("endTowerNo") String endTowerNo, @Query("taskCode") String taskCode, @Query("page") int page, @Query("rows") int rows);

    // 交跨测量详情页
    @Headers("Connection: close")
    @GET("detection/measureRecordMsg ")
    Observable<AutoSingleResponse<PayMeasureDetailBean>> getPayMeasureDetailList(@Query("recodeCode") String recodeCode);

    // 新增交跨测量
    @Headers("Connection: close")
    @POST("detection/addMeasureRecord")
    @FormUrlEncoded
    Observable<AutoSingleResponse<AddGrounResistanceBean>> addPayMeasure(@FieldMap HashMap<String, Object> map);

    //上传图片停运图片
    @Multipart
    @POST("detection/uploadDetectionPicture")
    Observable<ResponseBody> uploadImageOffLone(@Part("file\"; filename=\"image.jpg") RequestBody file, @Query("recodeCode") String recodeCode,
                                                @Query("taskCode") String taskCode, @Query("picType") String picType, @Query("remarks") String remarks);

    @Headers("Connection: close")
    @GET("xiandu/category/wow")
        //登陆
    Observable<ResponseBody> test();

    /**
     * taskName	选填	任务名称
     * startDate	选填	开始时间
     * endDate	选填	结束时间
     * overhaulType	选填	检修分类 1：技改 2：大修 3：日常维护 int
     * createDeptId	选填	创建部门id
     * page	必填	当前页数 int
     * rows	必填	每页条数 int
     * taskContent	选填	工作内容 android 端无
     * isPowerCut	选填	是否停电 int,android 端无
     * powerOutage	选填	停电范围    android 端无
     */
    @Headers("Connection: close")
    @GET("overhaul/overhaulTaskList")
    //检修分页列表
    Observable<AutoSingleResponse<RepairListBean>> checkList(@Query("taskName") String taskName, @Query("startDate") String startDate,
                                                             @Query("endDate") String endDate, @Query("overhaulType") int overhaulType,
                                                             @Query("createDeptId") String createDeptId, @Query("page") int page,
                                                             @Query("rows") int rows);

    @Headers("Connection: close")
    @GET("overhaul/overhaulTaskMsg")
        //任务台账
    Observable<AutoSingleResponse<RepairBillBean>> repairBill(@Query("taskCode") String taskCode);

    /**
     * 登杆检查列表
     *
     * @param taskCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/lamppostList")
    Observable<AutoSingleResponse<RepairTowerListBean>> repairTower(@Query("startDate") String startDate, @Query("endDate") String endDate,
                                                                    @Query("taskCode") String taskCode);

    /**
     * 消缺工作列表
     *
     * @param taskCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/rectifyDefectList")
    Observable<AutoSingleResponse<RepairTowerListBean>> repairTaskList(@Query("startDate") String startDate, @Query("endDate") String endDate,
                                                                       @Query("taskCode") String taskCode);

    /**
     * 绝缘子清扫列表
     *
     * @param taskCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/insulatorClearList")
    Observable<AutoSingleResponse<RepairTowerListBean>> insulatorClearList(@Query("startDate") String startDate, @Query("endDate") String endDate,
                                                                           @Query("taskCode") String taskCode);

    /**
     * 走线检查列表
     *
     * @param taskCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/traceCheckList")
    Observable<AutoSingleResponse<RepairTowerListBean>> traceCheckList(@Query("startDate") String startDate, @Query("endDate") String endDate,
                                                                       @Query("taskCode") String taskCode);

    /**
     * RTV喷涂列表
     *
     * @param taskCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/rtvSprayList")
    Observable<AutoSingleResponse<RepairTowerListBean>> rtvSprayList(@Query("startDate") String startDate, @Query("endDate") String endDate,
                                                                     @Query("taskCode") String taskCode);

    /**
     * 憎水性实验列表
     *
     * @param taskCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/hydrophobicTestList")
    Observable<AutoSingleResponse<RepairTowerListBean>> hydrophobicTestList(@Query("startDate") String startDate, @Query("endDate") String endDate,
                                                                            @Query("taskCode") String taskCode);

    /**
     * 现场勘察列表
     *
     * @param taskCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/siteSurveyList")
    Observable<AutoSingleResponse<RepairTowerListBean>> siteSurveyList(@Query("taskCode") String taskCode);

    /**
     * 开收工录音列表
     *
     * @param taskCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/soundList")
    Observable<AutoSingleResponse<RepairTowerListBean>> soundList(@Query("taskCode") String taskCode);

    /**
     * 检修资料列表
     *
     * @param taskCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/overhaulDatumList")
    Observable<AutoSingleResponse<RepairInformationBean>> informationList(@Query("taskCode") String taskCode);

    /**
     * 接地线管理列表
     *
     * @param taskCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/groundWireMountList")
    Observable<AutoSingleResponse<RepairGroundBean>> groundList(@Query("taskCode") String taskCode);

    /**
     * 线路杆塔列表
     *
     * @param taskCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/overhaulLineList")
    Observable<AutoSingleResponse<EquipmentBean>> equipmentList(@Query("taskCode") String taskCode, @Query("selectType") int selectType);

    /**
     * 选择杆塔
     */
    @Headers("Connection: close")
    @GET("overhaul/overhaulTowerList")
    Observable<AutoSingleResponse<TowerBean>> lineCheckList(@Query("taskCode") String taskCode, @Query("selectType") int selectType,
                                                            @Query("lineId") String lineId);


    /**
     * 开收工录音详情
     *
     * @param recordCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/soundMsg")
    Observable<AutoSingleResponse<RecordDetailBean>> repairSoundDetail(@Query("recordCode") String recordCode);

    /**
     * 登杆检查详情
     *
     * @param checkRecordCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/lamppostMsg")
    Observable<AutoSingleResponse<RepairTowerDetailBean>> repairTowerDetail(@Query("checkRecordCode") String checkRecordCode);

    /**
     * 消缺工作详情
     *
     * @param recordCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/rectifyDefectMsg")
    Observable<AutoSingleResponse<DefectDetailBean>> repairTaskDetail(@Query("recordCode") String recordCode);

    /**
     * 绝缘子清扫详情
     *
     * @param recordCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/insulatorClearMsg")
    Observable<AutoSingleResponse<InsulatorDetailBean>> insulatorDetail(@Query("recordCode") String recordCode);

    /**
     * 检修资料详情
     *
     * @param datumCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/overhaulDatumMsg")
    Observable<AutoSingleResponse<InformationDetailBean>> informationDetail(@Query("datumCode") String datumCode);

    /**
     * 接地线管理详情
     *
     * @param recordCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/groundWireMountMsg")
    Observable<AutoSingleResponse<GroundDetailBean>> groundrDetail(@Query("recordCode") String recordCode);

    /**
     * 走线检查详情
     *
     * @param recordCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/traceCheckMsg")
    Observable<AutoSingleResponse<LineCheckBean>> traceCheckDetail(@Query("recordCode") String recordCode);

    /**
     * RTV喷涂详情
     *
     * @param recordCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/rtvSprayMsg")
    Observable<AutoSingleResponse<RtvSprayDetailBean>> sprayDetail(@Query("recordCode") String recordCode);

    /**
     * 憎水性实验详情
     *
     * @param recordCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/hydrophobicTestMsg")
    Observable<AutoSingleResponse<WaterTestDetailBean>> waterTestDetail(@Query("recordCode") String recordCode);

    /**
     * 现场查勘详情
     *
     * @param recordCode 必填
     */
    @Headers("Connection: close")
    @GET("overhaul/siteSurveyMsg")
    Observable<AutoSingleResponse<RepairTaskDetailBean>> siteSurveyDetail(@Query("recordCode") String recordCode);


    /**
     * 上传开工收录音频
     *
     */
    @Headers("Connection: close")
    @Multipart
    @POST("overhaul/uploadSoundAudio")
    Observable<AutoSingleResponse<UpRepairPhotoBean>> upSoundAudio(@Part("file\"; filename=\"video.mp4") RequestBody file, @Query("taskCode") String taskCode, @Query("remarks") String remarks);

    /**
     * 上传检修资料音频
     *
     */
    @Headers("Connection: close")
    @Multipart
    @POST("overhaul/uploadOverhaulDatumAudio")
    Observable<AutoSingleResponse<UpRepairPhotoBean>> upInformationAudio(@Part("file\"; filename=\"video.mp4") RequestBody file, @Query("taskCode") String taskCode,@Query("remarks") String remarks);


    /**
     * 上传绝缘子清扫照片
     * 照片类型 （1：清扫前 2：清扫后） int
     */
    @Headers("Connection: close")
    @Multipart
    @POST("overhaul/uploadInsulatorClearPicture")
    Observable<AutoSingleResponse<UpRepairPhotoBean>> upInsulatorPhoto(@Part("file\"; filename=\"image.jpg") RequestBody file, @Query("taskCode") String taskCode,
                                                                    @Query("picType") int picType, @Query("remarks") String remarks);

    /**
     * 上传RTV喷涂照片
     *  1：喷涂前照片 2：第一道喷涂照片 3：第二道喷涂照片
     */
    @Headers("Connection: close")
    @Multipart
    @POST("overhaul/uploadRtvSprayPicture")
    Observable<AutoSingleResponse<UpRepairPhotoBean>> upSprayPhoto(@Part("file\"; filename=\"image.jpg") RequestBody file, @Query("taskCode") String taskCode,
                                                                    @Query("picType") int picType, @Query("remarks") String remarks);

    /**
     * 上传接地线管理照片
     *
     */
    @Headers("Connection: close")
    @Multipart
    @POST("overhaul/uploadGroundWireMountPicture")
    Observable<AutoSingleResponse<UpRepairPhotoBean>> upGroundPhoto(@Part("file\"; filename=\"image.jpg") RequestBody file, @Query("taskCode") String taskCode,
                                                                   @Query("picType") int picType, @Query("remarks") String remarks);

    /**
     * 上传憎水性实验照片
     *
     */
    @Headers("Connection: close")
    @Multipart
    @POST("overhaul/uploadHydrophobicTestPicture")
    Observable<AutoSingleResponse<UpRepairPhotoBean>> upWaterTestPhoto(@Part("file\"; filename=\"image.jpg") RequestBody file, @Query("taskCode") String taskCode, @Query("remarks") String remarks);

    /**
     * 上传登杆检查照片
     *
     */
    @Headers("Connection: close")
    @Multipart
    @POST("overhaul/uploadLamppostPicture")
    Observable<AutoSingleResponse<UpRepairPhotoBean>> upTowerPhoto(@Part("file\"; filename=\"image.jpg") RequestBody file, @Query("taskCode") String taskCode,
                                                                   @Query("picType") int picType, @Query("remarks") String remarks);

    /**
     * 上传检修资料照片
     *
     */
    @Headers("Connection: close")
    @Multipart
    @POST("overhaul/uploadOverhaulDatumPicture")
    Observable<AutoSingleResponse<UpRepairPhotoBean>> upInformationPhoto(@Part("file\"; filename=\"image.jpg") RequestBody file, @Query("taskCode") String taskCode,
                                                                        @Query("picType") int picType,@Query("remarks") String remarks);

    /**
     * 上传现场查勘照片
     *
     */
    @Headers("Connection: close")
    @Multipart
    @POST("overhaul/uploadSiteSurveyPicture")
    Observable<AutoSingleResponse<UpRepairPhotoBean>> upSiteSurveyPhoto(@Part("file\"; filename=\"image.jpg") RequestBody file, @Query("taskCode") String taskCode,
                                                                   @Query("remarks") String remarks);

    /**
     * 上传走线检查照片
     *
     */
    @Headers("Connection: close")
    @Multipart
    @POST("overhaul/uploadTraceCheckPicture")
    Observable<AutoSingleResponse<UpRepairPhotoBean>> upLineCheckPhoto(@Part("file\"; filename=\"image.jpg") RequestBody file, @Query("taskCode") String taskCode,
                                                                        @Query("remarks") String remarks);

    /**
     * 上传开工收录照片
     *
     */
    @Headers("Connection: close")
    @Multipart
    @POST("overhaul/uploadSoundPicture")
    Observable<AutoSingleResponse<UpRepairPhotoBean>> upWorkRecordPhoto(@Part("file\"; filename=\"image.jpg") RequestBody file, @Query("taskCode") String taskCode,
                                                                       @Query("remarks") String remarks);

    /**
     * 删除检修资料照片
     *
     * @param picCode 必填
     */
    @Headers("Connection: close")
    @DELETE("overhaul/deleteOverhaulDatumPicture")
    Observable<AutoSingleResponse<Object>> deleteInformationPicture(@Query("picCode") String picCode);

    /**
     * 删除开工收录照片
     *
     * @param picCode 必填
     */
    @Headers("Connection: close")
    @DELETE("overhaul/deleteSoundPicture")
    Observable<AutoSingleResponse<Object>> deleteSoundPicture(@Query("picCode") String picCode);

    /**
     * 删除开工收录音频
     *
     * @param audioCode 必填
     */
    @Headers("Connection: close")
    @DELETE("overhaul/deleteSoundAudio")
    Observable<AutoSingleResponse<Object>> deleteSound(@Query("audioCode") String audioCode);

    /**
     * 删除检修资料音频
     *
     * @param audioCode 必填
     */
    @Headers("Connection: close")
    @DELETE("overhaul/deleteOverhaulDatumAudio")
    Observable<AutoSingleResponse<Object>> deleteInformationSound(@Query("audioCode") String audioCode);

    /**
     * 删除憎水性实验照片
     *
     * @param picCode 必填
     */
    @Headers("Connection: close")
    @DELETE("overhaul/deleteHydrophobicTestPicture")
    Observable<AutoSingleResponse<Object>> deleteWaterTestPhoto(@Query("picCode") String picCode);

    /**
     * 删除走线检查照片
     *
     * @param picCode 必填
     */
    @Headers("Connection: close")
    @DELETE("overhaul/deleteTraceCheckPicture")
    Observable<AutoSingleResponse<Object>> deleteLineCheckPhoto(@Query("picCode") String picCode);

    /**
     * 删除RTV喷涂照片
     *
     * @param picCode 必填
     */
    @Headers("Connection: close")
    @DELETE("overhaul/deleteRtvSprayPicture")
    Observable<AutoSingleResponse<Object>> deleteSprayPhoto(@Query("picCode") String picCode);

    /**
     * 删除登杆检查照片
     *
     * @param picCode 必填
     */
    @Headers("Connection: close")
    @DELETE("overhaul/deleteLamppostPicture")
    Observable<AutoSingleResponse<Object>> deleteTowerPhoto(@Query("picCode") String picCode);

    /**
     * 删除现场勘察照片
     *
     * @param picCode 必填
     */
    @Headers("Connection: close")
    @DELETE("overhaul/deleteSiteSurveyPicture")
    Observable<AutoSingleResponse<Object>> deleteSitePhoto(@Query("picCode") String picCode);

    /**
     * 删除接地线管理照片
     *
     * @param picCode 必填
     */
    @Headers("Connection: close")
    @DELETE("overhaul/deleteGroundWireMountPicture")
    Observable<AutoSingleResponse<Object>> deleteGroundPhoto(@Query("picCode") String picCode);

    /**
     * 新增登杆检查
     * lineId	必填	线路id
     * lineName	必填	线路名称
     * towerId	必填	杆塔id
     * towerNo	必填	杆号
     * taskCode	必填	任务编码
     * lineVol	必填	电压等级
     * phase	必填	相别 0:A 1:B 2:C
     * textName	选填	内容描述
     * picCodes	选填	上传照片编号(字符串多个用逗号分隔)
     */
    @Headers("Connection: close")
    @POST("overhaul/addLamppost")
    @FormUrlEncoded
    Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addTowerCheck(@FieldMap HashMap<String, Object> map);

    /**
     * 新增走线检查
     */
    @Headers("Connection: close")
    @POST("overhaul/addTraceCheck")
    @FormUrlEncoded
    Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addLineCheck(@FieldMap HashMap<String, Object> map);

    /**
     * 新增憎水性实验
     */
    @Headers("Connection: close")
    @POST("overhaul/addHydrophobicTest")
    @FormUrlEncoded
    Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addWaterTest(@FieldMap HashMap<String, Object> map);


    /**
     * 新增绝缘子清扫
     * lineId	必填	线路id
     * lineName	必填	线路名称
     * towerId	必填	杆塔id
     * towerNo	必填	杆号
     * taskCode	必填	任务编码
     * lineVol	必填	电压等级
     * phase	必填	相别 0:A 1:B 2:C
     * textName	选填	内容描述
     * picCodes	选填	上传照片编号(字符串多个用逗号分隔)
     */
    @Headers("Connection: close")
    @POST("overhaul/addInsulatorClear")
    @FormUrlEncoded
    Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addInsulatorClear(@FieldMap HashMap<String, Object> map);


    /**
     * 新增现场查勘
     * taskCode	必填	任务编号
     * lineId	必填	线路id
     * towerId	必填	杆塔id
     * workContent	必填	安全措施
     * surveyManId	必填	查勘人id
     * picCodes	选填	上传照片编号(字符串多个用逗号分隔)
     */
    @Headers("Connection: close")
    @POST("overhaul/addSiteSurvey")
    @FormUrlEncoded
    Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addSiteSurvey(@FieldMap HashMap<String, Object> map);

    /**
     * 编辑接地线管理
     */
    @Headers("Connection: close")
    @POST("overhaul/updateGroundWireMount")
    @FormUrlEncoded
    Observable<AutoSingleResponse<RepairAddTowerCheckBean>> updateGroundCheck(@FieldMap HashMap<String, Object> map);

    /**
     * 新增接地线挂接
     */
    @Headers("Connection: close")
    @POST("overhaul/addGroundWireMount")
    @FormUrlEncoded
    Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addGroundMout(@FieldMap HashMap<String, Object> map);

    /**
     * 新增RTV喷涂
     */
    @Headers("Connection: close")
    @POST("overhaul/addRtvSpray")
    @FormUrlEncoded
    Observable<AutoSingleResponse<RepairAddTowerCheckBean>> addSpray(@FieldMap HashMap<String, Object> map);


}
