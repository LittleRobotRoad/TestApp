package com.ycgwl.carrier.ui.fragment.base;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycgwl.carrier.ui.activity.base.CompState;

/**
 * Created by ZhangQianqian on 2016/9/9  09:57.
 * email 415692689@qq.com
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends StateFragment implements View.OnClickListener{

    private static final String TAG = BaseFragment.class.getName();
    private ProgressDialog mLoadingDialog;
    protected View mContentView = null;
    private LayoutInflater inflater;
    protected T binding;
    protected FragmentActivity mActivity;

    public BaseFragment() {

    }

    @Override
    public View createDataView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mContentView == null) {
            this.inflater = inflater;
            mActivity = getActivity();
            binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            mContentView=binding.getRoot();
            this.initBaseView();
            initView(mContentView);
            initData();
            initRec();
            setState(CompState.DATA);
        }
        return mContentView;
    }
    protected void initRec(){}
    private void initBaseView() {
        this.mLoadingDialog = new ProgressDialog(this.getActivity());
        this.mLoadingDialog.setMessage("加载中...");
    }


    protected void showLoadingDialog() {
        this.mLoadingDialog.show();
    }

    protected void dismissLoadingDialog() {
        this.mLoadingDialog.dismiss();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
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
    protected abstract void initView(View contentView);

    /**
     * 初始化数据
     */
    protected abstract void initData();


    @Override
    public void onClick(View view) {
    }

    public LayoutInflater getInflater() {
        return inflater;
    }
}
