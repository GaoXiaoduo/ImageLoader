package com.mss.imageloader.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.mss.imageloader.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Picasso适配器
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/17下午3:55
 */
public class PicassoAdapter extends BaseAdapter
{

    private Context context;


    private ArrayList<String> urls;

    public PicassoAdapter (Context context, ArrayList<String> urls)
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

        Picasso.with(context).load(urls.get(i)).placeholder(R.mipmap.icon_placeholder)
                .error(R.mipmap.icon_failure).into(imageView);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(450, 450));

        return imageView;
    }
}

