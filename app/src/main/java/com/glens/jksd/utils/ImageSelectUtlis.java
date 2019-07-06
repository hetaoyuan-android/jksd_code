package com.glens.jksd.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;




public class ImageSelectUtlis {

    /**
     * 打开相机，解决7.0文件权限问题
     */
    public static void openCamera(FragmentActivity mContext, int requestCode, Context context) {
        Uri imageUri;
        File outputImage = new File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM), "/Camera/" + System.currentTimeMillis() + ".jpg");
        if (!outputImage.getParentFile().exists()) {
            outputImage.getParentFile().mkdirs();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Log.e("作者", context.getApplicationContext().getPackageName() + ".provider");
            imageUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        Log.e("作者地址", outputImage.getAbsolutePath());
//        AutoCarApplication.setAddress(outputImage.getAbsolutePath());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        mContext.startActivityForResult(intent, requestCode);

    }
}
