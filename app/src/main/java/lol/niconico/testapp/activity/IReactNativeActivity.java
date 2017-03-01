package lol.niconico.testapp.activity;

import android.content.Context;
import android.media.AudioManager;

import com.facebook.react.ReactActivity;

import javax.annotation.Nullable;

/**
 * Created by ZhangQianqian on 2017/2/9  11:02.
 * email 415692689@qq.com
 */

public class IReactNativeActivity extends ReactActivity {

    private static final String TAG = IReactNativeActivity.class.getSimpleName();

    @Nullable
    @Override
    protected String getMainComponentName() {
        AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        return "HelloWorld";
    }


}
