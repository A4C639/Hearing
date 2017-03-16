package com.example.xonvi.hearing.aty.presenter;

import com.example.xonvi.hearing.aty.apiservice.IView.ILoginView;
import com.example.xonvi.hearing.aty.model.BaseMD;
import com.example.xonvi.hearing.aty.model.login.LoginMD;
import com.example.xonvi.hearing.aty.model.login.LoginModel;
import com.example.xonvi.hearing.aty.model.login.OnLoginFinishListener;

/**
 * Created by xonvi on 2017/3/6.
 */

//用户登陆的代理
public class LoginPresenter implements OnLoginFinishListener {

    private ILoginView loginView;
    public LoginPresenter(ILoginView iLoginView){
        this.loginView = iLoginView;
    }

    public void loginPresent(String username,String userpass){
        new LoginModel().login(username,userpass,this);

    }

//登陆成功刷新视图
    @Override
    public void onLoginSuccess(BaseMD<LoginMD> loginMDBaseMD) {
        loginView.onSuccessView();
    }

    //登陆失败视图
    @Override
    public void onLoginFailed() {
        loginView.onFailedView();
    }
}
