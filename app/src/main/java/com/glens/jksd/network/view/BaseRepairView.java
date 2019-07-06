package com.glens.jksd.network.view;

import com.glens.jksd.network.PresentView;

/**
 * Created by wkc on 2019/6/11.
 */
public interface BaseRepairView extends PresentView {
    void onSuccess(Object bean, String resultMsg);

    void showDialog(String message);

    void hideDialog();
}
