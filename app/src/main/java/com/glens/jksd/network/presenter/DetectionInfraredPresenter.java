package com.glens.jksd.network.presenter;

import android.content.Context;

import com.glens.jksd.bean.LoginBean;
import com.glens.jksd.network.BasePresenter;
import com.glens.jksd.network.InterManage;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.DetectionInfraredView;
import com.glens.jksd.utils.ToastUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by wkc on 2019/5/24.
 */
public class DetectionInfraredPresenter extends BasePresenter {
    private Context mContext;
    private DetectionInfraredView mDetectionInfraredView;
    private LoginBean mUserInfo;

    public DetectionInfraredPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        super.BindPresentView(presentView);
        mDetectionInfraredView = (DetectionInfraredView) presentView;

    }

    public void getInfraredList(Context context,String lineVol, String lineName, int isInspected, String towerNo, String taskCode,int page, int rows){
        InterManage.getInstance().infraredList(context,lineVol, lineName, isInspected, towerNo, taskCode, page, rows, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.getMessage();
                mDetectionInfraredView.onError(e.getMessage() + " 请求失败！");
                ToastUtils.showToastSafe(mContext, "请求失败");
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String info = responseBody.string();
                    mDetectionInfraredView.showToast(info);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
