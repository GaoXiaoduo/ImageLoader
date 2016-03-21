package com.mss.imageloader.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mss.imageloader.R;

import java.util.ArrayList;

/**
 * Glide适配器
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/17下午3:55
 */
public class GlideAdapter extends BaseAdapter
{

    private Context context;


    private ArrayList<String> urls;

    public GlideAdapter (Context context, ArrayList<String> urls)
    {

        this.context = context;
        this.urls = urls;
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

        Glide.with(context).load(urls.get(i))
                .placeholder(R.mipmap.gif_placholder)
                .error(R.mipmap.icon_failure)
                //.centerCrop()
                //.fitCenter()
                .into(imageView);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(450, 450));

        return imageView;
    }
}

