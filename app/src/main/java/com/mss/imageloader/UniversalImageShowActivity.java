package com.mss.imageloader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Universal框架
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/14下午2:36
 */
public class UniversalImageShowActivity extends BaseActivity
{
    private static final String TAG = "UniversalActivity";

    private ImageView mImageView = null;

    String imageUrl = "http://pooyak.com/p/progjpeg/jpegload.cgi?o=3";
    //String imageUrl = "https://lh6.googleusercontent" +
    //        ".com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer
    // .jpg";

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        initView();
        getIntentUrl();
        //loadImage();
        //simpleImageLoadingListener();
        //loadImageSize();
        //displayImageOptions();
        displayImageProgress();
    }

    @Override
    protected int contentView ()
    {

        return R.layout.activity_universal_image_show;
    }

    private void initView ()
    {

        mImageView = (ImageView) this.findViewById(R.id.universal_imgv);
    }

    private void getIntentUrl ()
    {
        //获取传递过来的imageUrl，并将image显示出来，注意设置placeholer
        Intent intent = getIntent();
        imageUrl = intent.getExtras().getString("imageUrl");
    }

    /**
     * loadImage方法加载网络图片
     */
    private void loadImage ()
    {

        ImageLoader.getInstance().loadImage(imageUrl, new ImageLoadingListener()
        {

            @Override
            public void onLoadingStarted (String imageUri, View view)
            {

            }

            @Override
            public void onLoadingFailed (
                    String imageUri, View view,
                    FailReason failReason)
            {

                Log.d(TAG, "onLoadingFailed");
            }

            @Override
            public void onLoadingComplete (String imageUri, View view, Bitmap loadedImage)
            {

                Log.d(TAG, "onLoadingComplete");
                mImageView.setImageBitmap(loadedImage);
            }

            @Override
            public void onLoadingCancelled (String imageUri, View view)
            {

            }
        });
    }

    /**
     * 使用SimpleImageLoadingListener方法加载网络图片
     */
    private void simpleImageLoadingListener ()
    {

        ImageLoader.getInstance().loadImage(imageUrl, new SimpleImageLoadingListener()
        {
            @Override
            public void onLoadingComplete (
                    String imageUri, View view,
                    Bitmap loadedImage)
            {

                super.onLoadingComplete(imageUri, view, loadedImage);
                mImageView.setImageBitmap(loadedImage);
            }
        });
    }

    /**
     * 设定图片加载尺寸
     */
    private void loadImageSize ()
    {

        ImageSize mImageSize = new ImageSize(100, 100);

        ImageLoader.getInstance().loadImage(imageUrl, mImageSize, new SimpleImageLoadingListener()
        {

            @Override
            public void onLoadingComplete (
                    String imageUri, View view,
                    Bitmap loadedImage)
            {

                super.onLoadingComplete(imageUri, view, loadedImage);
                mImageView.setImageBitmap(loadedImage);
            }
        });
    }

    /**
     * 设置图片显示选项
     */
    private void displayImageOptions ()
    {

        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.gif_placholder)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.icon_failure)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().displayImage(imageUrl, mImageView, options);
    }

    /**
     * 设置进度条
     */
    private void displayImageProgress ()
    {

        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.gif_placholder)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.icon_failure)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().displayImage(imageUrl, mImageView, options, new
                SimpleImageLoadingListener(), new ImageLoadingProgressListener()

        {
            @Override
            public void onProgressUpdate (String imageUri, View view, int current, int total)
            {

                Log.d(TAG, "onProgressUpdate current:" + current + "; total:" + total);
            }
        });
    }
}
