package com.glens.jksd.activity.activity_detect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.glens.jksd.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;

public class ImageShowActivity extends AppCompatActivity {

    @Bind(R.id.iv_ground_image_show)
    PhotoView ivGroundImageShow;
    @Bind(R.id.image_back)
    LinearLayout imageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        ButterKnife.bind(this);
        String imgUrl = getIntent().getExtras().getString("data");
        Glide.with(this).load(imgUrl).placeholder(R.mipmap.ic_launcher).into(ivGroundImageShow);
    }

    @OnClick(R.id.image_back)
    public void onViewClicked() {
        finish();
    }
}
