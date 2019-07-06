package com.glens.jksd.utils.imagePick.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.glens.jksd.R;
import com.glens.jksd.utils.GlideUtils;
import com.glens.jksd.utils.StringUtil;
import com.glens.jksd.utils.imagePick.model.ImageBucket;

import java.util.List;


/**
 * @author Administrator
 */
public class ImageBucketAdapter extends BaseAdapter
{
	private List<ImageBucket> mDataList;
	private Context mContext;

	public ImageBucketAdapter(Context context, List<ImageBucket> dataList)
	{
		this.mContext = context;
		this.mDataList = dataList;
	}

	@Override
	public int getCount()
	{
        return mDataList == null ? 0 : mDataList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ViewHolder mHolder;
		if (convertView == null)
		{
			convertView = View.inflate(mContext, R.layout.item_bucket_list,
					null);
			mHolder = new ViewHolder();
			mHolder.coverIv = (ImageView) convertView.findViewById(R.id.cover);
			mHolder.titleTv = (TextView) convertView.findViewById(R.id.title);
			mHolder.countTv = (TextView) convertView.findViewById(R.id.count);
			convertView.setTag(mHolder);
		}
		else
		{
			mHolder = (ViewHolder) convertView.getTag();
		}

		final ImageBucket item = mDataList.get(position);

		if (item.imageList != null && item.imageList.size() > 0)
		{
			String sourcePath =  StringUtil.getPath(item.imageList.get(0));
//			ImageDisplayer.getInstance(mContext).displayBmp(mHolder.coverIv, thumbPath,
//					sourcePath);
			if (!sourcePath.equals( mHolder.coverIv.getTag())) {
//				PicassoUtils.getinstance().loadFile(mContext, "file://"+sourcePath, R.drawable.img_default, R.drawable.img_fail, mHolder.coverIv);
//				GlideUtils.loadImageViewOrError(mContext,"file://"+sourcePath,mHolder.coverIv, R.drawable.img_default);
				Glide.with(mContext).load("file://"+sourcePath).error(R.drawable.img_default).into(mHolder.coverIv);

//				mHolder.coverIv.setTag(sourcePath);
			}
		}
		else
		{
			mHolder.coverIv.setImageBitmap(null);
		}

		mHolder.titleTv.setText(item.bucketName);
		mHolder.countTv.setText("("+item.count + ")");

		return convertView;
	}

	static class ViewHolder
	{
		private ImageView coverIv;
		private TextView titleTv;
		private TextView countTv;
	}

}
