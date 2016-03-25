package com.mss.imageloader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.mss.imageloader.R;
import com.mss.imageloader.view.BitmapCache;

/**
 * Volley框架
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/14下午2:36
 */
public class VolleyImageShowActivity extends BaseActivity
{
    private NetworkImageView mNetworkImageView = null;

    private TextView mTextView = null;

    private String mImageUrl = null;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        initView();
        getIntentUrl();
        showImage();
    }

    @Override
    protected int contentView ()
    {

        return R.layout.activity_volley_image_show;
    }

    private void getIntentUrl ()
    {
        //获取传递过来的imageUrl，并将image显示出来，注意设置placeholer
        Intent intent = getIntent();
        mImageUrl = intent.getExtras().getString("imageUrl");
    }

    /**
     * 初始化对象
     */
    private void initView ()
    {

        mNetworkImageView = (NetworkImageView) this.findViewById(R.id.volley_imgv);
        mTextView = (TextView) this.findViewById(R.id.volley_tv);
    }

    private void showImage ()
    {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //实例化ImageLoader
        ImageLoader loader = new ImageLoader(requestQueue, new BitmapCache());
        //设置默认图片
        mNetworkImageView.setDefaultImageResId(R.mipmap.icon_placeholder);
        //设置错误图片
        mNetworkImageView.setErrorImageResId(R.mipmap.icon_failure);
        //设置图片url和ImageLoader
        mNetworkImageView.setImageUrl(mImageUrl, loader);
    }
}
