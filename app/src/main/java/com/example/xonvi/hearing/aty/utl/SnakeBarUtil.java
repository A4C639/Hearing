package com.example.xonvi.hearing.aty.utl;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by xonvi on 2017/3/6.
 */

public class SnakeBarUtil {



    public static void normalShortSnakeBar( View root,String text){
      Snackbar.make(root,text,Snackbar.LENGTH_SHORT).show();
    }

    public static void normalLongSnakeBar(View root, String text){
        Snackbar.make(root,text,Snackbar.LENGTH_LONG).show();
    }
}
