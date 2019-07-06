package com.glens.jksd.network.view;

import com.glens.jksd.network.PresentView;

public interface MainLoginView extends PresentView {

    void setUserName();

    String getUserName();

    String getPassword();

    void showToast(String info);

    void onSuccess(Object userInfo);

    void changeToMainActivity();

}
