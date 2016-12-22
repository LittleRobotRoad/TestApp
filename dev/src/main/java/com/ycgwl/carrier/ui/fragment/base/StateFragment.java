package com.ycgwl.carrier.ui.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ycgwl.carrier.ui.activity.base.CompState;
import com.ycgwl.carrier.ui.activity.base.ComponentStateHelper;
import com.ycgwl.carrier.ui.activity.base.IComponentStatable;

/**
 * 1. Activity定义了许多的状态，用户可以通过set(state)来切换状态，每个状态对应一个View，用户还可以自定义这个View
 * <p/>
 * 每个状态对应一个UI 布局,用户可以自定义这些布局,通过setXX方法（e.g setEmptyView）
 *
 * 2. Fragment默认没有title bar,User可以自定义自己的title bar
 *
 */
public abstract class StateFragment extends Fragment implements IComponentStatable {

    private LinearLayout mRootView;
    private RelativeLayout mMainView;

    private ComponentStateHelper mHelper;
    private CompState mCompState = CompState.DATA;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = new LinearLayout(getActivity());
        mRootView.setOrientation(LinearLayout.VERTICAL);
        mRootView.setGravity(Gravity.CENTER);
        mMainView = new RelativeLayout(getActivity());
        LinearLayout.LayoutParams mainViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mRootView.addView(mMainView, mainViewParams);
        mHelper = new ComponentStateHelper(getActivity(), mMainView);

        View newContentView = createDataView(inflater, container, savedInstanceState);
        mHelper.setDataContentView(newContentView);
        mHelper.setEmptyErrorView(createEmptyErrorView(inflater));
        mHelper.setEmptyInvalidNetView(createEmptyInvalidNetView(inflater));
        mHelper.setEmptyRefreshing(createEmptyRefreshingView(inflater));
        mHelper.setEmptyView(createEmptyView(inflater));
        mHelper.changeState(mCompState);
        return mRootView;
    }


    /**
     * 请确保在onCreateView之后调用
     *
     * @param state
     */
    public void setState(CompState state) {
        if (mCompState != state) {
            mCompState = state;
            mHelper.changeState(mCompState);
        }
    }


    public CompState getState() {
        return mCompState;
    }


    public View getEmptyView() {
        return mHelper.getEmptyView();
    }


    public View getDataView() {
        return mHelper.getDataContentView();
    }


    public View getEmptyRefreshingView() {
        return mHelper.getEmptyRefreshingView();
    }


    public View getEmptyInvalidNetView() {
        return mHelper.getEmptyInvalidNetView();
    }


    public View getEmptyErrorView() {
        return mHelper.getEmptyErrorView();
    }


    public View createEmptyView(LayoutInflater inflater){
        return mHelper.getEmptyView();
    }


    public View createEmptyInvalidNetView(LayoutInflater inflater) {
        return mHelper.getEmptyInvalidNetView();
    }


    public View createEmptyRefreshingView(LayoutInflater inflater) {
        return mHelper.getEmptyRefreshingView();
    }


    public View createEmptyErrorView(LayoutInflater inflater) {
        return mHelper.getEmptyErrorView();
    }

    private View mCustomToolbar;

    public void setCustomToolbar(View title) {
        mCustomToolbar = title;
        mRootView.addView(title, 0);
    }

    public View getCustomToolbar() {
        return mCustomToolbar;
    }


    public void hideToolbar() {
        View customToolbar = getCustomToolbar();
        if (customToolbar != null && customToolbar.getVisibility() == View.VISIBLE) {
            customToolbar.setVisibility(View.GONE);
        }
    }


    public void showToolbar() {
        View customToolbar = getCustomToolbar();
        if (customToolbar != null && customToolbar.getVisibility() != View.VISIBLE) {
            customToolbar.setVisibility(View.VISIBLE);
        }
    }


}
