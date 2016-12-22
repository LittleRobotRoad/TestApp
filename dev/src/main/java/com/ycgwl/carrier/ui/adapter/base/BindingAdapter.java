package com.ycgwl.carrier.ui.adapter.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by ZhangQianqian on 2016/9/23  14:03.
 * email 415692689@qq.com
 */

public class BindingAdapter<E, T extends ViewDataBinding> extends RecyclerViewAdapter<E, BindingAdapter.VH> {


    private int layoutRes;
    private int variableId;

    public BindingAdapter(@LayoutRes int layout, int variableId) {
        layoutRes = layout;
        this.variableId = variableId;
    }


    public void onBindHolder(BindingAdapter.VH holder, int i, E e) {
        holder.binding.setVariable(variableId, e);
    }

    public BindingAdapter.VH getViewHolder(ViewGroup parent, int viewType) {
        T binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutRes, parent, false);
        return new BindingAdapter.VH(binding);
    }

    class VH extends RecyclerView.ViewHolder {
        private T binding;

        VH(T binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
