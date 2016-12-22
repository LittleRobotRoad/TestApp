package lol.niconico.dev.retrofit.rx;


import lol.niconico.dev.retrofit.LogParamsInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jam on 16-5-17
 * Description:
 */
public class Api {
    private static ApiService SERVICE;

    public static ApiService getDefault() {
        if (SERVICE == null) {
            /**
             * addParams 增加固定参数
             * showLog 是否显示log
             * tag
             */
            LogParamsInterceptor interceptor = new LogParamsInterceptor.Builder()
//                .addParams()
                    .showLog(true)
                    .tag("RetrofitUtils")
                    .build();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            SERVICE = new Retrofit.Builder()
                    .baseUrl("http://gank.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build().create(ApiService.class);

        }
        return SERVICE;
    }

}
