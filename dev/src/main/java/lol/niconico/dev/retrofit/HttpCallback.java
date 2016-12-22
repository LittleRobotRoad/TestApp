package lol.niconico.dev.retrofit;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ZhangQianqian on 2016/9/1  17:01.
 * email 415692689@qq.com
 */
public abstract class HttpCallback<T> implements Callback<HttpResult<T>> {

    @Override
    public void onResponse(Call<HttpResult<T>> call, Response<HttpResult<T>> response) {
        HttpResult<T> result = response.body();

        if (result == null) {
            //通常是服务器出错返回了非约定格式
            onHttpFail(response.code(), "网络错误,请稍后再试");
        } else {
            if (result.getCode() == HttpResult.CODE_SUCCESS) {
                //正确返回约定的OK码
                onHttpSuccess(result.getData(), result.getMessage());
            }else if (result.getCode() == HttpResult.NO_DATA) {
                onNoData();
            } else {
                //返回约定的其他类型码，可根据返回码进行相对应的操作
                onHttpFail(result.getCode(), result.getMessage());
            }
        }
        onHttpComplete();
    }

    @Override
    public void onFailure(Call<HttpResult<T>> call, Throwable t) {
        onNetWorkError();
        onHttpComplete();
    }

    public abstract void onHttpSuccess(T resultData, String msg);

    public abstract void onHttpFail(int code, String msg);

    public abstract void onNetWorkError();

    public abstract void onNoData();

    public abstract void onHttpComplete();
}