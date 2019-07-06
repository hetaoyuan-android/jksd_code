package com.glens.jksd.adapter.detection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.glens.jksd.R;
import com.glens.jksd.bean.deteect.ShowImgUrlBean;

import java.util.ArrayList;

public class GroundDetailShowImgAdapter extends RecyclerView.Adapter<GroundDetailShowImgAdapter.ViewHolder> {
    public Context mContext;
    public ArrayList<ShowImgUrlBean> mList;

    public GroundDetailShowImgAdapter(Context mContext, ArrayList<ShowImgUrlBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ground_show_img_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String imageUrl = mList.get(i).getImageUrl();
        Log.i("imageUrl_ground", imageUrl);
        Glide.with(mContext).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(viewHolder.imageView);
        // 点击事件
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(imageUrl);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            //适配器构造时只会用到实体类的get方法，用以获取相应的属性
            imageView = (ImageView) itemView.findViewById(R.id.bird_image);
        }
    }

    //自定义的接口 用于传递图片地址给跳转的Activity
    public interface OnItemClickListener {
        //data 用于获得图片的网址
        void onItemClick(String data);
    }

    //声明接口变量
    private OnItemClickListener mOnItemClickListener = null;

    //暴露接口给外部
    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        this.mOnItemClickListener = OnItemClickListener;
    }
}
