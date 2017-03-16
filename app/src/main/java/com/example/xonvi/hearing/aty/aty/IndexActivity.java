package com.example.xonvi.hearing.aty.aty;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.xonvi.hearing.R;
import com.example.xonvi.hearing.aty.apiservice.IView.ILoginView;
import com.example.xonvi.hearing.aty.manger.NetWorkManger;
import com.example.xonvi.hearing.aty.model.BaseMD;
import com.example.xonvi.hearing.aty.model.login.LoginMD;
import com.example.xonvi.hearing.aty.myview.ClipRevealFrame;
import com.example.xonvi.hearing.aty.presenter.LoginPresenter;
import com.example.xonvi.hearing.aty.utl.AnimatorUtils;
import com.example.xonvi.hearing.aty.utl.RxUtil;
import com.example.xonvi.hearing.aty.utl.SnakeBarUtil;
import com.example.xonvi.hearing.aty.utl.SoftInputUtil;
import com.example.xonvi.hearing.aty.utl.ToastUtil;
import com.ogaclejapan.arclayout.Arc;
import com.ogaclejapan.arclayout.ArcLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

import static android.view.ViewAnimationUtils.createCircularReveal;

/**
 * Created by xonvi on 2017/3/6.
 */

public class IndexActivity extends BaseActivity implements ILoginView {

    //进度条
    ProgressDialog progressDialog;
    //drawerlayout
    @BindView(R.id.drawer_aty_index)DrawerLayout drawer_aty_index;
    //
    @BindView(R.id.cart_layout_right)FrameLayout cart_layout_right;

    //位于cliprevelframe中心的按钮
    @BindView(R.id.center_item) View centerItem;
    //弹出布局页面
    @BindView(R.id.fl_aty_index_menu_layout)ClipRevealFrame fl_aty_index_menu_layout;
    //floatbutton
    @BindView(R.id.fab_aty_index)FloatingActionButton fab_aty_index;
    //arc layout
    private ArcLayout arcLayout;
    //父布局
    @BindView(R.id.ll_parent_aty_index)LinearLayout ll_parent_aty_index;
    //coordinator layout 协调器布局
    @BindView(R.id.cdl_root_layout)CoordinatorLayout cdl_root_layout;
//    //input user name
//    @BindView(R.id.edt_username)EditText edt_username;
//    //input user pass
//    @BindView(R.id.edt_userpass)EditText edt_userpass;


    @OnClick({R.id.btn_net,R.id.btn_net2,R.id.fab_aty_index,R.id.iv_aty_index_more,R.id.center_item})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_net:
//                String username = edt_username.getText().toString();
//                String userpass =edt_userpass.getText().toString();
//                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(userpass)){
//                    ToastUtil.toast("用户名或者密码为空");
//                    return;
//                }
//
//                login(username,userpass);
                break;
            case R.id.btn_net2:
                Snackbar.make(cdl_root_layout,"snake222222222",Snackbar.LENGTH_LONG).setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                break;
            case R.id.fab_aty_index:
//                SnakeBarUtil.normalShortSnakeBar(cdl_root_layout,"点击了fab");
                onFabClick(fab_aty_index);

