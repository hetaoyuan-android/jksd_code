package com.glens.jksd.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.glens.jksd.bean.RecordingItem;

import java.io.IOException;

public class MediaRecordUtils {
    public static int mRecordLayout = 1;//当前录音条数位置
    /**
     * 初始化MediaPlayer，Chronometer
     * @param mMediaPlayer MediaPlayer
     * @param item 录音数据实体类
     * @param progressBar 进度条
     * @param chronometer 计时器
     */
    public static void  setMediaSource(MediaPlayer mMediaPlayer, RecordingItem item, ProgressBar progressBar,
                                      Chronometer chronometer) {
        try {
            mMediaPlayer.setDataSource(item.getFilePath());
            mMediaPlayer.prepare();
            Log.e("文件路径", item.getFilePath());
            mMediaPlayer.start();
            progressBar.setMax(mMediaPlayer.getDuration());
            Log.e("setMediaSource", "总进度进度" + mMediaPlayer.getDuration());
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取出录音存储路径，保存到实体类
     * @param context context
     * @param recordingItem 录音数据实体类
     * @param record 路径存储key
     */
    public static void setRecordItemPath(Context context, RecordingItem recordingItem, String record) {
        String path = PreferenceUtils.getString(context, record, "");
        recordingItem.setFilePath(path);
        Log.e("取出录音路径", recordingItem.getFilePath() + "22" + "item类型" + path + "参数类型" + record);
    }


    /**
     * 执行删除录音操作
     * @param context context
     * @param layoutSize 要删除的录音位置
     * @param recordLayout 对应的录音布局
     */
    public static void isDeleteRecord(Context context,RecordingItem recordingItem,int layoutSize, RelativeLayout recordLayout) {
        switch (layoutSize) {
            case 1:
                setDeleteConfigure(context,1, recordingItem, recordLayout, "recordOne");
                break;
            case 2:
                setDeleteConfigure(context,2, recordingItem, recordLayout, "recordTwo");
                break;
            case 3:
                setDeleteConfigure(context,3, recordingItem, recordLayout, "recordThree");
                break;
            default:
                break;
        }
    }

    private static void setDeleteConfigure(Context context, int i, RecordingItem recordingItem, RelativeLayout mRyRepairRecord, String recordType) {
        if (recordingItem != null) {
            Log.e("删除文件路径", recordingItem.getFilePath());
            DataCleanUtil.cleanCustomCache(recordingItem.getFilePath());
            mRyRepairRecord.setVisibility(View.GONE);
            PreferenceUtils.remove(context, recordType);
            mRecordLayout = i;
        }
    }


    public static void stopPlaying(Handler mHandler,Runnable mRunnable,ProgressBar progressBar,
                                   Chronometer chronometer,MediaPlayer mMediaPlayer) {
//        mPlayButton.setImageResource(R.drawable.ic_media_play);
        mHandler.removeCallbacks(mRunnable);
        if (mMediaPlayer != null) {
            try {
                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.stop();
                }
            }catch (IllegalStateException e){
                mMediaPlayer = null;
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.stop();
            }

            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        if (progressBar != null) {
            progressBar.setProgress(progressBar.getMax());
        }
        if (chronometer != null) {
            chronometer.stop();
        }

    }


    public static void cleanData(Chronometer chronometer,ProgressBar progressBar){
        if (progressBar != null) {
            progressBar.setProgress(progressBar.getMax());
        }
        if (chronometer != null) {
            chronometer.stop();
        }
    }
}
