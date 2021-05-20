package com.example.maptest.util;

import android.media.MediaRecorder;

public class RecordHelper {

    private final static int AUDIO_INPUT = MediaRecorder.AudioSource.MIC;
    private final static int AUDIO_OUTPUT = MediaRecorder.OutputFormat.AAC_ADTS;
    private final static int AUDIO_ENCODER = MediaRecorder.AudioEncoder.AAC;
    private static RecordHelper _helper = null;
    private boolean _isRecording = false;
    private MediaRecorder _recorder = null;

    private RecordHelper() {
    }

    public synchronized static RecordHelper getInstance() {
        if (_helper == null) {
            _helper = new RecordHelper();
        }
        return _helper;
    }

    public boolean start(String path) {
        Boolean res = false;
        try {
            if (_recorder != null) {
                _recorder.release();
                _recorder = null;
            }

            if (_recorder == null) {
                initRecorder(path);
                _recorder.prepare();
                _recorder.start();
            }
            res = true;
        } catch (Exception e) {
            e.fillInStackTrace();
            _isRecording = false;
            res = false;
        }
        return res;
    }

    public void stop() {
        try {
            close();
        } catch (Exception e) {
            e.fillInStackTrace();
        }

    }

    private void initRecorder(String path) {
        _recorder = new MediaRecorder();
        _recorder.setAudioSource(AUDIO_INPUT);
        _recorder.setOutputFormat(AUDIO_OUTPUT);
        _recorder.setAudioEncoder(AUDIO_ENCODER);
        _recorder.setOutputFile(path);
    }


    private void close() {
        if (_recorder != null) {
            _recorder.stop();
            _recorder.release();
            _recorder = null;
            _isRecording = false;
        }
    }


}
