package com.glens.jksd.utils.imagePick.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glens.jksd.PowerApplication;
import com.glens.jksd.R;
import com.glens.jksd.utils.PowerUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.StringUtil;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.utils.baseEvent.BaseEvent;
import com.glens.jksd.utils.imagePick.IntentConstants;
import com.glens.jksd.utils.imagePick.adapter.ImageGridAdapter;
import com.glens.jksd.utils.imagePick.model.ImageItem;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * 图片选择
 *
 * @author Administrator
 */

public class ImageChooseActivity extends Activity {
    private List<ImageItem> mDataList = new ArrayList<ImageItem>();
    private String mBucketName;
    private int availableSize;
    private GridView mGridView;
    private TextView mBucketNameTv;
    private LinearLayout cancelTv;
    private ImageGridAdapter mAdapter;
    private Button mFinishBtn;
    private Button mPreviewBtn;
    private BaseEvent event;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        UiUtils.initStatusBar(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_image_choose);

        PowerApplication.setmDataList(null);
        mDataList = PowerApplication.getImageList();//(List<ImageItem>) getIntent().getSerializableExtra(IntentConstants.EXTRA_IMAGE_LIST);
        if (mDataList != null && mDataList.size() > 0) {
            for (int i = 0; i < mDataList.size(); i++) {
                mDataList.get(i).isSelected = false;
            }
        }
        event = (BaseEvent) getIntent().getSerializableExtra(IntentConstants.GET_PHOTO_EVENT);
        if (mDataList == null) {
            mDataList = new ArrayList<ImageItem>();
        }
        mBucketName = getIntent().getStringExtra(IntentConstants.EXTRA_BUCKET_NAME);

        if (TextUtils.isEmpty(mBucketName)) {
            mBucketName = "请选择";
        }
        availableSize = getIntent().getIntExtra(
                IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE,
                IntentConstants.MAX_IMAGE_SIZE);

        initView();
        initListener();

    }

    private void initView() {
        mBucketNameTv = (TextView) findViewById(R.id.tv_title);
        mBucketNameTv.setText(mBucketName);

        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        if (PowerApplication.getSelectedImgs() != null) {
            for (String key : PowerApplication.getSelectedImgs().keySet()) {
                for (int i = 0; i < mDataList.size(); i++) {
                    if (TextUtils.equals(mDataList.get(i).imageId, key)) {
                        mDataList.get(i).isSelected = true;
                    }
                }
            }
        }
        mAdapter = new ImageGridAdapter(ImageChooseActivity.this, mDataList);
        mGridView.setAdapter(mAdapter);
        mFinishBtn = (Button) findViewById(R.id.finish_btn);
        mPreviewBtn = (Button) findViewById(R.id.bt_preview);
        cancelTv = (LinearLayout) findViewById(R.id.btn_back);
        if (PowerApplication.getSelectedImgs() != null) {
            mFinishBtn.setText("确定" + "(" + PowerApplication.getSelectedImgs().size() + "/"
                    + availableSize + ")");
        }
        mAdapter.notifyDataSetChanged();
    }


    private void initListener() {
        mFinishBtn.setOnClickListener(v -> {
            if (PowerApplication.getSelectedImgs() != null && PowerApplication.getSelectedImgs().values().size() > 0) {
                List<ImageItem> mData = new ArrayList<>(PowerApplication.getSelectedImgs().values());
                PowerApplication.setmDataList(StringUtil.removeDuplicateImageById(mData));
                Log.e("photo->", mData.size() + "   " + PowerApplication.getmDataList().size());
                EventBus.getDefault().post(event);
                Log.e("传送类型","类型"+event.toString());
                setResult(RESULT_OK);
                new Handler().postDelayed(() -> {
                    PowerUtils.postEvent(RepairConstantUtils.DELETE_IMAGE);
                }, 500);
                finish();
            } else {
                ToastUtils.showToastSafe(ImageChooseActivity.this, "您还未选择图片");
            }
        });

        mGridView.setOnItemClickListener(new OnItemClickListener() {

            private File file;

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ImageItem item = mDataList.get(position);
                file = new File(item.sourcePath);
//				if(file.length()<=512*1024){
//					//所选图片小于512Kb
//					UiUtils.showToast("很抱歉，您上传的图片小于512K");
//				}else{
                item.size = file.length();

                if (item.isSelected) {
                    item.isSelected = false;
                    PowerApplication.getSelectedImgs().remove(item.imageId);
                } else {
                    if (PowerApplication.getSelectedImgs().size() >= availableSize) {
                        ToastUtils.showToastSafe(ImageChooseActivity.this, "最多选择" + availableSize + "张图片");
                        return;
                    }
                    item.isSelected = true;
                    PowerApplication.getSelectedImgs().put(item.imageId, item);
                    Log.e("测试", file.getAbsolutePath());

                }
//				}

                mFinishBtn.setText("完成" + "(" + PowerApplication.getSelectedImgs().size() + "/"
                        + availableSize + ")");
                mAdapter.notifyDataSetChanged();
            }

        });

        cancelTv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//				Intent intent = new Intent(ImageChooseActivity.this,
//						PublishActivity.class);
//				startActivity(intent);
                finish();

            }
        });

        mPreviewBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//				处理预览
                if (PowerApplication.getSelectedImgs() != null && !PowerApplication.getSelectedImgs().isEmpty()) {
//                    try {
//                        Intent intent = new Intent(ImageChooseActivity.this, ImageZoomActivity.class);
//                        intent.putExtra("album", true);
//                        intent.putExtra(
//                                IntentConstants.EXTRA_IMAGE_LIST,
//                                (Serializable) new ArrayList<ImageItem>(MyApplication.selectedImgs
//                                        .values()));
//                        startActivity(intent);
//                    } catch (Exception e) {
                    Intent intent = new Intent(ImageChooseActivity.this, ImageZoomActivity.class);
                    PowerApplication.setCurrentDataList(new ArrayList<ImageItem>(PowerApplication.getSelectedImgs()
                            .values()));
                    startActivity(intent);
//                    }
                } else {
                    ToastUtils.showToastSafe(ImageChooseActivity.this, "您还未选择图片哦~");
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PowerApplication.setImageList(null);
    }
}