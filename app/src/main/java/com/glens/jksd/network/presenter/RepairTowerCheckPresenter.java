package com.glens.jksd.network.presenter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.glens.jksd.R;
import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.bean.RepairTowerListBean;
import com.glens.jksd.network.BasePresenter;
import com.glens.jksd.network.InterManage;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.RepairTowerCheckView;
import com.glens.jksd.utils.LogUtil;
import com.glens.jksd.utils.RepairConstantUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;

import rx.Subscriber;

/**
 * Created by wkc on 2019/6/10.
 */
public class RepairTowerCheckPresenter extends BasePresenter {
    private Context mContext;
    private RepairTowerCheckView mRepairListView;


    public RepairTowerCheckPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        super.BindPresentView(presentView);
        mRepairListView = (RepairTowerCheckView) presentView;
    }

    public void getRepairTower(String startDate, String endDate, String taskCode) {
        mRepairListView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().getRepairTower(this, startDate, endDate, taskCode, new Subscriber<AutoSingleResponse<RepairTowerListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mRepairListView.hideDialog();
                mRepairListView.onError(e.getMessage() + "请求失败！");

            }

            @Override
            public void onNext(AutoSingleResponse<RepairTowerListBean> userInfo) {
                Log.e("presenter", userInfo.toString());
                mRepairListView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairListView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairListView.showDialog(userInfo.getMsg());
                    mRepairListView.hideDialog();
                }
            }
        });
    }

    public void openTimerPicker(String type, FragmentActivity activity) {
        TimePickerDialog timePickerDialog = new TimePickerDialog.Builder()
                .setCallBack((timePickerView, millseconds) -> {
                    String time = LogUtil.changeToStandardTime(millseconds);
                    switch (type) {
                        case RepairConstantUtils.START_TIME:
                            mRepairListView.setTimeTaskSearch(time, null);
                            break;
                        case RepairConstantUtils.END_TIME:
                            mRepairListView.setTimeTaskSearch(null, time);
                            break;
                    }
                })
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("选择")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMaxMillseconds(System.currentTimeMillis()) //设置能到达的最大值
                .setCurrentMillseconds(System.currentTimeMillis()) //设置当前时间
                .setThemeColor(activity.getResources().getColor(R.color.title_theme))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(activity.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(activity.getResources().getColor(R.color.black))
                .setWheelItemTextSize(12)
                .build();
        timePickerDialog.show(activity.getSupportFragmentManager(), "YEAR_MONTH_DAY");
    }

    public void insulatorClearList(String startDate, String endDate, String taskCode) {
        mRepairListView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().insulatorClearList(this, startDate, endDate, taskCode, new Subscriber<AutoSingleResponse<RepairTowerListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mRepairListView.hideDialog();
                mRepairListView.onError(e.getMessage() + "请求失败！");
            }

            @Override
            public void onNext(AutoSingleResponse<RepairTowerListBean> userInfo) {
                mRepairListView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairListView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairListView.showDialog(userInfo.getMsg());
                    mRepairListView.hideDialog();
                }
            }
        });
    }

    public void getRepairTaskList(String startDate, String endDate, String taskCode) {
        mRepairListView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().getRepairTaskList(this, startDate, endDate, taskCode, new Subscriber<AutoSingleResponse<RepairTowerListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mRepairListView.hideDialog();
                mRepairListView.onError(e.getMessage() + "请求失败！");
            }

            @Override
            public void onNext(AutoSingleResponse<RepairTowerListBean> userInfo) {
                mRepairListView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairListView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairListView.showDialog(userInfo.getMsg());
                    mRepairListView.hideDialog();
                }
            }
        });
    }

    public void traceCheckList(String startDate, String endDate, String taskCode) {
        mRepairListView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().traceCheckList(this, startDate, endDate, taskCode, new Subscriber<AutoSingleResponse<RepairTowerListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mRepairListView.hideDialog();
                mRepairListView.onError(e.getMessage() + "请求失败！");
            }

            @Override
            public void onNext(AutoSingleResponse<RepairTowerListBean> userInfo) {
                mRepairListView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairListView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairListView.showDialog(userInfo.getMsg());
                    mRepairListView.hideDialog();
                }
            }
        });
    }

    public void rtvSprayList(String startDate, String endDate, String taskCode) {
        mRepairListView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().rtvSprayList(this, startDate, endDate, taskCode, new Subscriber<AutoSingleResponse<RepairTowerListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mRepairListView.hideDialog();
                mRepairListView.onError(e.getMessage() + "请求失败！");
            }

            @Override
            public void onNext(AutoSingleResponse<RepairTowerListBean> userInfo) {
                mRepairListView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairListView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairListView.showDialog(userInfo.getMsg());
                    mRepairListView.hideDialog();
                }
            }
        });
    }
    public void hydrophobicTestList(String startDate, String endDate, String taskCode) {
        mRepairListView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().hydrophobicTestList(this, startDate, endDate, taskCode, new Subscriber<AutoSingleResponse<RepairTowerListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mRepairListView.hideDialog();
                mRepairListView.onError(e.getMessage() + "请求失败！");
            }

            @Override
            public void onNext(AutoSingleResponse<RepairTowerListBean> userInfo) {
                mRepairListView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairListView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairListView.showDialog(userInfo.getMsg());
                    mRepairListView.hideDialog();
                }
            }
        });
    }

    public void siteSurveyList( String taskCode) {
        mRepairListView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().siteSurveyList(this,taskCode, new Subscriber<AutoSingleResponse<RepairTowerListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mRepairListView.hideDialog();
                mRepairListView.onError(e.getMessage() + "请求失败！");
            }

            @Override
            public void onNext(AutoSingleResponse<RepairTowerListBean> userInfo) {
                mRepairListView.hideDialog();
                if (userInfo.isResult()) {
                    mRepairListView.onSuccess(userInfo.getData(), userInfo.getMsg());
                } else {
                    mRepairListView.showDialog(userInfo.getMsg());
                    mRepairListView.hideDialog();
                }
            }
        });
    }



}
