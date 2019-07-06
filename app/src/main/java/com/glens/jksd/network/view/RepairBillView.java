package com.glens.jksd.network.view;

import com.glens.jksd.bean.RepairBillBean;
import com.glens.jksd.network.PresentView;

/**
 * Created by wkc on 2019/6/10.
 */
public interface RepairBillView extends PresentView {
    void onSuccess(RepairBillBean bean, String resultMsg);
}
