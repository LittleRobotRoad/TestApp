package lol.niconico.dev.retrofit;


import lol.niconico.dev.R;
import lol.niconico.dev.util.ToastUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ZhangQianqian on 2016/9/1  17:01.
 * email 415692689@qq.com
 */
public abstract class HttpCallbackSuccess<T> implements Callback<HttpResult<T>> {

    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_FAILURE = 1;
    public static final int STATUS_NO_DATA = 2;
    public static final int STATUS_SERVER_ERROR = 3;

    @Override
    public void onResponse(Call<HttpResult<T>> call, Response<HttpResult<T>> response) {
        HttpResult<T> result = response.body();

        if (result == null) {
            //通常是服务器出错返回了非约定格式
            ToastUtil.show_short("responseCode: " + response.code() + "   网络错误,请稍后再试");
            onHttpComplete(STATUS_SERVER_ERROR);
        } else {
            if (result.getCode() == HttpResult.CODE_SUCCESS) {
                if (result.getData() instanceof List) {
                    List data = (List) result.getData();
                    if (data.size() == 0) {
                        onNoData();
                    } else {
                        onHttpSuccess(result.getData(), result.getMessage());
                        onHttpComplete(STATUS_SUCCESS);
                    }
                } else {
                    //正确返回约定的OK码
                    onHttpSuccess(result.getData(), result.getMessage());
                    onHttpComplete(STATUS_SUCCESS);
                }

            } else if (result.getCode() == HttpResult.NO_DATA) {
                onNoData();
            } else {
                onFailure(call, null);
                //返回约定的其他类型码，可根据返回码进行相对应的操作
                ToastUtil.show_short(result.getMessage()+"");
//                ToastUtil.show_short("code: " + result.getCode() + "   message: " + result.getMessage());
                onHttpComplete(STATUS_SERVER_ERROR);
            }
        }
    }

    @Override
    public void onFailure(Call<HttpResult<T>> call, Throwable t) {
        ToastUtil.show_short(R.string.text_network_exp);
        onHttpComplete(STATUS_FAILURE);
    }

    public abstract void onHttpSuccess(T resultData, String msg);

    public abstract void onHttpComplete(int status);

    public void onNoData() {
        ToastUtil.show_short(R.string.text_not_data);
        onHttpComplete(STATUS_NO_DATA);
    }
}