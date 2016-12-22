package com.ycgwl.carrier.ui.activity.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 组建的状态。
 * 
 * recyclerBase中每个组件（Activity or Fragment）都必须有此接口
 * recyclerBase规定，每个组件都有几种状态
 * 
 */
public interface IComponentStatable {

	/**
	 * 组件有data时的UI 布局，取名为createDataContentView 是为了与 createContentView 区分开
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	View createDataView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


	void setState(CompState state);


	CompState getState();


	View createEmptyView(LayoutInflater inflater);


	View createEmptyInvalidNetView(LayoutInflater inflater);


	View createEmptyRefreshingView(LayoutInflater inflater);


	View createEmptyErrorView(LayoutInflater inflater);


	View getEmptyView();


	View getDataView();


	View getEmptyRefreshingView();


	View getEmptyInvalidNetView();


	View getEmptyErrorView();

}
