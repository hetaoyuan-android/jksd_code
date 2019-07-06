package com.glens.jksd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.glens.jksd.R;
import com.glens.jksd.fragment.MineFragment;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.presenter.MainLoginPresenter;
import com.glens.jksd.network.view.MainLoginView;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;

import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends FragmentActivity implements MainLoginView {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Bind(R.id.et_username)
    EditText mUsername;
    @Bind(R.id.et_password)
    EditText mPassword;
    @Bind(R.id.cb_remember_username)
    CheckBox mRememberName;
    @Bind(R.id.btn_login)
    Button mLogin;
    @Bind(R.id.ll_login_btn)
    LinearLayout llLoginBtn;

    private MainLoginPresenter mMainLoginPresenter;
    private boolean isChecked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mRememberName.setChecked(true);
        if (mRememberName.isChecked()) {
            mUsername.setText(PreferenceUtils.getString(this, RepairConstantUtils.REPAIR_LOGIN_KEY, ""));
        }
        initNetData();
//        openLoginFragment();
    }

    private void initNetData() {
        mMainLoginPresenter = new MainLoginPresenter(this);
        mMainLoginPresenter.onCreate();
        mMainLoginPresenter.BindPresentView((PresentView) this);
    }

    @Override
    public void setUserName() {

    }

    @Override
    public String getUserName() {
        if (mUsername != null && mUsername.getText() != null) {
            return mUsername.getText().toString().trim();
        } else {
            return null;
        }
    }

    @Override
    public String getPassword() {
        if (mPassword != null && mPassword.getText() != null) {
            return mPassword.getText().toString().trim();
        } else {
            return null;
        }
    }

    @Override
    public void showToast(String info) {
        ToastUtils.showToastSafe(this, info);
    }

    @Override
    public void onSuccess(Object userInfo) {
        Log.e(TAG, userInfo.toString());
        ToastUtils.showToastSafe(this, userInfo.toString());
    }

    @Override
    public void changeToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        Objects.requireNonNull(this).finish();
        startActivity(intent);
    }

    @Override
    public void onError(String result) {
        Log.e(TAG, result);
    }

//    private void openLoginFragment() {
//        MineFragment fragment = new MineFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.fl_login, fragment);  //fr_container不能为fragment布局，可使用线性布局相对布局等。
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

    @OnClick({R.id.cb_remember_username, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_remember_username:
                mRememberName.setChecked(!isChecked);
                isChecked = !isChecked;
                break;
            case R.id.btn_login:
                mMainLoginPresenter.setLoginInfo(isChecked);
                break;
        }
    }

}
