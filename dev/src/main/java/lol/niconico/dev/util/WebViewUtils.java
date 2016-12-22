package lol.niconico.dev.util;

import android.annotation.SuppressLint;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by ZhangQianqian on 2016/9/23  10:34.
 * email 415692689@qq.com
 */

public class WebViewUtils {

    @SuppressLint("SetJavaScriptEnabled")
    public static void setting(WebView webView) {
        WebSettings mWebSettings = webView.getSettings();
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setBuiltInZoomControls(true);
        mWebSettings.setDisplayZoomControls(false);
        mWebSettings.setLoadWithOverviewMode(true);

        webView.setInitialScale(30); //配合setUseWideViewPort  和 setSupportZoom 实现自适应宽度不缩放
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
    }
}