                break;
            //更多选项
            case R.id.iv_aty_index_more:
                Log.e("more","ffffffffffff");
                drawer_aty_index.openDrawer(cart_layout_right);
                break;
            case R.id.center_item:
                startActivity(new Intent(this,CoordinatorActivity.class));
                break;
        }
    }

    //----------------------------------------------------arc layout tumblr------------------------------------------------------------

    //当按下floatbutton时
    private void onFabClick(View v) {
        int x = (v.getLeft() + v.getRight()) / 2;
        int y = (v.getTop() + v.getBottom()) / 2;
        float radiusOfFab = 1f * v.getWidth() / 2f;
        float radiusFromFabToRoot = (float) Math.hypot(
                Math.max(x, ll_parent_aty_index.getWidth() - x),
                Math.max(y, ll_parent_aty_index.getHeight() - y));

        if (v.isSelected()) {
            hideMenu(x, y, radiusFromFabToRoot, radiusOfFab);
        } else {
            showMenu(x, y, radiusOfFab, radiusFromFabToRoot);
        }
        v.setSelected(!v.isSelected());
    }


    //显示 arc layout展开界面
    private void showMenu(int cx, int cy, float startRadius, float endRadius) {

        fl_aty_index_menu_layout.setVisibility(View.VISIBLE);

        List<Animator> animList = new ArrayList<>();

        Animator revealAnim = createCircularReveal(fl_aty_index_menu_layout, cx, cy, startRadius, endRadius);
        revealAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        revealAnim.setDuration(200);

        animList.add(revealAnim);
        animList.add(createShowItemAnimator(centerItem));

        for (int i = 0, len = arcLayout.getChildCount(); i < len; i++) {
            animList.add(createShowItemAnimator(arcLayout.getChildAt(i)));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.playSequentially(animList);
        animSet.start();

    }


    //隐藏 arc layout展开界面
    private void hideMenu(int cx, int cy, float startRadius, float endRadius) {

        List<Animator> animList = new ArrayList<>();

        for (int i = arcLayout.getChildCount() - 1; i >= 0; i--) {
            animList.add(createHideItemAnimator(arcLayout.getChildAt(i)));
        }

        animList.add(createHideItemAnimator(centerItem));

        Animator revealAnim = createCircularReveal(fl_aty_index_menu_layout, cx, cy, startRadius, endRadius);
        revealAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        revealAnim.setDuration(200);
        revealAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                fl_aty_index_menu_layout.setVisibility(View.INVISIBLE);
            }
        });

        animList.add(revealAnim);

        AnimatorSet animSet = new AnimatorSet();
        animSet.playSequentially(animList);
        animSet.start();
    }


    //显示展开菜单布局的动画
    private Animator createShowItemAnimator(View item) {
        float dx = centerItem.getX() - item.getX();
        float dy = centerItem.getY() - item.getY();

        item.setScaleX(0f);
        item.setScaleY(0f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.scaleX(0f, 1f),
                AnimatorUtils.scaleY(0f, 1f),
                AnimatorUtils.translationX(dx, 0f),
                AnimatorUtils.translationY(dy, 0f)
        );

        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(50);
        return anim;
    }
    //隐藏展开菜单布局的动画
    private Animator createHideItemAnimator(final View item) {
        final float dx = centerItem.getX() - item.getX();
        final float dy = centerItem.getY() - item.getY();

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.scaleX(1f, 0f),
                AnimatorUtils.scaleY(1f, 0f),
                AnimatorUtils.translationX(0f, dx),
                AnimatorUtils.translationY(0f, dy)
        );

        anim.setInterpolator(new DecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }
        });
        anim.setDuration(50);
        return anim;
    }

    //展开菜单是像涟漪圆圈的动画
    private Animator createCircularReveal(final ClipRevealFrame view, int x, int y, float startRadius,
                                          float endRadius) {
        final Animator reveal;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            reveal = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
        } else {
            view.setClipOutLines(true);
            view.setClipCenter(x, y);
            reveal = ObjectAnimator.ofFloat(view, "ClipRadius", startRadius, endRadius);
            reveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setClipOutLines(false);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        return reveal;
    }

//----------------------------------------------------------------------------------------------------------------
    //用户登陆
    private void login(String username,String userpass) {
//        new LoginPresenter(this).loginPresent(username,userpass);

        progressDialog.show();
        SoftInputUtil.hiddenSoftInput(this);

        new NetWorkManger().getServer().login(username,userpass)
                .compose(RxUtil.<BaseMD<LoginMD>>transformer())
                .subscribe(new Subscriber<BaseMD<LoginMD>>() {
                    @Override
                    public void onCompleted() {
                        ToastUtil.toast("网络请求完成");
                        progressDialog.dismiss();
                    }
                    @Override
                    public void onError(Throwable e) {
                        //如果出错了就不会onComlete
                        Log.e("loginerror",e.toString());
                        SnakeBarUtil.normalShortSnakeBar(cdl_root_layout,"出错了");
                        onCompleted();
                    }
                    @Override
                    public void onNext(BaseMD<LoginMD> loginMDBaseMD) {
                        Log.e("successs",loginMDBaseMD.toString());
                        ToastUtil.toast("成功");
                    }
                });


    }

    @Override
    protected void setMyContentView() {
        setContentView(R.layout.activity_index);
    }

    @Override
    protected void init() {

        ToastUtil.initToast(this);
        progressDialog = new ProgressDialog(this);
        arcLayout = (ArcLayout) findViewById(R.id.al_aty_index);











    }

    @Override
    public void onSuccessView() {

    }

    @Override
    public void onFailedView() {

    }
}
