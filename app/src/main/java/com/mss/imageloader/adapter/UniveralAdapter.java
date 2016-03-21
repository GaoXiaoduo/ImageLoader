package com.mss.imageloader.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.mss.imageloader.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Univeral适配器
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/17下午3:55
 */
public class UniveralAdapter extends BaseAdapter
{

    private Context context;


    private ArrayList<String> urls;

    private DisplayImageOptions options = null;

    public UniveralAdapter (Context context, ArrayList<String> urls)
    {

        this.context = context;
        this.urls = urls;
        initOptional();
    }

    private void initOptional ()
    {
        //显示图片的配置
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.gif_placholder)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.icon_failure)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public int getCount ()
    {

        return urls.size();
    }

    @Override
    public Object getItem (int i)
    {

        return urls.get(i);
    }

    @Override
    public long getItemId (int i)
    {

        return i;
    }

    @Override
    public View getView (int i, View view, ViewGroup viewGroup)
    {

        ImageView imageView = new ImageView(context);


        ImageLoader.getInstance().displayImage(urls.get(i), imageView, options);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(450, 450));

        return imageView;
    }
}

