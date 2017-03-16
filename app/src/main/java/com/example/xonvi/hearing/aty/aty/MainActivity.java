package com.example.xonvi.hearing.aty.aty;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.speech.tts.TextToSpeechService;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.example.xonvi.hearing.R;

import java.util.Locale;

public class MainActivity extends Activity implements SpeechSynthesizerListener {


    private static final String TEXT_MODEL_FILE_FULL_PATH_NAME = "";
    private static final String LICENSE_FILE_FULL_PATH_NAME = "";
    private static final String SPEECH_MODEL_FILE_FULL_PATH_NAME = "";
    SpeechSynthesizer speechSynthesizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTTS();

    }


    //初始化百度TTS
    private void initTTS() {
        //获取tts实例
        speechSynthesizer = SpeechSynthesizer.getInstance();
        //s设置app上下文
        speechSynthesizer.setContext(getApplicationContext());
        //设置tts监听器
        speechSynthesizer.setSpeechSynthesizerListener(this);
        //文本模型文件路径，文件的绝对路径(离线引擎使用)
        speechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE,
                TEXT_MODEL_FILE_FULL_PATH_NAME);
        //声学模型文件路径，文件的绝对路径(离线)
        speechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE,
                SPEECH_MODEL_FILE_FULL_PATH_NAME);
        //本地授权文件路劲，如未设置将使用默认路径。设置临时授权文件路径，LICENCE_FILE_NAME请替换成
        //临时授权文件的实际路径，仅在使用临时license文件是需要进行设置，如果在 应用管理中开通了离线授权
        //不需要设置该参数，建议将改行代码删除（离线引擎）
        speechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_LICENCE_FILE,
                LICENSE_FILE_FULL_PATH_NAME);
        //appid
        speechSynthesizer.setAppId("");
        speechSynthesizer.setApiKey("apikey","secretkey");
        AuthInfo authInfo = speechSynthesizer.auth(TtsMode.MIX);
        speechSynthesizer.initTts(TtsMode.MIX);

    }



    //---------------------------------------------------------------------------------

    @Override
    public void onSynthesizeStart(String s) {

    }

    @Override
    public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {

    }

    @Override
    public void onSynthesizeFinish(String s) {

    }

    @Override
    public void onSpeechStart(String s) {

    }

    @Override
    public void onSpeechProgressChanged(String s, int i) {

    }

    @Override
    public void onSpeechFinish(String s) {

    }

    @Override
    public void onError(String s, SpeechError speechError) {

    }
}
