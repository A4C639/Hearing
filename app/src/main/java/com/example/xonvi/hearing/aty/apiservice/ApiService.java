package com.example.xonvi.hearing.aty.apiservice;

import com.example.xonvi.hearing.aty.model.BaseMD;
import com.example.xonvi.hearing.aty.model.login.LoginMD;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xonvi on 2017/3/6.
 */

public interface ApiService {

    //用户登陆

        @FormUrlEncoded
        @POST("Hearing/Login")
        Observable<BaseMD<LoginMD>> login(@Field("username") String username, @Field("userpass") String userpass);


}
