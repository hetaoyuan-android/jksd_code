package com.glens.jksd.utils.imagePick;

import android.annotation.SuppressLint;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;


/**
 * @author Administrator
 */
public class IntentConstants {
	/**相册中图片对象集合*/
	public static final String EXTRA_IMAGE_LIST = "image_list";
	/**相册名称*/
	public static final String EXTRA_BUCKET_NAME = "buck_name";
	/**可添加的图片数量*/
	public static final String EXTRA_CAN_ADD_IMAGE_SIZE = "can_add_image_size";
	/**当前选择的照片位置*/
	public static final String EXTRA_CURRENT_IMG_POSITION = "current_img_position";
	/**单次最多发送图片数*/
	public static final int MAX_IMAGE_SIZE = 9;
	/**下单页面图片张数*/
	public static final int MAX_IMAGE_SIZE_BOOKING = 5;
	/**头像图片张数*/
	public static final int MAX_IMAGE_SIZE_AVATAR= 1;
	/**pos机支付图片张数*/
	public static final int MAX_IMAGE_SIZE_POS_PAY= 2;

	public static final String DEFAULT_DEFAULT_PIC_PATH = "DIFU";

	public static final String GET_PHOTO_EVENT = "event";

	/**
	 * 获取拍照之后的图片路径
	 *
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getPicPath() {
		String path = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			SimpleDateFormat sDateFormat = new SimpleDateFormat(
					"yyyyMMdd_HHmmss");
			String date = sDateFormat.format(new java.util.Date());
			path = Environment.getExternalStorageDirectory().getAbsolutePath()
					+ "/" + DEFAULT_DEFAULT_PIC_PATH + "/IMG_" + date + ".jpg";
			File dir = new File(Environment.getExternalStorageDirectory()
					.getAbsolutePath()
					+ "/"
					+ DEFAULT_DEFAULT_PIC_PATH);
			if (!dir.exists()) {
				dir.mkdir();
			}
		}
		return path;
	}
}
