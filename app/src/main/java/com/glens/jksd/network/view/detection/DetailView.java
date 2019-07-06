package com.glens.jksd.network.view.detection;

import com.glens.jksd.network.PresentView;

public interface DetailView extends PresentView {
   void onSuccess(Object bean, String resultMsg);
   void showDialog(String message);
   void hideDialog();
}
