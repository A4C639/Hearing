package com.example.xonvi.hearing.aty.manger;

import com.example.xonvi.hearing.aty.apiservice.ApiService;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xonvi on 2017/3/6.
 */

//网络请求的管理类
public class NetWorkManger {

    public static final String HOST = "http://192.168.155.1:8080/";


    public  ApiService getServer() {
       return new Retrofit.Builder().baseUrl(HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);

    }

}
