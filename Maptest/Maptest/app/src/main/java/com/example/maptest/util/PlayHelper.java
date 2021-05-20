package com.example.maptest.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Build;
import android.widget.SeekBar;
import android.widget.TextView;


import com.example.maptest.base.BaseApplication;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

public class PlayHelper {

    private MediaPlayer _player = null;
    private boolean _isPlaying = false;
    /**
     * 互斥变量，防止定时器与SeekBar拖动时进度冲突
     **/
    private boolean isChanging = false;
    private boolean isPause = false;
    private SeekBar seekbar;
    private Timer mTimer;
    private TimerTask mTimerTask;

    public boolean isPlaying() {
        return _isPlaying;
    }

    public boolean play(String path, OnCompletionListener listener) {
        boolean res = false;
        if (_player == null) {
            _player = new MediaPlayer();
        }
        _player.reset();

        try {
            _isPlaying = true;
            _player.setDataSource(path);
            _player.prepare();
            _player.start();
            _player.setOnCompletionListener(listener);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public void release() {
        if (_player != null) {
            _player.reset();
            _player.release();
            _player = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
        }

    }

    private String parseTime(int oldTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");// 时间格式
        String newTime = sdf.format(new Date(oldTime));
        return newTime;
    }

    public String getSumTime(String path) {
        MediaPlayer _player = new MediaPlayer();
        String time1 = "00:00/";
        String time = "00:00";
        try {
            _player.reset();
            _player.setDataSource(path);
            _player.prepare();
            time = parseTime(_player.getDuration());
            _player.reset();
            _player.release();
            _player = null;
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
        }
        return time1 + time;
    }

}
