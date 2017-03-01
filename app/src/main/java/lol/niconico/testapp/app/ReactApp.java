package lol.niconico.testapp.app;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.util.Collections;
import java.util.List;

import lol.niconico.dev.BuildConfig;
import lol.niconico.dev.app.BaseApplication;

/**
 * Created by ZhangQianqian on 2017/2/9  11:05.
 * email 415692689@qq.com
 */

public class ReactApp extends BaseApplication implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Collections.<ReactPackage>singletonList(new MainReactPackage());
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }
}
