package com.glens.jksd.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 图片压缩并保存文件夹
 */
public class ImageSizeUtil {

    private static String TAG = ImageSizeUtil.class.getSimpleName();


    /**
     * 保存方法
     */
    public static String saveBitmap(String filePath, String picName, String dir) {
        Log.d(TAG, "保存图片");
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        File f = new File(dir, picName);
        if (f.exists()) {
            f.delete();
        }
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Bitmap bm = getSmallBitmap(filePath);
        if (bm != null) {
            Log.d(TAG, "压缩前" + (bm.getByteCount() / 1024));
        }


        FileOutputStream os = null;
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            int options = 90;
            while (stream.toByteArray().length / 1024 > 1000) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
                stream.reset(); // 重置baos即清空baos
                bm.compress(Bitmap.CompressFormat.JPEG, options, stream);// 这里压缩options%，把压缩后的数据存放到baos中
                options -= 10;// 每次都减少10
            }
//            bm.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            // 70 是压缩率，表示压缩30%; 如果不压缩是100，表示压缩率为0
            os = new FileOutputStream(f);
            os.write(stream.toByteArray());

            long fileSize = getFileSize(f);
            Log.e("ImageSizeUtil", "质量压缩后" + fileSize / 1024);

            return f.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (os != null) {
                safeClose(os);
            }
        }
    }

    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }

    public static void safeClose(FileOutputStream fis) {
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        //质量压缩
//        Logger.e("ImageSizeUtil", "质量压缩前" + bitmap.getByteCount() / 1024);
        return bitmap;
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


}
