package com.glens.jksd.adapter.RepairTaskAdapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.glens.jksd.R;
import com.glens.jksd.bean.repair_task_bean.RepairInformationBean;
import com.glens.jksd.utils.PowerUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wkc on 2019/7/2.
 */
public class InformtionListAdapter extends BaseAdapter {
    private Context context;
    private List<RepairInformationBean.RecordsBean.PicListBean> mImageInfoList;

    public InformtionListAdapter(Context context, List<RepairInformationBean.RecordsBean.PicListBean> appImageInfoList) {
        this.mImageInfoList = appImageInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mImageInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mImageInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        String url;
        if (null == convertView) {
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.item_list_image, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        url = PowerUtils.getXiaoPhoto() + mImageInfoList.get(position).getPicUrl();

        if (!TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).into(viewHolder.ivPhoto);
        } else {
            Log.e("图片url为空", "位置" + position);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_photo)
        ImageView ivPhoto;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
