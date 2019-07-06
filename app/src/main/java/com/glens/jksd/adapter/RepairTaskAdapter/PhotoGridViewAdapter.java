package com.glens.jksd.adapter.RepairTaskAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.glens.jksd.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wkc on 2019/6/25.
 */
public class PhotoGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> mImageInfoList;

    public PhotoGridViewAdapter(Context context, List<String> appImageInfoList) {
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
        if (null == convertView) {
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.item_list_image, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(mImageInfoList.get(position)).into(viewHolder.ivPhoto);
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
