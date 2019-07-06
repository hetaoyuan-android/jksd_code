package com.glens.jksd.network.view;

import com.glens.jksd.network.PresentView;

import java.util.HashMap;

/**
 * Created by wkc on 2019/6/14.
 */
public interface RepairTowerAddView extends PresentView {
    void onSuccess(Object bean, String resultMsg);

    void showDialog(String message);

    void hideDialog();

    HashMap<String, Object> getDataMap();
}
