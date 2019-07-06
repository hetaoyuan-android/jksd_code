package com.glens.jksd.utils.imagePick.model;

import java.io.Serializable;


/**
 * 图片对象
 *
 * @author Administrator
 */

public class ImageItem implements Serializable {
    private static final long serialVersionUID = -7188270558443739436L;
    /**
     * 图片id
     */
    public String imageId;
    /**
     * 缩略图地址
     */
    public String thumbnailPath;
    /**
     * 原图地址
     */
    public String sourcePath;

    /**
     * 图片大小
     */
    public long size;
    public boolean isSelected;
    public boolean isPdf;
    //-1 新增的图片id
    public int rowId = -1;
}
