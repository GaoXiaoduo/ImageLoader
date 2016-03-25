package com.mss.imageloader.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.mss.imageloader.R;
import com.squareup.picasso.Picasso;

/**
 * Picasso图片显示
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/17下午4:15
 */
public class PicassoImageShowActivity extends Activity
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
        Picasso.with(PicassoImageShowActivity.this).load(imageUrl).placeholder(R.mipmap
                .icon_placeholder)
                .error(R.mipmap.icon_failure).into(imageView);

    }

}
