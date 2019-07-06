package com.glens.jksd.utils.imagePick.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.glens.jksd.R;
import com.glens.jksd.utils.GlideUtils;
import com.glens.jksd.utils.PowerUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.StringUtil;
import com.glens.jksd.utils.imagePick.model.ImageItem;
import com.glens.jksd.utils.imagePick.view.RecyclerImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Administrator
 */
public class ImagePublishAdapter extends BaseAdapter {
    private List<ImageItem> mDataList = new ArrayList<ImageItem>();
    private Context mContext;
    private int maxSize;
    public static final String NET_HTTP = "http://";
    public static final String NET_HTTPS = "https://";
    private boolean isShowDelete;//根据这个变量来判断是否显示删除图标，true是显示，false是不显示
    private int type = 0;

    public ImagePublishAdapter(Context context, List<ImageItem> dataList, int maxSize) {
        this.mContext = context;
        this.mDataList = dataList;
        this.maxSize = maxSize;
    }

    public void setShowDelete(boolean isShowDelete) {
        this.isShowDelete = isShowDelete;
        notifyDataSetChanged();
    }

    public boolean getShowDelete() {
        return isShowDelete;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public int getCount() {
        // 多返回一个用于展示添加图标
        if (mDataList == null) {
            return 1;
        } else if (mDataList.size() == maxSize) {
            return maxSize;
        } else {
            return mDataList.size() + 1;
        }
    }

    @Override
    public Object getItem(int position) {
        if (mDataList != null && mDataList.size() == maxSize) {
            return mDataList.get(position);
        } else if (mDataList == null || position - 1 < 0
                || position > mDataList.size()) {
            return null;
        } else {
            return mDataList.get(position - 1);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    @SuppressLint("ViewHolder")
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_publish, null);
        RecyclerImageView imageIv = (RecyclerImageView) convertView
                .findViewById(R.id.item_grid_image);
        ImageView del = (ImageView) convertView
                .findViewById(R.id.iv_del);

        if (isShowAddItem(position)) {
            imageIv.setImageResource(R.drawable.btn_add_pic);
            imageIv.setBackgroundResource(R.color.bg_gray);
        } else {
            final ImageItem item = mDataList.get(position);
//            PicassoUtils.getinstance().load(mContext, StringUtil.getPath(item), R.drawable.img_default, R.drawable.img_fail, imageIv);
//            GlideUtils.loadImageViewOrError(mContext,StringUtil.getPath(item), imageIv, R.drawable.img_default);
            Glide.with(mContext).load(StringUtil.getPath(item)).error(R.drawable.img_default).into(imageIv);
        }
        if (!isShowAddItem(position)) {
            del.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);
        }
        if (del.getVisibility() == View.VISIBLE) {
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDataList.remove(position);
                    if (mDataList.size() == 0) {
//                        EventBus.getDefault().post(Event.DELETE_IMAGE_SUCCESS);
                        PowerUtils.postEvent(RepairConstantUtils.DELETE_IMAGE_SUCCESS);
                    }
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }

    private boolean isShowAddItem(int position) {
        int size = mDataList == null ? 0 : mDataList.size();
        return position == size;
    }

    public void addData(List<ImageItem> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void setData(List<ImageItem> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public List<ImageItem> getData() {
        return mDataList;
    }
}
