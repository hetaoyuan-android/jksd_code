package com.glens.jksd.network.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_repair.RepairTaskItemActivity;
import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.RepairListBean;
import com.glens.jksd.network.BasePresenter;
import com.glens.jksd.network.DataManager;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.RepairListView;
import com.glens.jksd.utils.LogUtil;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.view.CustomPopWindow;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wkc on 2019/6/3.
 */
public class RepairListPresenter extends BasePresenter {
    private Context mContext;
    private RepairListView mRepairListView;
    private RepairListBean mRepairListBean;

    public RepairListPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        super.BindPresentView(presentView);
        mRepairListView = (RepairListView) presentView;
    }

    public void getRepairListInfo(String taskName, String startDate, String endDate, int overhaulType, String createDeptId, int page, int rows, BaseActivity activity) {
        activity.showSvpDilog(activity, RepairConstantUtils.DATA_LOADING, false, null, null);
        super.mCompositeSubscription.add(DataManager.getInstance(mContext).checkList(taskName, startDate, endDate, overhaulType, createDeptId, page, rows)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AutoSingleResponse<RepairListBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                        mRepairListView.onError(e.getMessage() + "请求失败！");
                        ToastUtils.showToastSafe(mContext, "请求失败");
                        activity.dismissSvpDilog(RepairConstantUtils.DIALOG_TIME);
                    }

                    @Override
                    public void onNext(AutoSingleResponse<RepairListBean> userInfo) {
                        Log.e("presenter", userInfo.toString());
                        if (userInfo.isResult()) {
                            mRepairListBean = userInfo.getData();
                            mRepairListView.onSuccess(userInfo.getData(), userInfo.getMsg());
                            mRepairListView.setBottomViewText(userInfo.getData().getTotal(),
                                    userInfo.getData().getOverhaulCnt(), userInfo.getData().getTechTransCnt(),
                                    userInfo.getData().getMaintainCnt());
                        } else {
                            activity.showSvpDilog(activity, userInfo.getMsg(), false, null, null);
                            activity.dismissSvpDilog(RepairConstantUtils.DIALOG_TIME);
                        }
                    }
                }));
    }

    public void handleLogic(View view, String taskName, String mStartDate, String mEndData, String mCreateDeptId, CustomPopWindow popWindow, BaseActivity activity) {
        View.OnClickListener listener = v -> {
            switch (v.getId()) {
                case R.id.rb_spray_technical:
                    mRepairListView.setTaskType(RepairConstantUtils.DETECTION_GROUND_RESISTANCE);
                    break;
                case R.id.rb_spray_repair:
                    mRepairListView.setTaskType(RepairConstantUtils.DETECTION_INFRARDE);
                    break;
                case R.id.rb_spray_daily:
                    mRepairListView.setTaskType(RepairConstantUtils.DETECTION_PAY_ACROSS_MEASURE);
                    break;
                case R.id.rb_pop_reset:
                    mRepairListView.setTaskType(0);
                    startSearch(taskName, mStartDate, mEndData, mCreateDeptId, 0, popWindow, activity);
                    break;
                case R.id.rb_pop_submit:
                    startSearch(taskName, mStartDate, mEndData, mCreateDeptId, mRepairListView.geTaskType(), popWindow, activity);
                    break;
            }
        };
        keepPopState(view);

        view.findViewById(R.id.rb_spray_technical).setOnClickListener(listener);
        view.findViewById(R.id.rb_spray_repair).setOnClickListener(listener);
        view.findViewById(R.id.rb_spray_daily).setOnClickListener(listener);
        view.findViewById(R.id.rb_pop_reset).setOnClickListener(listener);
        view.findViewById(R.id.rb_pop_submit).setOnClickListener(listener);
    }

    private void keepPopState(View view) {
        RadioGroup rb = view.findViewById(R.id.rb_detection_type);
        switch (mRepairListView.geTaskType()) {
            case 0:
                break;
            case 1:
                rb.check(R.id.rb_spray_technical);
                break;
            case 2:
                rb.check(R.id.rb_spray_repair);
                break;
            case 3:
                rb.check(R.id.rb_spray_daily);
                break;
            default:
                Log.e("handleLogic", "非法参数类型" + mRepairListView.geTaskType());
                break;
        }
    }

    private void startSearch(String taskName, String mStartDate, String mEndData, String mCreateDeptId, int taskType, CustomPopWindow popWindow, BaseActivity activity) {
        if (popWindow != null) {
            popWindow.dissmiss();
        }
        getRepairListInfo(taskName, mStartDate, mEndData, taskType, mCreateDeptId, 0, 10, activity);
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

    public void changeToRepairTaskItemActivity(Context context,String taskCode) {
        Intent intent = new Intent(context, RepairTaskItemActivity.class);
        PreferenceUtils.putString(context,"taskCode",taskCode);
        context.startActivity(intent);
    }


}
