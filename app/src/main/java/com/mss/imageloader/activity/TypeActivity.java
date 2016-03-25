package com.mss.imageloader.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mss.imageloader.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 图片加载框架类型
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/14下午2:14
 */
public class TypeActivity extends BaseActivity
{
    @Bind(R.id.btn_fresco)
    Button btnFresco;

    @Bind(R.id.btn_glide)
    Button btnGlide;

    @Bind(R.id.btn_picasso)
    Button btnPicasso;

    @Bind(R.id.btn_universal)
    Button btnUniversal;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected int contentView ()
    {

        return R.layout.activity_type;
    }

    @OnClick({R.id.btn_fresco, R.id.btn_glide, R.id.btn_picasso, R.id.btn_universal, R.id.btn_volley})
    void onClick (View view)
    {

        switch (view.getId())
        {
            case R.id.btn_fresco:
                changeActivity(FrescoActivity.class);
                break;
            case R.id.btn_glide:
                changeActivity(GlideActivity.class);
                break;
            case R.id.btn_picasso:
                changeActivity(PicassoActivity.class);
                break;
            case R.id.btn_universal:
                changeActivity(UniversalActivity.class);
                break;
            case R.id.btn_volley:
                changeActivity(VolleyActivity.class);
                break;
        }
    }
}
