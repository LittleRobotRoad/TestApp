package com.ycgwl.carrier.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ycgwl.carrier.R;
import com.ycgwl.carrier.ui.adapter.base.RecyclerViewAdapter;
import com.ycgwl.carrier.util.IML;

/**
 * Created by ZhangQianqian on 2016/11/19  02:09.
 * email 415692689@qq.com
 */

public class ChooseImageAdapter extends RecyclerViewAdapter<String, ChooseImageAdapter.VH> {
    public static final int MODE_VIEW = 0;
    public static final int MODE_EDIT = 1;
    public int image_count = 6;
    private int mode;
    private ChooseImageListener l;

    public ChooseImageAdapter(int mode, int image_count) {
        this.mode = mode;
        this.image_count = image_count;
    }

    @Override
    public void onBindHolder(VH holder, int i, String image) {
        if (mode == MODE_EDIT) {
            if (i < getData().size()) {
                IML.load(holder.ivImage, image);
                holder.ivDelete.setVisibility(View.VISIBLE);
            } else {
                holder.ivDelete.setVisibility(View.GONE);
                holder.ivImage.setImageResource(R.drawable.ic_add);
            }
        } else {
            IML.load(holder.ivImage, image);
        }
    }

    @Override
    public int getDataSize() {
        if (mode == MODE_EDIT) {
            return getData().size() > image_count - 1 ? image_count : getData().size() + 1;
        } else {
            return super.getDataSize();
        }
    }

    @Override
    public VH getViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new VH(v);
    }

    class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivDelete, ivImage;

        public VH(View itemView) {
            super(itemView);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            ivDelete.setOnClickListener(this);
            ivImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            int i = view.getId();
            if (i == R.id.ivDelete) {
                getData().remove(adapterPosition);
                notifyItemRemoved(adapterPosition);

            } else if (i == R.id.ivImage) {
                if (mode == MODE_EDIT) {
                    if (adapterPosition == getData().size() && l != null) {
                        l.chooseImage(adapterPosition);
                    }
                } else {
                    // TODO: 2016/11/19
                }

            }
        }
    }

    public void setOnChooseImageListener(ChooseImageListener l) {
        this.l = l;
    }

    public interface ChooseImageListener {
        void chooseImage(int position);
    }

}
