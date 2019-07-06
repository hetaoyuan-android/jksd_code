package com.glens.jksd.network.view.detection;

import android.content.Context;
import android.util.Log;

import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.bean.deteect.AddGrounResistanceBean;
import com.glens.jksd.network.BasePresenter;
import com.glens.jksd.network.InterManage;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.utils.RepairConstantUtils;

import rx.Subscriber;

public class AddDetectionPresenter extends BasePresenter {
    private Context mContext;
    private AddDetectionView mAddDetectionView;

    public AddDetectionPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        super.BindPresentView(presentView);
        mAddDetectionView = (AddDetectionView) presentView;
    }

    public void addGroundResistance() {
        mAddDetectionView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addGroundResistance(this, mAddDetectionView.getDataMap(), new Subscriber<AutoSingleResponse<AddGrounResistanceBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mAddDetectionView.hideDialog();
                mAddDetectionView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据",e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<AddGrounResistanceBean> info) {
                mAddDetectionView.hideDialog();
                if (info.isResult()) {
                    mAddDetectionView.onSuccess(info.getData(), info.getMsg(), info.isResult());
                    mAddDetectionView.getDataMap();
                } else {
                    mAddDetectionView.showDialog(info.getMsg());
                    mAddDetectionView.hideDialog();
                }
            }
        });
    }

    public void addGroundResistanceAndImage(String imagePath) {
        mAddDetectionView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addGroundResistanceAndImage(this, imagePath, mAddDetectionView.getDataMap(), new Subscriber<AutoSingleResponse<AddGrounResistanceBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mAddDetectionView.hideDialog();
                mAddDetectionView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据",e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<AddGrounResistanceBean> info) {
                mAddDetectionView.hideDialog();
                if (info.isResult()) {
                    mAddDetectionView.onSuccess(info.getData(), info.getMsg(), info.isResult());
                    mAddDetectionView.getDataMap();
                } else {
                    mAddDetectionView.showDialog(info.getMsg());
                    mAddDetectionView.hideDialog();
                }
            }
        });
    }

    public void addInfrared() {
        mAddDetectionView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addInfrared(this, mAddDetectionView.getDataMap(), new Subscriber<AutoSingleResponse<AddGrounResistanceBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mAddDetectionView.hideDialog();
                mAddDetectionView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据",e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<AddGrounResistanceBean> info) {
                mAddDetectionView.hideDialog();
                if (info.isResult()) {
                    mAddDetectionView.onSuccess(info.getData(), info.getMsg(), info.isResult());
                    mAddDetectionView.getDataMap();
                } else {
                    mAddDetectionView.showDialog(info.getMsg());
                    mAddDetectionView.hideDialog();
                }
            }
        });
    }

    public void addPayMeasure() {
        mAddDetectionView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().addPayMeasure(this, mAddDetectionView.getDataMap(), new Subscriber<AutoSingleResponse<AddGrounResistanceBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mAddDetectionView.hideDialog();
                mAddDetectionView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据",e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<AddGrounResistanceBean> info) {
                mAddDetectionView.hideDialog();
                if (info.isResult()) {
                    mAddDetectionView.onSuccess(info.getData(), info.getMsg(), info.isResult());
                    mAddDetectionView.getDataMap();
                } else {
                    mAddDetectionView.showDialog(info.getMsg());
                    mAddDetectionView.hideDialog();
                }
            }
        });
    }
}
