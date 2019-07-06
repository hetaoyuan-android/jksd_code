package com.glens.jksd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.bean.MainGradView;

import java.util.List;

public class MainGradViewAdapter extends BaseAdapter {

    private List<MainGradView> mainGradViews;
    private LayoutInflater layoutInflater;

    public MainGradViewAdapter(Context context, List<MainGradView> mainGradViews) {
        this.mainGradViews = mainGradViews;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mainGradViews.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_main_gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.textTitle = convertView.findViewById(R.id.text);
            viewHolder.imgSource = convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MainGradView mainGradView = mainGradViews.get(position);
        if (mainGradView != null) {
            viewHolder.textTitle.setText(mainGradView.getTextTitle());
            viewHolder.imgSource.setImageResource(mainGradView.getImgSource());
        }
        return convertView;
    }

    class ViewHolder {
        TextView textTitle;
        ImageView imgSource;
    }
}
