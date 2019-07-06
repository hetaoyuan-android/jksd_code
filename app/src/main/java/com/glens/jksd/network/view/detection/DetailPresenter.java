package com.glens.jksd.network.view.detection;

import android.content.Context;
import android.util.Log;

import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.bean.deteect.GroundResistanceDetailBean;
import com.glens.jksd.bean.deteect.InfraredDetailBean;
import com.glens.jksd.bean.deteect.PayMeasureDetailBean;
import com.glens.jksd.network.BasePresenter;
import com.glens.jksd.network.InterManage;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.utils.RepairConstantUtils;

import java.io.File;

import rx.Subscriber;

public class DetailPresenter extends BasePresenter {
    private Context mContext;
    private DetailView detailView;
    public DetailPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        super.BindPresentView(presentView);
        detailView = (DetailView) presentView;
    }

    //接地电阻详情页
    public void getGroundResistanceDetailList(String recodeCode) {
        detailView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().getGroundResistanceDetailList(this, recodeCode, new Subscriber<AutoSingleResponse<GroundResistanceDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                detailView.hideDialog();
                detailView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<GroundResistanceDetailBean> response) {
                Log.e("infraraedPresenter", response.toString());
                detailView.hideDialog();
                if (response.isResult()) {
                    detailView.onSuccess(response.getData(), response.getMsg());
                } else {
                    detailView.showDialog(response.getMsg());
                    detailView.hideDialog();
                }
            }
        });
    }

    // 红外测温的详情页
    public void getInfraredDetailList(String recodeCode) {
        detailView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().getInfraredDetailList(this, recodeCode, new Subscriber<AutoSingleResponse<InfraredDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                detailView.hideDialog();
                detailView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<InfraredDetailBean> response) {
                Log.e("infraraedPresenter", response.toString());
                detailView.hideDialog();
                if (response.isResult()) {
                    detailView.onSuccess(response.getData(), response.getMsg());
                } else {
                    detailView.showDialog(response.getMsg());
                    detailView.hideDialog();
                }
            }
        });
    }

    // 交跨测温详情页
    public void getPayMeasureDetailList(String recodeCode) {
        detailView.showDialog(RepairConstantUtils.DATA_LOADING);
        InterManage.getInstance().getPayMeasureDetailList(this, recodeCode, new Subscriber<AutoSingleResponse<PayMeasureDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                detailView.hideDialog();
                detailView.onError(e.getMessage() + "请求失败！");
                Log.e("报错数据", e.getMessage());
            }

            @Override
            public void onNext(AutoSingleResponse<PayMeasureDetailBean> response) {
                detailView.hideDialog();
                if (response.isResult()) {
                    detailView.onSuccess(response.getData(), response.getMsg());
                } else {
                    detailView.showDialog(response.getMsg());
                    detailView.hideDialog();
                }
            }
        });
    }


}
