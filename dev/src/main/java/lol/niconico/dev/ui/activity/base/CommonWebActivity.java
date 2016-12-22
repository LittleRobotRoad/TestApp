package lol.niconico.dev.ui.activity.base;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import lol.niconico.dev.R;
import lol.niconico.dev.app.Constants;
import lol.niconico.dev.databinding.AcWebCommonBinding;
import lol.niconico.dev.util.CircularAnim;
import lol.niconico.dev.util.WebViewUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommonWebActivity extends BaseActivity<AcWebCommonBinding> {

    private static final String TAG = CommonWebActivity.class.getSimpleName();

    private String url;

    @Override
    protected void initGetIntent() {
        url = getIntent().getStringExtra(Constants.INTENT_URL);
    }

    /**
     * 加载启动动画
     */
    public static void start(final Context context, View v, @ColorRes int colorRes, String title, String url) {
        final Intent starter = new Intent(context, CommonWebActivity.class);
        starter.putExtra(Constants.INTENT_TITLE, title);
        starter.putExtra(Constants.INTENT_URL, url);

        CircularAnim.fullActivity((Activity) context, v)
                .colorOrImageRes(colorRes)
                .go(new CircularAnim.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd() {
                        context.startActivity(starter);
                    }
                });
    }

    /**
     * 无启动动画
     */
    public static void start(final Context context, String title, String url) {
        final Intent starter = new Intent(context, CommonWebActivity.class);
        starter.putExtra(Constants.INTENT_TITLE, title);
        starter.putExtra(Constants.INTENT_URL, url);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_web_common;
    }

    @Override
    public void initView() {
        binding.inAcWebCommonTitle.tvTitle.setText(getIntent().getStringExtra(Constants.INTENT_TITLE));
        binding.inAcWebCommonTitle.ivBack.setOnClickListener(this);
        initWeb();
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshData() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initWeb() {
        WebViewUtils.setting(binding.wvAcCommonContent); //webview通用基础设置
        binding.wvAcCommonContent.loadUrl(url);
        Log.e(TAG, "WebUrl: " + binding.wvAcCommonContent.getUrl());
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ivBack) {
            finish();

        }
    }

    @Override
    public void onBackPressed() {
        binding.wvAcCommonContent.goBack();
    }
}
