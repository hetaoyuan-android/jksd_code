package com.glens.jksd.utils.imagePick.util;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.Log;

import com.glens.jksd.utils.imagePick.model.ImageBucket;
import com.glens.jksd.utils.imagePick.model.ImageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片工具类
 *
 * @author Administrator
 */
public class ImageFetcher {
    private static ImageFetcher instance;
    private Context mContext;
    private LinkedHashMap<String, ImageBucket> mBucketList = new LinkedHashMap<String, ImageBucket>();
    private HashMap<String, String> mThumbnailList = new HashMap<String, String>();
    private String allPhotosBucketId = "-11111111111";//自定义全部图片的bucketId
    public static int mMinImageSize = 5000;//5000b

    private ImageFetcher() {
    }

    private ImageFetcher(Context context) {
        this.mContext = context;
    }

    public static ImageFetcher getInstance(Context context) {

        if (instance == null) {
            synchronized (ImageFetcher.class) {
                instance = new ImageFetcher(context);
            }
        }
        return instance;
    }

    /**
     * 是否已加载过了相册集合
     */
    boolean hasBuildImagesBucketList = false;

    /**
     * 得到图片集
     *
     * @param refresh
     * @return
     */
    public List<ImageBucket> getImagesBucketList(boolean refresh) {
        boolean isBuild = (refresh || (!refresh && !hasBuildImagesBucketList));
        if (isBuild) {
            if(mBucketList!=null){
                mBucketList.clear();
            }
            buildImagesBucketList();
        }
        List<ImageBucket> tmpList = new ArrayList<ImageBucket>();
        Iterator<Map.Entry<String, ImageBucket>> itr = mBucketList.entrySet()
                .iterator();
        while (itr.hasNext()) {
            Map.Entry<String, ImageBucket> entry = (Map.Entry<String, ImageBucket>) itr
                    .next();
            tmpList.add(entry.getValue());
        }
        return tmpList;
    }

    /**
     * 得到图片集
     */
    private void buildImagesBucketList() {
        Cursor cur = null;
        try {
            long startTime = System.currentTimeMillis();

            // 构造缩略图索引
            getThumbnail();
            //按时间倒序
            String sortOrder = Media.DATE_ADDED + " DESC";
            // 构造相册索引
            String[] columns = new String[]{Media._ID, Media.BUCKET_ID,
                    Media.DATA, Media.BUCKET_DISPLAY_NAME, Media.SIZE,};
            // 得到一个游标
            cur = mContext.getContentResolver().query(
                    Media.EXTERNAL_CONTENT_URI, columns, null, null, sortOrder);
            if (cur != null && cur.moveToFirst()) {
                ImageBucket allBucket = new ImageBucket();
                allBucket.imageList = new ArrayList<>();
                allBucket.bucketName = "所有图片";
                mBucketList.put(allPhotosBucketId, allBucket);

                // 获取指定列的索引
                int photoIDIndex = cur.getColumnIndexOrThrow(Media._ID);
                int photoPathIndex = cur.getColumnIndexOrThrow(Media.DATA);
                int bucketDisplayNameIndex = cur
                        .getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);
                int bucketIdIndex = cur.getColumnIndexOrThrow(Media.BUCKET_ID);
                int photoSizeIndex = cur.getColumnIndexOrThrow(Media.SIZE);

                do {
                    boolean isValid;
                    try {
                        int subLen = cur.getString(photoPathIndex).lastIndexOf(".") - (cur.getString(photoPathIndex).lastIndexOf("/") + 1);
                        if ((cur.getString(photoPathIndex).lastIndexOf("/") + 1) < 0) {
                            isValid = true;
                        } else if ((cur.getString(photoPathIndex).lastIndexOf(".") > cur.getString(photoPathIndex).length()) || subLen < 0) {
                            isValid = true;
                        } else {
                            isValid = cur.getString(photoPathIndex).substring(
                                    cur.getString(photoPathIndex).lastIndexOf("/") + 1,
                                    cur.getString(photoPathIndex).lastIndexOf("."))
                                    .replaceAll(" ", "").length() <= 0;
                        }

                    } catch (Exception e) {
                        String prefix = cur.getString(photoPathIndex).substring(cur.getString(photoPathIndex).lastIndexOf(".") + 1);
                        if (prefix.contains("jpg") || prefix.contains("jpeg")
                                || prefix.contains("png") || prefix.contains("gif")
                                || prefix.contains("bmp") || prefix.contains("wbmp")) {
                            isValid = false;
                        } else {
                            isValid = true;
                        }
                    }
                    if (isValid) {
                        Log.e("buildImagesBucketList()","无效的图片url");
                    } else {
                        String id = cur.getString(photoIDIndex);
                        String path = cur.getString(photoPathIndex);
                        String bucketName = cur.getString(bucketDisplayNameIndex);
                        String bucketId = cur.getString(bucketIdIndex);
                        int photoSize = cur.getInt(photoSizeIndex);
                        ImageBucket bucket = mBucketList.get(bucketId);
                        if (bucket == null) {
                            bucket = new ImageBucket();
                            mBucketList.put(bucketId, bucket);
                            bucket.imageList = new ArrayList<ImageItem>();
                            bucket.bucketName = bucketName;
                        }
                        bucket.count++;
                        ImageItem imageItem = new ImageItem();
                        imageItem.imageId = id;
                        imageItem.sourcePath = path;
                        imageItem.thumbnailPath = mThumbnailList.get(id);
                        bucket.imageList.add(imageItem);
                        //所有图片里过滤掉较小图片，避免显示过多无效图片(空图)
                        if (photoSize > mMinImageSize) {
                            allBucket.count = allBucket.count + 1;
                            allBucket.imageList.add(imageItem);
                        }
                    }
                }
                while (cur.moveToNext());
            }

            hasBuildImagesBucketList = true;
            long endTime = System.currentTimeMillis();
            Log.d(ImageFetcher.class.getName(), "use time: "
                    + (endTime - startTime) + " ms");
        } finally {
            if (cur != null) {
                cur.close();
            }
        }
    }

    /**
     * 得到缩略图
     */
    private void getThumbnail() {
        Cursor cursor = null;
        try {
            String[] projection = {Thumbnails.IMAGE_ID, Thumbnails.DATA};
            cursor = mContext.getContentResolver().query(
                    Thumbnails.EXTERNAL_CONTENT_URI, projection, null, null,
                    null);
            getThumbnailColumnData(cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * 从数据库中得到缩略图
     *
     */
    private void getThumbnailColumnData(Cursor cur) {
        if (cur != null && cur.moveToFirst()) {
            int imageId;
            String imagePath;
            int imageIdColumn = cur.getColumnIndex(Thumbnails.IMAGE_ID);
            int dataColumn = cur.getColumnIndex(Thumbnails.DATA);

            do {
                imageId = cur.getInt(imageIdColumn);
                imagePath = cur.getString(dataColumn);

                mThumbnailList.put("" + imageId, imagePath);
            }
            while (cur.moveToNext());
        }
    }

}
