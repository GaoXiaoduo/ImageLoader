package com.mss.imageloader.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.mss.imageloader.R;
import com.mss.imageloader.view.BitmapCache;

import java.util.ArrayList;

/**
 * Volley适配器
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/17下午3:55
 */
public class VolleyAdapter extends BaseAdapter
{

    private Context context;


    private ArrayList<String> urls;

    private ImageLoader mImageLoader;

    public VolleyAdapter (Context context, ArrayList<String> urls)
    {

        this.context = context;
        this.urls = urls;
        RequestQueue mQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mQueue, new BitmapCache());
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
    public View getView (int position, View view, ViewGroup viewGroup)
    {

        ImageView imageView = new ImageView(context);

        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(450, 450));

        // 通过volley的ImageLoader来获取网络图片

        ImageLoader.ImageListener listener = ImageLoader
                .getImageListener(imageView, R.mipmap.icon_placeholder,
                        R.mipmap.icon_failure);

        mImageLoader.get(urls.get(position), listener);

        return imageView;
    }
}

