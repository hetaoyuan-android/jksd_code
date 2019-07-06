package com.glens.jksd.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.PowerApplication;
import com.glens.jksd.R;
import com.glens.jksd.bean.LoginBean;
import com.glens.jksd.network.DataManager;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.utils.baseEvent.BaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class BaseActivity extends AppCompatActivity {

    private RelativeLayout mLlRoot;
    private LinearLayout mLlBasetitleBack;
    private TextView mTvBasetitleTitle;
    private ImageView mImBasetitleRight;
    private Dialog svpDialog;
    private View mSvpdialogView;
    private ProgressBar svpProgress2View;
    private ImageView svpProgressView;
    private LinearLayout svpDialogLayout;
    private TextView svpTitleView;
    private static final int DISMISS_DIALOG = 12312;//消除 SVPDialog的消息wat
    public static final int ME_CAMERA_TAKE = 1117;//进入相机拍照图片页面请求码
    private SafeHandler safeHandler;

    public static class SafeHandler extends Handler{
        private final WeakReference<BaseActivity> mTarget;
        private Dialog mDialog;
        SafeHandler(BaseActivity controller, Dialog dialog) {
            mTarget = new WeakReference<>(controller);
            mDialog = dialog;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DISMISS_DIALOG:
                    if (mDialog != null && mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                    break;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.title_bar);
        findView();
    }

    private void findView() {
        mLlRoot = findViewById(R.id.ll_basetitle_root);
        mLlBasetitleBack = findViewById(R.id.ll_basetitle_back);
        mTvBasetitleTitle = findViewById(R.id.tv_basetitle_title);
        mImBasetitleRight = findViewById(R.id.im_basetitle_right);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 重点是重写setContentView，让继承者可以继续设置setContentView
     * 重写setContentView
     *
     * @param resId
     */
    @Override
    public void setContentView(int resId) {
        View view = getLayoutInflater().inflate(resId, null);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.BELOW, R.id.ll_basetitle);
        if (null != mLlRoot) {
            mLlRoot.addView(view, lp);
        }
    }

    /**
     *
     * 设置中间标题文字
     * @param c
     */
    public void setTitleText(CharSequence c) {
        if (mTvBasetitleTitle != null)
            mTvBasetitleTitle.setText(c);
    }
    /**
     *
     * 设置中间标题文字
     * @param resId
     */
    public void setTitleText(int resId) {
        if (mTvBasetitleTitle != null)
            mTvBasetitleTitle.setText(resId);
    }
    /**
     * 设置右边按钮是否显示
     * @param visible
     */
    public void setImageRightVisibity(boolean visible) {
        if (mImBasetitleRight != null) {
            if (visible)
                mImBasetitleRight.setVisibility(View.VISIBLE);
            else
                mImBasetitleRight.setVisibility(View.GONE);
        }
    }

    /**
     * 返回按钮
     * @return
     */
    public LinearLayout getLlBasetitleBack() {
        return mLlBasetitleBack;
    }

    /**
     *
     * @return
     */
    public TextView getTvBasetitleTitle() {
        return mTvBasetitleTitle;
    }



    public ImageView getImageBasetitleRight() {
        return mImBasetitleRight;
    }

    //提交的dialog
    public void showSvpDilog(Context context,String title, boolean isOnOtherClickDismiss, String showOther, DialogInterface.OnDismissListener listener) {
        Log.e("", "title=" + title + " isOnOtherClickDismiss=" + isOnOtherClickDismiss);
        if (TextUtils.equals(title, "用户未登录或已过期，请重新登录！")) {
          //重新登陆
            HashMap<String,String> map = new HashMap<>();
            map.put("loginName",PreferenceUtils.getString(context,RepairConstantUtils.USER_SAVE_NAME,""));
            map.put("psw",PreferenceUtils.getString(context,RepairConstantUtils.USER_SAVE_KEY,""));
            Log.e("BaseActivity","重新登陆 "+ map.toString());
            getLoginInfo(map,context);
        } else {
            if (mSvpdialogView == null)
                initSvpDialog();
            if (svpDialog != null) {
                if (svpDialog.isShowing()) {
                    svpDialog.dismiss();
                }
            }

            if (TextUtils.isEmpty(title)) {
                this.svpTitleView.setVisibility(View.GONE);
            } else {
                Log.e("BaseActivity", "title=" + title);
                this.svpTitleView.setText(title);
                this.svpTitleView.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(showOther)) {
                svpProgressView.setVisibility(View.GONE);
                svpProgress2View.setVisibility(View.VISIBLE);
            } else {
                svpProgressView.setVisibility(View.VISIBLE);
                svpProgress2View.setVisibility(View.GONE);
                int imageID = R.drawable.ic_svstatus_info;
                if (TextUtils.equals(showOther, "info")) {
                    imageID = R.drawable.ic_svstatus_info;
                }
                if (TextUtils.equals(showOther, "error")) {
                    imageID = R.drawable.ic_svstatus_error;
                }
                if (TextUtils.equals(showOther, "success")) {
                    imageID = R.drawable.ic_svstatus_success;
                }
                svpProgressView.setImageResource(imageID);
            }
            svpDialog.setCanceledOnTouchOutside(isOnOtherClickDismiss);
            svpDialog.setOnDismissListener(listener);
            if (!isFinishing()) {
                svpDialog.show();
            }
        }

    }

    public View initSvpDialog() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        svpDialog = new Dialog(this, R.style.svpDialogGlassStyle);
        mSvpdialogView = getLayoutInflater().inflate(R.layout.dialog_load_data, null);
        svpDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mSvpdialogView.setLayoutParams(params);
        svpDialog.setCanceledOnTouchOutside(false);
        svpDialog.setContentView(mSvpdialogView, params);
        svpProgressView = (ImageView) mSvpdialogView.findViewById(R.id.svp_dialog_progress);
        svpProgress2View = (ProgressBar) mSvpdialogView.findViewById(R.id.svp_dialog_progress2);
        svpDialogLayout = (LinearLayout) mSvpdialogView.findViewById(R.id.svp_dialog_layout);
        svpTitleView = (TextView) mSvpdialogView.findViewById(R.id.svp_dialog_title);
        safeHandler = new SafeHandler(BaseActivity.this,svpDialog);
        return mSvpdialogView;
    }

    public void dismissSvpDilog(int time) {
        if (svpDialog != null) {
            if (time == 0) {
                svpDialog.dismiss();
            } else {
                safeHandler.sendEmptyMessageDelayed(DISMISS_DIALOG,time);
            }

        }

    }

    /**
     * 登录成功保存账号密码sp,30分钟以后超时重新登录获取sp,退出登录
     */
    public void getLoginInfo(HashMap<String,String> map, Context mContext) {
       DataManager.getInstance(mContext).login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AutoSingleResponse<LoginBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                        ToastUtils.showToastSafe(getApplicationContext(),"请求失败");
                    }

                    @Override
                    public void onNext(AutoSingleResponse<LoginBean> userInfo) {
                        Log.e("getLoginInfo",userInfo.toString());
                        if(userInfo.isResult()){
                            PowerApplication.setToken(userInfo.getTicket());
//                            ToastUtils.showToastSafe(getApplicationContext(),"重新登陆成功");
                        }else{
                            ToastUtils.showToastSafe(getApplicationContext(),userInfo.getMsg());
                        }

                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(BaseEvent event) {

    }
}
