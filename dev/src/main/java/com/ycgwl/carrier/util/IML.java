package com.ycgwl.carrier.util;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.ycgwl.carrier.R;

public class IML {

    /**
     * 加载图片
     *
     * @param imageView
     * @param url
     */
    public static void load(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (imageView == null) {
            return;
        }
        Glide.with(Utils.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param url
     */
    public static void loadIcon(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (imageView == null) {
            return;
        }
        Glide.with(Utils.getContext())
                .load(url)
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .into(getBitmapImageViewTarget(imageView));
    }

    private static BitmapImageViewTarget getBitmapImageViewTarget(final ImageView imageView) {
        return new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(Utils.getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        };
    }


    /**
     * 加载图片
     *
     * @param imageView
     * @param url
     */
    public static void loadBanner(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (imageView == null) {
            return;
        }
        Glide.with(Utils.getContext())
                .load(url)
                .dontAnimate()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }


    public static void clear() {
        Glide.get(Utils.getContext())
                .clearMemory();
    }

}
