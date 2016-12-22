package com.ycgwl.carrier.ui.activity.base;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ycgwl.carrier.R;

import java.lang.reflect.Field;


public abstract class BaseActivity<T extends ViewDataBinding> extends StateActivity implements
        OnClickListener {

    protected ProgressDialog mLoadingDialog;
    protected T binding;

    @Override
    protected void onDestroy() {
        if (mLoadingDialog != null) {
            if (mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
            mLoadingDialog = null;
        }
        super.onDestroy();
    }


    @Override
    public View createDataView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        hideToolbar();
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        refreshContentView();
        return binding.getRoot();
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }//强制设置为竖屏
        initStutas();
    }

    protected View refreshContentView() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.setMessage(getString(R.string.loading));
        }

        initGetIntent();
        initView();
        initData();
        return binding.getRoot();
    }

    protected void setLoadingDialogMessage(String msg) {
        mLoadingDialog.setMessage(msg);
    }

    /**
     * 初始化intent数据
     */
    protected abstract void initGetIntent();


    /**
     * 显示加载中对话框
     */
    public void showLoadingDialog() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (!mLoadingDialog.isShowing()) {
                    mLoadingDialog.show();
//					animationDrawable.start();
                }

            }
        });

    }

    /**
     * 隐藏加载对话框
     */
    public void dismissLoadingDialog() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
                    mLoadingDialog.dismiss();
//					animationDrawable.stop();
                }
            }
        });

    }

    /**
     * 返回内容View布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();


    @Override
    public void onClick(View v) {
    }

    /**
     * 刷新数据（重新联网后调用）
     */
    public abstract void refreshData();

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }


    private void initStutas() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 全透明实现
            //getWindow.setStatusBarColor(Color.TRANSPARENT)
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

            if (navigationTranslucent()){
                window.setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            if (navigationTranslucent()){
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
    }

    protected boolean navigationTranslucent(){
        return true;
    }

    /**
     * 得到statusBar高度
     *
     * @return
     */
    public int getStatusBarSize() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sBar = 38;//默认为38，貌似大部分是这样的
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sBar = getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sBar;
    }
}
