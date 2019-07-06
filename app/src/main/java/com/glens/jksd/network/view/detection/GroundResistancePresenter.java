package com.glens.jksd.network.view.detection;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.glens.jksd.R;
import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.deteect.GroundResistanceListBean;
import com.glens.jksd.bean.deteect.InfraraedListBean;
import com.glens.jksd.bean.deteect.PayMeasureListBean;
import com.glens.jksd.network.BasePresenter;
import com.glens.jksd.network.InterManage;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.view.CustomPopWindow;

import rx.Subscriber;

/**
 * 接地电阻的Presenter
 */
public class GroundResistancePresenter extends BasePresenter {
    private Context mContext;
    private GroundResistanceSearchView groundResistanceView;

    public GroundResistancePresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        super.BindPresentView(presentView);
        groundResistanceView = (GroundResistanceSearchView) presentView;
    }


    //接地电阻的分页列表
    public void getGroundResistanceList(String lineVol, String lineName, String isInspected, String towerNo, int page, int rows, String taskCode) {
        groundResistanceView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().getGroundResistanceList(this, lineVol, lineName, isInspected, towerNo, page, rows, taskCode, new Subscriber<AutoSingleResponse<GroundResistanceListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                groundResistanceView.hideDialog();
                groundResistanceView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<GroundResistanceListBean> groundResistanceResponse) {
                Log.e("presenter", groundResistanceResponse.toString());
                groundResistanceView.hideDialog();
                if (groundResistanceResponse.isResult()) {
                    groundResistanceView.onSuccess(groundResistanceResponse.getData(), groundResistanceResponse.getMsg());
                    groundResistanceView.setGroundBottomText(groundResistanceResponse.getData().getTotal(), groundResistanceResponse.getData().getUntreatedCnt(), groundResistanceResponse.getData().getHandleCnt());
                } else {
                    groundResistanceView.showDialog(groundResistanceResponse.getMsg());
                    groundResistanceView.hideDialog();

                }
            }
        });
    }

    // 红外测温列表
    public void getInfraredList(String lineVol, String lineName, String isInspected, String towerNo, int page, int rows, String taskCode) {
        groundResistanceView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().getInfraredList(this, lineVol, lineName, isInspected, towerNo, page, rows, taskCode, new Subscriber<AutoSingleResponse<InfraraedListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                groundResistanceView.hideDialog();
                groundResistanceView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<InfraraedListBean> infraraedResponse) {
                Log.e("infraraedPresenter", infraraedResponse.toString());
                groundResistanceView.hideDialog();
                if (infraraedResponse.isResult()) {
                    groundResistanceView.onSuccess(infraraedResponse.getData(), infraraedResponse.getMsg());
                    groundResistanceView.setGroundBottomText(infraraedResponse.getData().getTotal(), infraraedResponse.getData().getUntreatedCnt(), infraraedResponse.getData().getHandleCnt());
                } else {
                    groundResistanceView.showDialog(infraraedResponse.getMsg());
                    groundResistanceView.hideDialog();
                }


            }
        });
    }

    // 交跨测量列表
    public void getPayMeasureList(String lineVol, String lineName, String isInspected, Integer crossType, String exeUnitId, String startTowerNo, String endTowerNo, String taskCode, int page, int rows) {
        groundResistanceView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().getPayMeasureList(this, lineVol, lineName, isInspected, crossType, exeUnitId, startTowerNo, endTowerNo, taskCode, page, rows, new Subscriber<AutoSingleResponse<PayMeasureListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                groundResistanceView.hideDialog();
                groundResistanceView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<PayMeasureListBean> payMeasureResponse) {
                Log.e("getPayMeasureList", payMeasureResponse.toString());
                groundResistanceView.hideDialog();
                if (payMeasureResponse.isResult()) {
                    groundResistanceView.onSuccess(payMeasureResponse.getData(), payMeasureResponse.getMsg());
                    groundResistanceView.setGroundBottomText(payMeasureResponse.getData().getTotal(), payMeasureResponse.getData().getUntreatedCnt(), payMeasureResponse.getData().getHandleCnt());
                } else {
                    groundResistanceView.showDialog(payMeasureResponse.getMsg());
                    groundResistanceView.hideDialog();
                }
            }
        });
    }

    // 接地电阻处理状态的搜索
    public void groundProcessState(View view, CustomPopWindow popWindow, BaseActivity activity) {
        View.OnClickListener listener = v -> {
            switch (v.getId()) {
                case R.id.rb_ground_done:
                    groundResistanceView.setTaskType(RepairConstantUtils.GROUND_DONE);
                    break;
                case R.id.rb_ground_no_dispose:
                    groundResistanceView.setTaskType(RepairConstantUtils.GROUND_NO_DISPOSE);
                    break;
                case R.id.rb_ground_all:
//                    groundResistanceView.setTaskType(2);
//                    startSearch(2, popWindow,activity);
                    getGroundResistanceList("", "", "", "", 0, 10, groundResistanceView.getTaskCode());
                    popWindow.dissmiss();
                    break;
                case R.id.rb_ground_sure:
                    startGroundSearch(groundResistanceView.geTaskType(), popWindow, activity);
                    break;
            }
        };

        RadioGroup rb = view.findViewById(R.id.rb_detection_type);
        switch (groundResistanceView.geTaskType()) {
            case 0:
                break;
            case 1:
                rb.check(R.id.rb_ground_done);
                break;
            case 2:
                rb.check(R.id.rb_ground_no_dispose);
                break;
            default:
                Log.e("handleLogic", "非法参数类型" + groundResistanceView.geTaskType());
                break;
        }
        view.findViewById(R.id.rb_ground_done).setOnClickListener(listener);
        view.findViewById(R.id.rb_ground_no_dispose).setOnClickListener(listener);
        view.findViewById(R.id.rb_ground_all).setOnClickListener(listener);
        view.findViewById(R.id.rb_ground_sure).setOnClickListener(listener);

    }

    // 接地电阻开始搜索
    private void startGroundSearch(int type, CustomPopWindow popWindow, BaseActivity activity) {
        if (popWindow != null) {
            popWindow.dissmiss();
        }
        getGroundResistanceList("", "", String.valueOf(type), "", 0, 10, groundResistanceView.getTaskCode());
        Log.i("hetaostartSearch", "" + String.valueOf(type));
    }

    // 交跨测量 处理状态的搜索
    public void MeasureProcessState(View view, CustomPopWindow popWindow, BaseActivity activity) {
        View.OnClickListener listener = v -> {
            switch (v.getId()) {
                case R.id.rb_ground_done:
                    groundResistanceView.setTaskType(RepairConstantUtils.GROUND_DONE);
                    break;
                case R.id.rb_ground_no_dispose:
                    groundResistanceView.setTaskType(RepairConstantUtils.GROUND_NO_DISPOSE);
                    break;
                case R.id.rb_ground_all:
                    getPayMeasureList("", "", "", null, "", "", "", groundResistanceView.getTaskCode(), 0, 10);
                    popWindow.dissmiss();
                    break;
                case R.id.rb_ground_sure:
                    startMeasureSearch(groundResistanceView.geTaskType(), popWindow, activity);
                    break;
            }
        };

        RadioGroup rb = view.findViewById(R.id.rb_detection_type);
        switch (groundResistanceView.geTaskType()) {
            case 0:
                break;
            case 1:
                rb.check(R.id.rb_ground_done);
                break;
            case 2:
                rb.check(R.id.rb_ground_no_dispose);
                break;
            default:
                Log.e("handleLogic", "非法参数类型" + groundResistanceView.geTaskType());
                break;
        }
        view.findViewById(R.id.rb_ground_done).setOnClickListener(listener);
        view.findViewById(R.id.rb_ground_no_dispose).setOnClickListener(listener);
        view.findViewById(R.id.rb_ground_all).setOnClickListener(listener);
        view.findViewById(R.id.rb_ground_sure).setOnClickListener(listener);

    }

    // 接地电阻开始搜索
    private void startMeasureSearch(int type, CustomPopWindow popWindow, BaseActivity activity) {
        if (popWindow != null) {
            popWindow.dissmiss();
        }
        getPayMeasureList("", "", String.valueOf(type), null, "", "", "", groundResistanceView.getTaskCode(), 0, 10);
        Log.i("hetaostartSearch", "" + String.valueOf(type));
    }

    // 交跨测量 处理状态的搜索
    public void MeasureDepartProcessState(View view, CustomPopWindow popWindow, BaseActivity activity) {
        View.OnClickListener listener = v -> {
            switch (v.getId()) {
                case R.id.rb_measure_road:
                    groundResistanceView.setTaskType(RepairConstantUtils.MEASURE_ROAD);
                    break;
                case R.id.rb_measure_railway:
                    groundResistanceView.setTaskType(RepairConstantUtils.MEASURE_RAILWAY);
                    break;
                case R.id.rb_measure_imp_channel:
                    groundResistanceView.setTaskType(RepairConstantUtils.MEASURE_IMP_CHANNEL);
                    break;
                case R.id.rb_measure_river:
                    groundResistanceView.setTaskType(RepairConstantUtils.MEASURE_RIVER);
                    break;
                case R.id.rb_ground_all:
                    getPayMeasureList("", "", "", null, "", "", "", groundResistanceView.getTaskCode(), 0, 10);
                    popWindow.dissmiss();
                    break;
                case R.id.rb_ground_sure:
                    startMeasureDepartSearch(groundResistanceView.geTaskType(), popWindow, activity);
                    break;
            }
        };

        RadioGroup rb = view.findViewById(R.id.rb_detection_type);
        switch (groundResistanceView.geTaskType()) {
            case 0:
                break;
            case 1:
                rb.check(R.id.rb_measure_road);
                break;
            case 2:
                rb.check(R.id.rb_measure_railway);
                break;
            case 3:
                rb.check(R.id.rb_measure_imp_channel);
                break;
            case 4:
                rb.check(R.id.rb_measure_river);
                break;
            default:
                Log.e("handleLogic", "非法参数类型" + groundResistanceView.geTaskType());
                break;
        }
        view.findViewById(R.id.rb_measure_road).setOnClickListener(listener);
        view.findViewById(R.id.rb_measure_railway).setOnClickListener(listener);
        view.findViewById(R.id.rb_measure_imp_channel).setOnClickListener(listener);
        view.findViewById(R.id.rb_measure_river).setOnClickListener(listener);
        view.findViewById(R.id.rb_ground_all).setOnClickListener(listener);
        view.findViewById(R.id.rb_ground_sure).setOnClickListener(listener);

    }

    // 接地电阻开始搜索
    private void startMeasureDepartSearch(int type, CustomPopWindow popWindow, BaseActivity activity) {
        if (popWindow != null) {
            popWindow.dissmiss();
        }
        getPayMeasureList("", "", "", type, "", "", "", groundResistanceView.getTaskCode(), 0, 10);
        Log.i("hetaostartSearch", "" + type);
    }

    // 红外测温 处理状态的搜索
    public void infraredDepartProcessState(View view, CustomPopWindow popWindow, BaseActivity activity) {
        View.OnClickListener listener = v -> {
            switch (v.getId()) {
                case R.id.rb_ground_done:
                    groundResistanceView.setTaskType(RepairConstantUtils.INFRARED_DONE);
                    break;
                case R.id.rb_ground_no_dispose:
                    groundResistanceView.setTaskType(RepairConstantUtils.INFRARED_NO_DISPOSE);
                    break;
                case R.id.rb_ground_all:
                    getInfraredList("", "", "", "", 0, 10, groundResistanceView.getTaskCode());
                    popWindow.dissmiss();
                    break;
                case R.id.rb_ground_sure:
                    startInfraredProcessStateSearch(groundResistanceView.geTaskType(), popWindow, activity);
                    break;
            }
        };

        RadioGroup rb = view.findViewById(R.id.rb_detection_type);
        switch (groundResistanceView.geTaskType()) {
            case 0:
                break;
            case 1:
                rb.check(R.id.rb_ground_done);
                break;
            case 2:
                rb.check(R.id.rb_ground_no_dispose);
                break;
            default:
                Log.e("handleLogic", "非法参数类型" + groundResistanceView.geTaskType());
                break;
        }
        view.findViewById(R.id.rb_ground_done).setOnClickListener(listener);
        view.findViewById(R.id.rb_ground_no_dispose).setOnClickListener(listener);
        view.findViewById(R.id.rb_ground_all).setOnClickListener(listener);
        view.findViewById(R.id.rb_ground_sure).setOnClickListener(listener);

    }

    // 红外测温 开始搜索
    private void startInfraredProcessStateSearch(int type, CustomPopWindow popWindow, BaseActivity activity) {
        if (popWindow != null) {
            popWindow.dissmiss();
        }
        getInfraredList("", "", String.valueOf(type), "", 0, 10, groundResistanceView.getTaskCode());
        Log.i("hetaostartSearch", "" + String.valueOf(type));
    }
}
