package com.ycgwl.carrier.retrofit.rx;


import com.ycgwl.carrier.ui.activity.base.BaseActivity;

import rx.Subscriber;

/**
 * Created by Jam on 16-7-21
 * Description: 自定义Subscribe
 */
public abstract class RxSubscribe<T> extends Subscriber<T> {
    private BaseActivity mContext;
    private String msg;

    /**
     * @param context context
     * @param msg     dialog message
     */
    public RxSubscribe(BaseActivity context, String msg) {
        this.mContext = context;
        this.msg = msg;
    }

    /**
     * @param context context
     */
    public RxSubscribe(BaseActivity context) {
        this(context, "请稍后...");
    }

    @Override
    public void onCompleted() {
        mContext.dismissLoadingDialog();
    }
    @Override
    public void onStart() {
        super.onStart();
        mContext.showLoadingDialog();
    }
    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        mContext.showLoadingDialog();
        if (false) { //这里自行替换判断网络的代码
            _onError("网络不可用");
        } else if (e instanceof ServerException) {
            _onError(e.getMessage());
        } else {
            _onError("请求失败，请稍后再试...");
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
