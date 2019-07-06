package com.glens.jksd.utils.imagePick.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.glens.jksd.PowerApplication;
import com.glens.jksd.R;
import com.glens.jksd.utils.baseEvent.BaseEvent;
import com.glens.jksd.utils.imagePick.IntentConstants;
import com.glens.jksd.utils.imagePick.adapter.ImageBucketAdapter;
import com.glens.jksd.utils.imagePick.model.ImageBucket;
import com.glens.jksd.utils.imagePick.model.ImageItem;
import com.glens.jksd.utils.imagePick.util.ImageFetcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 选择相册
 *
 * @author Administrator
 */
public class ImageBucketChooseActivity extends Activity {
    private ImageFetcher mHelper;
    private List<ImageBucket> mDataList = new ArrayList<ImageBucket>();
    private ListView mListView;
    private ImageBucketAdapter mAdapter;
    private int availableSize;
    private BaseEvent event;
    private HashMap<String, ImageItem> maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        UiUtils.initStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image_bucket_choose);
        mHelper = ImageFetcher.getInstance(getApplicationContext());
        initData();
        initView();
    }

    private void initData() {
        mDataList = mHelper.getImagesBucketList(true);
        availableSize = getIntent().getIntExtra(
                IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE,
                IntentConstants.MAX_IMAGE_SIZE);
        event = (BaseEvent) getIntent().getSerializableExtra(IntentConstants.GET_PHOTO_EVENT);
        maps = new HashMap<>();
        PowerApplication.setSelectedImgs(maps);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new ImageBucketAdapter(this, mDataList);
        mListView.setAdapter(mAdapter);
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        titleTv.setText("相册");
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                selectOne(position);
                PowerApplication.setImageList(mDataList.get(position).imageList);
                Intent intent = new Intent(ImageBucketChooseActivity.this,
                        ImageChooseActivity.class);
//                intent.putExtra(IntentConstants.EXTRA_IMAGE_LIST,
//                        (Serializable) mDataList.get(position).imageList);
                intent.putExtra(IntentConstants.EXTRA_BUCKET_NAME,
                        mDataList.get(position).bucketName);
                intent.putExtra(IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE,
                        availableSize);
                intent.putExtra(IntentConstants.GET_PHOTO_EVENT, event);
                startActivityForResult(intent, 100);
            }
        });

        LinearLayout cancelTv = (LinearLayout) findViewById(R.id.btn_back);
        cancelTv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void selectOne(int position) {
        int size = mDataList.size();
        for (int i = 0; i != size; i++) {
            if (i == position) {
                mDataList.get(i).selected = true;
            } else {
                mDataList.get(i).selected = false;
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
