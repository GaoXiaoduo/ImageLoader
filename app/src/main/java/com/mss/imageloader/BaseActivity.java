package com.mss.imageloader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * 基类
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/14下午2:55
 */
public abstract class BaseActivity extends Activity
{
    protected abstract int contentView ();

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(contentView());
        ButterKnife.bind(this);
    }

    /**
     * 界面跳转
     *
     * @param clazz 目标activity
     */
    public void changeActivity (Class<?> clazz)
    {

        Intent intent = new Intent(this, clazz);
        this.startActivity(intent);
    }
}
