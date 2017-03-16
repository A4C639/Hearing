package com.example.xonvi.hearing.aty.model.login;

import com.example.xonvi.hearing.aty.manger.NetWorkManger;
import com.example.xonvi.hearing.aty.model.BaseMD;
import com.example.xonvi.hearing.aty.utl.RxUtil;

import rx.Subscriber;

/**
 * Created by xonvi on 2017/3/6.
 */

//用户登陆的model层
public class LoginModel {

    public void login(String username, String userpass, final OnLoginFinishListener listener){

        new NetWorkManger().getServer().login(username,userpass)
                .compose(RxUtil.<BaseMD<LoginMD>>transformer())
                .subscribe(new Subscriber<BaseMD<LoginMD>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onLoginFailed();
                    }

                    @Override
                    public void onNext(BaseMD<LoginMD> loginMDBaseMD) {
                        listener.onLoginSuccess(loginMDBaseMD);
                    }
                });


    }
}
