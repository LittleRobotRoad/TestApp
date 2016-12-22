package lol.niconico.dev.ui.adapter.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ZhangQianqian on 2016/9/23  09:25.
 * email 415692689@qq.com
 */
public  class BindingAllAdapter<E,T extends ViewDataBinding> extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private int layoutRes;
    private int variableId;
    private ArrayList<E> mData;
    private OnItemClickListener l;


    public BindingAllAdapter(@LayoutRes int layout, int variableId) {
        layoutRes = layout;
        this.variableId = variableId;
    }


    public void onBindHolder(VH holder, int i, E e) {
        holder.binding.setVariable(variableId, e);
    }

    public VH getViewHolder(ViewGroup parent, int viewType) {
        T binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutRes, parent, false);
        return new VH(binding);
    }

    class VH extends RecyclerView.ViewHolder {
        private T binding;

        VH(T binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(BindingAllAdapter.OnItemClickListener l) {
        this.l = l;
    }

    public interface OnItemClickListener {
        void onItemClick(View views, int position);
        boolean onItemLongClick(View v, int position);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {

        E e = mData.get(i);
        VH t = (VH) holder;
        onBindHolder(t, i, e);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final VH viewHolder;
        viewHolder = getViewHolder(parent,viewType);

        if (l != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.onItemClick(viewHolder.itemView, viewHolder.getAdapterPosition());
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    boolean isclick = l.onItemLongClick(viewHolder.itemView, viewHolder.getAdapterPosition());
                    return isclick;
                }
            });

        }
        return viewHolder;
    }


    public ArrayList<E> getData() {
        return mData;
    }

    public void setData(ArrayList<E> mData) {
        this.mData = mData;
    }

}
