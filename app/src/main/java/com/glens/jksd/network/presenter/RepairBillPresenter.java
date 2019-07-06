package com.glens.jksd.network.presenter;

import android.content.Context;
import android.util.Log;

import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.base.BaseFragment;
import com.glens.jksd.bean.RepairBillBean;
import com.glens.jksd.network.BasePresenter;
import com.glens.jksd.network.DataManager;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.RepairBillView;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wkc on 2019/6/10.
 */
public class RepairBillPresenter extends BasePresenter {
    private Context mContext;
    private RepairBillView mRepairListView;

    public RepairBillPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        super.BindPresentView(presentView);
        mRepairListView = (RepairBillView) presentView;
    }

    public void getRepairListInfo(Context context, String taskCode, BaseFragment fragment) {
        fragment.showSvpDilog(context, RepairConstantUtils.DATA_LOADING, false, null, null);
        super.mCompositeSubscription.add(DataManager.getInstance(mContext).repairBill(taskCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AutoSingleResponse<RepairBillBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                        mRepairListView.onError(e.getMessage() + "请求失败！");
                        ToastUtils.showToastSafe(mContext, "请求失败");
                        fragment.dismissSvpDialog(RepairConstantUtils.DIALOG_TIME);
                    }

                    @Override
                    public void onNext(AutoSingleResponse<RepairBillBean> userInfo) {
                        Log.e("presenter", userInfo.toString());
                        if (userInfo.isResult()) {
                            mRepairListView.onSuccess(userInfo.getData(), userInfo.getMsg());
                            fragment.dismissSvpDialog(RepairConstantUtils.DIALOG_TIME);
                        } else {
                            fragment.showSvpDilog(context, userInfo.getMsg(), false, null, null);
                            fragment.dismissSvpDialog(RepairConstantUtils.DIALOG_TIME);
                        }
                    }
                }));
    }

}
