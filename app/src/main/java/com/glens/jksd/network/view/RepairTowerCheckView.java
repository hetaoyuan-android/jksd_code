package com.glens.jksd.network.view;

import com.glens.jksd.bean.RepairTowerListBean;
import com.glens.jksd.network.PresentView;

/**
 * Created by wkc on 2019/6/10.
 */
public interface RepairTowerCheckView extends PresentView {
    void onSuccess(RepairTowerListBean bean, String resultMsg);

    void setTimeTaskSearch(String startTime,String endTime);

    void showDialog(String message);

    void hideDialog();
}
