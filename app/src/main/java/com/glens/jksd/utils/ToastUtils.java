package com.glens.jksd.utils;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.ref.WeakReference;


public class ToastUtils implements Serializable {
    /**
     * 可以在子线程中弹出toast
     *
     * @param context
     * @param text
     */
    public static void showToastSafe(final Context context, final String text) {
        com.glens.jksd.utils.ThreadUtils.runInUIThread(new Runnable() {
            @Override
            public void run() {

                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.setText(text);
                toast.show();
            }
        });
    }


}
