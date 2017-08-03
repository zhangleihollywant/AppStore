package com.king.zlei.appstore;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static String[] REQUEST_PERSSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public static int REQUEST_CODE = 1;
    public static String LOGIN_DIR = "LOGIN_DIR";
    public static String ISFIRST = "isfirst";
    public SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        sp = getSharedPreferences(LOGIN_DIR, MODE_PRIVATE);
        boolean isFirst = sp.getBoolean(ISFIRST, true);
        checkPerssion();
        if (!isFirst) {
            goHome();
        }
    }

    @OnClick(R.id.login_in_home)
    public void goHome() {
        sp.edit().putBoolean(ISFIRST, false).commit();//改变是否是第一次
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    //检测权限
    public void checkPerssion() {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PermissionChecker.PERMISSION_GRANTED) {//不等于检测权限常量，没有得到权限
            ActivityCompat.requestPermissions(this, REQUEST_PERSSION, REQUEST_CODE);//请求权限,回调方法
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
            Toast.makeText(this, "请求权限成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "开启权限失败，影响使用", Toast.LENGTH_SHORT).show();
        }
    }
}
