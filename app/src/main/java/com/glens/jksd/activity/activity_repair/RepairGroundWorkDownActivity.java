package com.glens.jksd.activity.activity_repair;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.RecordingItem;
import com.glens.jksd.service.RecordingService;
import com.glens.jksd.utils.MediaRecordUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.glens.jksd.utils.MediaRecordUtils.mRecordLayout;

/**
 * 接地线交底录音
 */
public class RepairGroundWorkDownActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = RepairGroundWorkDownActivity.class.getSimpleName();
    @Bind(R.id.bt_repair_record)
    ImageView mBtRepairRecord;
    @Bind(R.id.repair_record_line)
    View mRepairRecordLine;
    @Bind(R.id.iv_repair_record_trumpet)
    ImageView mIvRepairRecordTrumpet;
    @Bind(R.id.pb_repair_record)
    ProgressBar mPbRepairRecord;
    @Bind(R.id.cm_repair_record)
    Chronometer mCmRepairRecord;
    @Bind(R.id.iv_repair_record_delete)
    ImageView mIvRepairRecordDelete;
    @Bind(R.id.ry_repair_record)
    RelativeLayout mRyRepairRecord;
    @Bind(R.id.iv_repair_record_trumpet_two)
    ImageView mIvRepairRecordTrumpetTwo;
    @Bind(R.id.pb_repair_record_two)
    ProgressBar mPbRepairRecordTwo;
    @Bind(R.id.cm_repair_record_two)
    Chronometer mCmRepairRecordTwo;
    @Bind(R.id.iv_repair_record_delete_two)
    ImageView mIvRepairRecordDeleteTwo;
    @Bind(R.id.ry_repair_record_two)
    RelativeLayout mRyRepairRecordTwo;
    @Bind(R.id.iv_repair_record_trumpet_three)
    ImageView mIvRepairRecordTrumpetThree;
    @Bind(R.id.pb_repair_record_three)
    ProgressBar mPbRepairRecordThree;
    @Bind(R.id.cm_repair_record_three)
    Chronometer mCmRepairRecordThree;
    @Bind(R.id.iv_repair_record_delete_three)
    ImageView mIvRepairRecordDeleteThree;
    @Bind(R.id.ry_repair_record_three)
    RelativeLayout mRyRepairRecordThree;
    @Bind(R.id.ly_repair_record)
    LinearLayout mLyRepairRecord;
    @Bind(R.id.repair_ground_one)
    RadioButton mRepairGroundOne;
    @Bind(R.id.rg_repair_ground)
    RadioGroup mRgRepairGround;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;

    private boolean mStartRecording = true;
    private MediaPlayer mMediaPlayer = null;
    private int recordTime = 0;//循环更新的次数
    public int mCurrentItem = 1;//当前播放的录音
    private RecordingItem mRecordingItemOne;
    private RecordingItem mRecordingItemTwo;
    private RecordingItem mRecordingItemThree;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_ground_work_down);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void initView() {
        mHandler = new Handler();
        mRgRepairGround.check(R.id.repair_ground_one);
        setTitleText("交底录音");
        getLlBasetitleBack().setOnClickListener(this);
        mRepairTaskStandardText.setText(R.string.repair_check_submit);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_repair_record_trumpet, R.id.iv_repair_record_delete, R.id.iv_repair_record_trumpet_two,
            R.id.iv_repair_record_delete_two, R.id.iv_repair_record_trumpet_three, R.id.iv_repair_record_delete_three,
            R.id.repair_task_standard_ry, R.id.bt_repair_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_repair_record_trumpet:
                mCurrentItem = RepairConstantUtils.REPAIR_RECORD_ITEM_ONE;
                isPlayRecord(mCurrentItem);
                break;
            case R.id.iv_repair_record_trumpet_two:
                mCurrentItem = RepairConstantUtils.REPAIR_RECORD_ITEM_TWO;
                isPlayRecord(mCurrentItem);
                break;
            case R.id.iv_repair_record_trumpet_three:
                mCurrentItem = RepairConstantUtils.REPAIR_RECORD_ITEM_THREE;
                isPlayRecord(mCurrentItem);
                break;
            case R.id.iv_repair_record_delete:
                MediaRecordUtils.isDeleteRecord(getApplicationContext(),mRecordingItemOne,RepairConstantUtils.REPAIR_RECORD_ITEM_ONE,mRyRepairRecord);
                break;
            case R.id.iv_repair_record_delete_two:
                MediaRecordUtils.isDeleteRecord(getApplicationContext(),mRecordingItemTwo,RepairConstantUtils.REPAIR_RECORD_ITEM_TWO,mRyRepairRecordTwo);
                break;
            case R.id.iv_repair_record_delete_three:
                MediaRecordUtils.isDeleteRecord(getApplicationContext(),mRecordingItemThree,RepairConstantUtils.REPAIR_RECORD_ITEM_THREE,mRyRepairRecordThree);
                break;
            case R.id.repair_task_standard_ry:
                break;
            case R.id.bt_repair_record:
                isStartRecord(mRecordLayout);
                break;

        }
    }

    /**
     * 播放音乐
     */
    private void isPlayRecord(int layoutSize) {
        isKeepFile(layoutSize);
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        mMediaPlayer = new MediaPlayer();

        switch (layoutSize) {
            case 1:
                MediaRecordUtils.setMediaSource(mMediaPlayer, mRecordingItemOne, mPbRepairRecord, mCmRepairRecord);
                break;
            case 2:
                MediaRecordUtils.setMediaSource(mMediaPlayer, mRecordingItemTwo, mPbRepairRecordTwo, mCmRepairRecordTwo);
                break;
            case 3:
                MediaRecordUtils.setMediaSource(mMediaPlayer, mRecordingItemThree, mPbRepairRecordThree, mCmRepairRecordThree);
                break;
            default:
                Log.e(TAG, "isPlayRecord layoutSize不合法");
                break;
        }
        updateSeekBar();
    }

    private void isKeepFile(int layoutSize) {
        Log.e("isKeepFile", "layoutSize" + layoutSize);
        switch (layoutSize) {
            case RepairConstantUtils.REPAIR_RECORD_ITEM_ONE:
                mRecordingItemOne = new RecordingItem();
                MediaRecordUtils.setRecordItemPath(getApplicationContext(), mRecordingItemOne, RepairConstantUtils.REPAIR_RECORD_ONE);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_TWO:
                mRecordingItemTwo = new RecordingItem();
                MediaRecordUtils.setRecordItemPath(getApplicationContext(), mRecordingItemTwo, RepairConstantUtils.REPAIR_RECORD_TWO);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_THREE:
                mRecordingItemThree = new RecordingItem();
                MediaRecordUtils.setRecordItemPath(getApplicationContext(), mRecordingItemThree, RepairConstantUtils.REPAIR_RECORD_THREE);
                break;
            default:
                Log.e("isKeepFile", "mRecordLayout 不合法");
                break;
        }
    }

    private Runnable mRunnable = () -> {
        if (mMediaPlayer != null) {
            int mCurrentPosition = mMediaPlayer.getCurrentPosition();
            setProgressbar(mCurrentItem, mCurrentPosition);
            updateSeekBar();
            if (!mMediaPlayer.isPlaying()) {
                setConfigureStop();
            }
        }
    };

    private void setProgressbar(int mRecordLayout, int progress) {
        switch (mRecordLayout) {
            case RepairConstantUtils.REPAIR_RECORD_ITEM_ONE:
                mPbRepairRecord.setProgress(progress);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_TWO:
                mPbRepairRecordTwo.setProgress(progress);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_THREE:
                mPbRepairRecordThree.setProgress(progress);
                break;
            default:
                break;
        }
    }

    private void updateSeekBar() {
        mHandler.postDelayed(mRunnable, 1000);
    }

    private void setConfigureStop() {
        switch (mCurrentItem) {
            case RepairConstantUtils.REPAIR_RECORD_ITEM_ONE:
                MediaRecordUtils.stopPlaying(mHandler, mRunnable, mPbRepairRecord, mCmRepairRecord, mMediaPlayer);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_TWO:
                MediaRecordUtils.stopPlaying(mHandler, mRunnable, mPbRepairRecordTwo, mCmRepairRecordTwo, mMediaPlayer);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_THREE:
                MediaRecordUtils.stopPlaying(mHandler, mRunnable, mPbRepairRecordThree, mCmRepairRecordThree, mMediaPlayer);
                break;
            default:
                break;

        }
    }

    /**
     * 录音前鉴权
     *
     * @param layout 布局位置
     */
    private void isStartRecord(int layout) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this
                    , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1);
        } else {
            onRecord(mStartRecording, layout);
            mStartRecording = !mStartRecording;
            Log.e(TAG, "点击录音后状态" + mStartRecording);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    onRecord(mStartRecording, mRecordLayout);
                }
                break;
        }
    }

    /**
     * 开始录音
     *
     * @param start        是否录音状态
     * @param recordLayout 录音总条数
     */
    private void onRecord(boolean start, int recordLayout) {
        recordTime = 0;
        Intent intent = new Intent(this, RecordingService.class);
        intent.putExtra(RepairConstantUtils.REPAIR_RECORD_LAYOUT, recordLayout);

        if (start) {
            ToastUtils.showToastSafe(this, "开始录音...");
            File folder = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC) + "/SoundRecorder");
            if (!folder.exists()) {
                folder.mkdir();
            }
            startTimer();
            startService(intent);
        } else {
            ToastUtils.showToastSafe(this, "录音结束...");
            stopService(intent);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    private void startTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.e("持续时间", "en" + SystemClock.elapsedRealtime() + "录音次数" + mRecordLayout);
                recordTime++;
                if (recordTime == 5) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onRecord(false, mRecordLayout);
                            switch (mRecordLayout) {
                                case RepairConstantUtils.REPAIR_RECORD_ITEM_ONE:
                                    mRepairRecordLine.setVisibility(View.VISIBLE);
                                    mRyRepairRecord.setVisibility(View.VISIBLE);
                                    break;
                                case RepairConstantUtils.REPAIR_RECORD_ITEM_TWO:
                                    mRyRepairRecordTwo.setVisibility(View.VISIBLE);
                                    break;
                                case RepairConstantUtils.REPAIR_RECORD_ITEM_THREE:
                                    mRyRepairRecordThree.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    Log.e(" startTimer", "mRecordLayout不合法");
                                    break;
                            }
                            Log.e("终止时间", "en" + SystemClock.elapsedRealtime());
                            mStartRecording = !mStartRecording;
                            timer.cancel();
                        }
                    });
                }
            }
        };
        timer.schedule(task, 1000, 1000);                //启动定时器

    }
}
