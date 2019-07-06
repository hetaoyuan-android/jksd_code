package com.glens.jksd.utils.imagePick.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.glens.jksd.PowerApplication;
import com.glens.jksd.R;
import com.glens.jksd.utils.DfConfig;
import com.glens.jksd.utils.GlideUtils;
import com.glens.jksd.utils.imagePick.IntentConstants;
import com.glens.jksd.utils.imagePick.model.ImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ImageZoomActivity extends Activity {

    private MyViewPager pager;
    private MyPageAdapter adapter;
    private int currentPosition;
    private TextView tvTitle;
    private LinearLayout btnBack;
    private List<ImageItem> mDataList;
    public static final String ALBUM = "album";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_zoom);
        initData();
        initView();
    }

    private void initView() {
        adapter = new MyPageAdapter();
        pager = (MyViewPager) findViewById(R.id.viewpager);
        pager.setOnPageChangeListener(pageChangeListener);
        pager.setAdapter(adapter);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnBack = (LinearLayout) findViewById(R.id.btn_back);
        tvTitle.setText("预览");
        btnBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (mDataList != null) {
            adapter.setDataList(mDataList);
            pager.setCurrentItem(currentPosition);
        }
    }

    private void initData() {
        currentPosition = getIntent().getIntExtra(
                IntentConstants.EXTRA_CURRENT_IMG_POSITION, 0);
        if (getIntent().getBooleanExtra(ALBUM, false)) {
            mDataList = (List<ImageItem>) getIntent().getSerializableExtra(IntentConstants.EXTRA_IMAGE_LIST);
        } else {
            mDataList = PowerApplication.getCurrentDataList();
        }
    }


    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

        @Override
        public void onPageSelected(int arg0) {
            currentPosition = arg0;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    class MyPageAdapter extends PagerAdapter {
        private List<ImageItem> dataList = new ArrayList<ImageItem>();
        private ArrayList<PhotoView> mViews = new ArrayList<PhotoView>();

        public void setDataList(List<ImageItem> dataList) {
            this.dataList = dataList;
            notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            PhotoView iv = new PhotoView(ImageZoomActivity.this);
            PhotoViewAttacher attacher = new PhotoViewAttacher(iv);
            attacher.setScaleLevels(1, 5, 10);
            String srcPath = dataList.get(arg1).sourcePath;
            if (srcPath.contains("http://") || srcPath.contains("https://")) {
//                GlideUtils.loadImageViewOrError(ImageZoomActivity.this, srcPath + DfConfig.IMAGE_SLIM, iv, R.drawable.img_default);
                Glide.with(ImageZoomActivity.this).load(srcPath + DfConfig.IMAGE_SLIM).error(R.drawable.img_default).into(iv);


            } else {
//                GlideUtils.loadImageViewOrError(ImageZoomActivity.this, "file://" + srcPath, iv, R.drawable.img_default);
                Glide.with(ImageZoomActivity.this).load("file://" + srcPath).error(R.drawable.img_default).into(iv);
            }
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            ((ViewPager) arg0).addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            if (mViews.size() >= arg1 + 1) {
                ((ViewPager) arg0).removeView(mViews.get(arg1));
            }
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return dataList == null ? 0 : dataList.size();
        }

    }

    @Override
    protected void onDestroy() {
//        adapter.notifyDataSetChanged();
        super.onDestroy();
    }
}