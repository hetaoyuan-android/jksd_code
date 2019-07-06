package com.glens.jksd.utils.imagePick.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.widget.PopupWindow;

import com.glens.jksd.R;
import com.glens.jksd.utils.ImageSizeUtil;
import com.glens.jksd.utils.UiUtils;
import com.glens.jksd.utils.baseEvent.BaseEvent;
import com.glens.jksd.utils.imagePick.IntentConstants;
import com.glens.jksd.utils.imagePick.model.ImageItem;
import com.glens.jksd.utils.imagePick.view.ImageBucketChooseActivity;
import com.glens.jksd.utils.imagePick.view.ImageZoomActivity;
import com.glens.jksd.utils.imagePick.view.SelectPicPopupWindow;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * @author sunyan
 */
public class ImagePickUtils {

    private static File vFile;
    private static SelectPicPopupWindow menuWindow;

    /**
     * 获取当前图片数量
     *
     * @return
     */
    public static int getDataSize(List mDataList) {
        return mDataList == null ? 0 : mDataList.size();
    }


    /**
     * 获取当前可以添加的图片数量
     *
     * @param maxsize
     * @param mDataList
     * @return
     */
    public static int getAvailableSize(int maxsize, List mDataList) {
        int availSize = maxsize - getDataSize(mDataList);
        if (availSize >= 0) {
            return availSize;
        }
        return 0;
    }

    /**
     * 拍照
     *
     * @param act
     * @param requestCode 请求码
     */
    public static void takePhoto(Activity act, int requestCode) {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        vFile = new File(Environment.getExternalStorageDirectory()
                + "/DIFU/", String.valueOf(System.currentTimeMillis())
                + ".jpg");
        if (!vFile.exists()) {
            File vDirPath = vFile.getParentFile();
            vDirPath.mkdirs();
        } else {
            if (vFile.exists()) {
                vFile.delete();
            }
        }
        Uri cameraUri = Uri.fromFile(vFile);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        act.startActivityForResult(openCameraIntent, requestCode);
    }

    /**
     * 打开相机，解决7.0文件权限问题
     */
    public static void openCamera(Activity mContext, int requsetCode) {
        Uri imageUri;
        vFile = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera/" + System.currentTimeMillis() + ".jpg");
        if (!vFile.getParentFile().exists()) {
            vFile.getParentFile().mkdirs();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", vFile);
        } else {
            imageUri = Uri.fromFile(vFile);
        }
        Log.e("作者地址", vFile.getAbsolutePath());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        mContext.startActivityForResult(intent, requsetCode);

    }

    public static String getImagePath() {
        if (vFile != null) {
            long fileSize = 0;
            try {
                fileSize = ImageSizeUtil.getFileSize(vFile);
                Log.e("ImageSizeUtil", "原始文件大小" + fileSize / 1024);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return vFile.getPath();
        }
        return null;
    }

    /**
     * @param position    点击图片位置
     * @param act         当前Activity
     * @param list        图片数据List
     * @param requestCode 启动相机请求码
     * @param maxSize     图片限制张数
     * @param layout     弹窗显示依赖的位置
     */
    public static void promptSelectImgDialog( BaseEvent event, int position,  Activity act, final List<ImageItem> list, final int requestCode, final int maxSize, int layout) {
        if (ImagePickUtils.getDataSize(list) == position) {
            // 获取拍照后未压缩的原图片，并保存在uri路径中
            menuWindow = new SelectPicPopupWindow(
                    act, view -> {
                        switch (view.getId()) {
                            case R.id.btn_take_photo:
                                try {
                                    ImagePickUtils.openCamera(act,requestCode);
                                    menuWindow.dismiss();
                                } catch (Throwable e) {
                                    Log.e("消息",e.getMessage());
                                }

                                break;
                            case R.id.btn_pick_photo:

                                Intent intent = new Intent(act, ImageBucketChooseActivity.class);
                                Bundle bundle = new Bundle();
                                intent.putExtra(IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE,
                                        ImagePickUtils.getAvailableSize(maxSize, list));
                                bundle.putSerializable(IntentConstants.GET_PHOTO_EVENT, event);
                                intent.putExtras(bundle);
                                act.startActivity(intent);
                                if (menuWindow != null && menuWindow.isShowing()) {
                                    menuWindow.dismiss();
                                }
                                break;
                            default:
                                break;
                        }
                    });
            // 显示窗口
            menuWindow.showAtLocation(
                    act.findViewById(layout),
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                    0);
            UiUtils.setAlpha(0.6f, act);
            menuWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    menuWindow = null;
                    UiUtils.setAlpha(1.0f, act);
                }
            });
        } else {

            Intent intent = new Intent(act, ImageZoomActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(IntentConstants.EXTRA_IMAGE_LIST, (Serializable) list);
            intent.putExtra(IntentConstants.EXTRA_CURRENT_IMG_POSITION, position);
            act.startActivity(intent);
        }
    }

    /**
     * 按尺寸压缩图片
     *
     * @param srcPath  图片路径
     * @param desWidth 压缩的图片宽度
     * @return Bitmap 对象
     */

    public static Bitmap compressImageFromFile(String srcPath, float desWidth) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float desHeight = desWidth * h / w;
        int be = 1;
        if (w > h && w > desWidth) {
            be = (int) (newOpts.outWidth / desWidth);
        } else if (w < h && h > desHeight) {
            be = (int) (newOpts.outHeight / desHeight);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

//        newOpts.inPreferredConfig = Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;
    }

    /**
     * 压缩图片（质量压缩）
     *
     * @param image
     */

    public static File compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;

        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            long length = baos.toByteArray().length;
        }
//        long length = baos.toByteArray().length;
//        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
//        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片


        File file = new File(Environment.getExternalStorageDirectory() + "/"+System.currentTimeMillis()+".png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * 压缩
     *
     * @param filePath 文件路径
     */
    public static String compressImage(final String filePath) {
        Bitmap bitmap = ImagePickUtils.compressImageFromFile(filePath, 1024f);// 按尺寸压缩图片
        int size = bitmap.getByteCount();
        File file = ImagePickUtils.compressImage(bitmap);  //按质量压缩图片
        return file.getAbsolutePath();
    }
}
