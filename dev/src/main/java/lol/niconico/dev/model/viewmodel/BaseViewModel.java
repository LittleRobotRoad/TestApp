package lol.niconico.dev.model.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

/**
 * Created by ZhangQianqian on 2016/12/1  10:34.
 * email 415692689@qq.com
 */

public class BaseViewModel <T> extends BaseObservable{
    public ObservableField<T> data = new ObservableField<>();
    public T getData() {
        return data.get();
    }

    public void setData(T data) {
        this.data.set(data);
    }
}
