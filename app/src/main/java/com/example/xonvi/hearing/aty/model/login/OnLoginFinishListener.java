package com.example.xonvi.hearing.aty.model.login;

import com.example.xonvi.hearing.aty.model.BaseMD;

/**
 * Created by xonvi on 2017/3/6.
 */
//d登陆完成后的回掉接口
public interface OnLoginFinishListener {

    void onLoginSuccess(BaseMD<LoginMD> loginMDBaseMD);
    void onLoginFailed();

}
