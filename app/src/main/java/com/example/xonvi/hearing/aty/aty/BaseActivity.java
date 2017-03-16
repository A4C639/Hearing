package com.example.xonvi.hearing.aty.aty;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by xonvi on 2017/3/6.
 */

public abstract class BaseActivity extends FragmentActivity{
    protected Bundle saveInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.saveInstanceState = savedInstanceState;
        setMyContentView();
        init();
        //butterknife
        ButterKnife.bind(this);

    }


    //初始化
    protected void init() {

    }

    //设置视图
    protected void setMyContentView() {
    }
}
