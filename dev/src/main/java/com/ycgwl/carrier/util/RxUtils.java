package com.ycgwl.carrier.util;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ZhangQianqian on 2016/9/28  09:49.
 * email 415692689@qq.com
 */

public class RxUtils {
    public static void onTimer(int seconds, Action1<Long> action1) {
        Observable.timer(seconds, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    public static void timer(int seconds, Action1<Long> action1) {
        Observable.timer(seconds, TimeUnit.SECONDS)
                .subscribe(action1);
    }
}
