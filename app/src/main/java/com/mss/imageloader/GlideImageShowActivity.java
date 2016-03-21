package com.mss.imageloader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mss.imageloader.view.GlideRoundTransform;

/**
 * Picasso图片显示
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/17下午4:15
 */
public class GlideImageShowActivity extends Activity
{

    private ImageView imageView;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picasso_image_show);

        imageView = (ImageView) findViewById(R.id.picasso_full_imgv);

        //获取传递过来的imageUrl，并将image显示出来，注意设置placeholer
        Intent intent = getIntent();
        String imageUrl = intent.getExtras().getString("imageUrl");
        Glide.with(GlideImageShowActivity.this).load(imageUrl)
                .placeholder(R.mipmap.gif_placholder)
                .error(R.mipmap.icon_failure)
                .transform(new GlideRoundTransform(this, 10))
                //.crossFade()//淡入淡出效果
                .into(imageView);

    }

}
