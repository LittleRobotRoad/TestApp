package com.ycgwl.carrier.retrofit;

import static android.R.attr.data;
import static android.R.id.message;

/**
 * Created by ZhangQianqian on 2016/9/1  17:00.
 * email 415692689@qq.com
 */
public class HttpResult<T> {
    public static final int CODE_SUCCESS = 200;
    public static final int SERVER_ERROR = 30000; // serverError
    public static final int NO_DATA = 37000; //没有数据
    private int resultCode;
    private String reason;
    private T resultInfo;

    public int getCode() {
        return resultCode;
    }

    public void setCode(int code) {
        this.resultCode = code;
    }

    public String getMessage() {
        return reason;
    }

    public void setMessage(String message) {
        this.reason = message;
    }

    public T getData() {
        return resultInfo;
    }

    public void setData(T data) {
        this.resultInfo = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + resultCode +
                ", message='" + reason + '\'' +
                ", data=" + resultInfo +
                '}';
    }
}
