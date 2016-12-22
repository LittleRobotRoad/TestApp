package lol.niconico.dev.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZhangQianqian on 2016/7/26  13:52.
 * email 415692689@qq.com
 */
public class RetrofitUtils {

    private static Retrofit retrofit;
    public static final String BASE_URL = "http://172.16.251.82:20888/";

    public static TrackService getApi() {
        if (retrofit == null) {
            initRetrofit();
        }
        return retrofit.create(TrackService.class);
    }

    private static void initRetrofit() {
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


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .callFactory(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
