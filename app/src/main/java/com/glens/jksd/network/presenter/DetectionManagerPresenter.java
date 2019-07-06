package com.glens.jksd.network.presenter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.glens.jksd.R;
import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.DetectionCountBean;
import com.glens.jksd.bean.LoginBean;
import com.glens.jksd.bean.ResistanceBean;
import com.glens.jksd.network.BasePresenter;
import com.glens.jksd.network.DataManager;
import com.glens.jksd.network.InterManage;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.DetectionManagerView;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.view.CustomPopWindow;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetectionManagerPresenter extends BasePresenter {
    private Context mContext;
    private DetectionManagerView detectionManagerView;
    private LoginBean mUserInfo;

    public DetectionManagerPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        super.BindPresentView(presentView);
        detectionManagerView = (DetectionManagerView) presentView;
    }

    public void getDetectionListInfo(String taskName, String taskTime, int taskType, int page, int rows, BaseActivity activity) {
        activity.showSvpDilog(activity,RepairConstantUtils.DATA_LOADING,false,null,null);
        super.mCompositeSubscription.add(DataManager.getInstance(mContext).detectionList(taskName, taskTime, taskType, page, rows)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AutoSingleResponse<ResistanceBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                        detectionManagerView.onError(e.getMessage() + "请求失败！");
                        ToastUtils.showToastSafe(mContext, "请求失败");
                        activity.dismissSvpDilog(RepairConstantUtils.DIALOG_TIME);
                    }

                    @Override
                    public void onNext(AutoSingleResponse<ResistanceBean> userInfo) {
                        Log.e("presenter" ,userInfo.toString());
                        if (userInfo.isResult()) {
                            detectionManagerView.showToast(userInfo.getMsg());
                            detectionManagerView.onSuccess(userInfo.getData(), userInfo.getMsg());
                            detectionManagerView.setBottomViewText(userInfo.getData().getTotal(),
                                    userInfo.getData().getIrDetectionCnt(),userInfo.getData().getCmDectionCnt(),
                                    userInfo.getData().getErDectionCnt());
                            detectionManagerView.changeToItemActivity();
                        } else {
                            activity.showSvpDilog(activity,userInfo.getMsg(),false,null,null);
                            activity.dismissSvpDilog(RepairConstantUtils.DIALOG_TIME);
                        }

                    }
                }));
    }

    public void getDetectionCountInfo(String taskName, String taskTime, int taskType) {
        super.mCompositeSubscription.add(DataManager.getInstance(mContext).detectionCount(taskName, taskTime, taskType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AutoSingleResponse<DetectionCountBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                        detectionManagerView.onError(e.getMessage() + "请求失败！");
                        ToastUtils.showToastSafe(mContext, "请求失败");
                    }

                    @Override
                    public void onNext(AutoSingleResponse<DetectionCountBean> userInfo) {
                        if (userInfo.isResult()) {
                            detectionManagerView.showToast(userInfo.getMsg());
                            detectionManagerView.setBottomViewText(userInfo.getData().getTotal(),
                                    userInfo.getData().getIrDetectionCnt(),userInfo.getData().getCmDectionCnt(),
                                    userInfo.getData().getErDectionCnt());
                        } else {
                            detectionManagerView.showToast(userInfo.getMsg());
                        }

                    }
                }));
    }



    public void getTest(String taskName, String taskTime, int taskType, int page, int rows){
        InterManage.getInstance().getDetectionList(taskName, taskTime, taskType, page, rows, new Subscriber<AutoSingleResponse<ResistanceBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                detectionManagerView.onError(e.getMessage() + "请求失败！");
                ToastUtils.showToastSafe(mContext, "请求失败");
            }

            @Override
            public void onNext(AutoSingleResponse<ResistanceBean> userInfo) {
                if (userInfo.isResult()) {
//                            mUserInfo = userInfo.getData();
                    detectionManagerView.showToast(userInfo.getMsg());
                    detectionManagerView.onSuccess(userInfo.getData(), userInfo.getMsg());
                    detectionManagerView.changeToItemActivity();
                } else {
                    detectionManagerView.showToast(userInfo.getMsg());
                }
            }
        });
    }

    public void openTimrPicker(FragmentActivity activity) {
        TimePickerDialog timePickerDialog = new TimePickerDialog.Builder()
                .setCallBack((timePickerView, millseconds) -> {
                    Date date = new Date(millseconds);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String time = format.format(date);
                    ToastUtils.showToastSafe(activity, "presenter选择了时间" + time);
                    detectionManagerView.setTimeTaskSearch(time);
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

    public void handleLogic(View view, CustomPopWindow popWindow,BaseActivity activity) {
        View.OnClickListener listener = v -> {
            switch (v.getId()) {
                case R.id.rb_spray_before:
                    detectionManagerView.setTaskType(RepairConstantUtils.DETECTION_GROUND_RESISTANCE);
                    break;
                case R.id.rb_spray_one:
                    detectionManagerView.setTaskType(RepairConstantUtils.DETECTION_INFRARDE);
                    break;
                case R.id.rb_spray_two:
                    detectionManagerView.setTaskType(RepairConstantUtils.DETECTION_PAY_ACROSS_MEASURE);
                    break;
                case R.id.rb_pop_reset:
                    detectionManagerView.setTaskType(0);
                    startSearch(0, popWindow,activity);
                    break;
                case R.id.rb_pop_submit:
                    startSearch(detectionManagerView.geTaskType(), popWindow,activity);
                    break;
            }
        };
        RadioGroup rb = view.findViewById(R.id.rb_detection_type);
        switch (detectionManagerView.geTaskType()) {
            case 0:
                break;
            case 1:
                rb.check(R.id.rb_spray_before);
                break;
            case 2:
                rb.check(R.id.rb_spray_one);
                break;
            case 3:
                rb.check(R.id.rb_spray_two);
                break;
            default:
                Log.e("handleLogic", "非法参数类型" + detectionManagerView.geTaskType());
                break;
        }
        view.findViewById(R.id.rb_spray_before).setOnClickListener(listener);
        view.findViewById(R.id.rb_spray_one).setOnClickListener(listener);
        view.findViewById(R.id.rb_spray_two).setOnClickListener(listener);
        view.findViewById(R.id.rb_pop_reset).setOnClickListener(listener);
        view.findViewById(R.id.rb_pop_submit).setOnClickListener(listener);
    }
    private void startSearch(int taskType, CustomPopWindow popWindow,BaseActivity activity) {
        if (popWindow != null) {
            popWindow.dissmiss();
        }
       getDetectionListInfo("", "", taskType, 0, 10,activity);
    }


}
