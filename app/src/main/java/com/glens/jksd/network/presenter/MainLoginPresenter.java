package com.glens.jksd.network.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.glens.jksd.PowerApplication;
import com.glens.jksd.base.AutoSingleResponse;
import com.glens.jksd.bean.LoginBean;
import com.glens.jksd.network.BasePresenter;
import com.glens.jksd.network.DataManager;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.MainLoginView;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;

import java.util.HashMap;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainLoginPresenter extends BasePresenter {
    private Context mContext;
    private MainLoginView mMainLoginView;
    private LoginBean mUserInfo;

    public MainLoginPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        mMainLoginView = (MainLoginView) presentView;
    }

    public void setLoginInfo(boolean isChecked){
        HashMap<String,String> map = new HashMap<>();
        if(TextUtils.isEmpty(mMainLoginView.getUserName())){
            mMainLoginView.showToast("请输入用户名");
        }else if (TextUtils.isEmpty(mMainLoginView.getPassword())){
            mMainLoginView.showToast("请输入密码");
        }else {
            map.put("loginName",mMainLoginView.getUserName());
            map.put("psw",mMainLoginView.getPassword());
            keepLoginInfo(mMainLoginView.getUserName(),mMainLoginView.getPassword());
            if (isChecked) {
                PreferenceUtils.putString(mContext, RepairConstantUtils.REPAIR_LOGIN_KEY, mMainLoginView.getUserName());
            }else {
                PreferenceUtils.putString(mContext, RepairConstantUtils.REPAIR_LOGIN_KEY,"");
            }
            Log.e("setLoginInfo",map.toString());
            getLoginInfo(map);
        }

    }

    private void keepLoginInfo(String userName, String pd) {
        PreferenceUtils.putString(mContext, RepairConstantUtils.USER_SAVE_NAME,userName);
        PreferenceUtils.putString(mContext, RepairConstantUtils.USER_SAVE_KEY, pd);
        Log.e("setLoginInfo", "保存用户数据" + mMainLoginView.getUserName());
    }

    private void getTestInfo(HashMap<String,String> map) {
        super.mCompositeSubscription.add(DataManager.getInstance(mContext).test(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onCompleted() {
//                        if (mUserInfo != null) {
//                            try {
//                                mMainLoginView.onSuccess(mUserInfo.string());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                        mMainLoginView.onError(e.getMessage()+"请求失败！");
                        ToastUtils.showToastSafe(mContext,"请求失败");
                    }

                    @Override
                    public void onNext(ResponseBody userInfo) {
//                        mUserInfo = userInfo;
//                        ToastUtils.showToastSafe(mContext,u);
                    }
                }));
    }

    private void getLoginInfo(HashMap<String,String> map) {
        super.mCompositeSubscription.add(DataManager.getInstance(mContext).login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AutoSingleResponse<LoginBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                        mMainLoginView.onError(e.getMessage()+"请求失败！");
                        ToastUtils.showToastSafe(mContext,"请求失败");
                    }

                    @Override
                    public void onNext(AutoSingleResponse<LoginBean> userInfo) {
                        Log.e("getLoginInfo",userInfo.toString());
                        if(userInfo.isResult()){
                            PowerApplication.setToken(userInfo.getTicket());
                            mUserInfo = userInfo.getData();
                            keepUserCode(mUserInfo.getUserCode());
                            mMainLoginView.showToast(userInfo.getMsg());
                            mMainLoginView.changeToMainActivity();

                        }else{
                            mMainLoginView.showToast(userInfo.getMsg());
                        }

                    }
                }));
    }

    private void keepUserCode(String userCode) {
        if(!TextUtils.isEmpty(userCode)){
            PreferenceUtils.putString(mContext, RepairConstantUtils.USER_SAVE_CODE,userCode);
        }else{
        Log.e("登陆页面","用户code为空");
        }

    }
}
