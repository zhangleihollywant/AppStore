package com.king.zlei.appstore;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import java.lang.reflect.Field;

/**
 * <b>Create Date:</b> 2017/8/1<br>
 * <b>Author:</b> Zhanglei<br>
 * <b>Description:</b> <br>
 */
public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏

        setContentView(R.layout.home_activity);
        int status = getStatusBarHeight();
        Log.d(TAG, "onCreate: "+status);
    }

    //通过反射获取状态栏的高度
    public int getStatusBarHeight() {
        try {
            //反射获取类对象
            Class<?> aClass = Class.forName("com.android.internal.R$dimen");
            //获取类对象
            Object o = aClass.newInstance();
            //取属性
            Field status_bar_height = aClass.getField("status_bar_height");
            //获取值
            Object o1 = status_bar_height.get(o);
            int height = Integer.parseInt(o1.toString());
            return getResources().getDimensionPixelSize(height);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
