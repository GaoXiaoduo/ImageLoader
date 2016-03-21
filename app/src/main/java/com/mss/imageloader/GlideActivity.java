package com.mss.imageloader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mss.imageloader.adapter.GlideAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Glide框架
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/14下午2:36
 */
public class GlideActivity extends BaseActivity
{
    private GlideAdapter adapter;

    private GridView gridView;

    private ArrayList<String> mUrlList = new ArrayList<>();

    private final String baseUrl = "http://www.jycoder.com/json/Image/";

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        initView();
        initUrl();
        initAdapter();
        //setUriImage();
    }

    @Override
    protected int contentView ()
    {

        return R.layout.activity_glide;
    }

    private void initView ()
    {

        gridView = (GridView) findViewById(R.id.grid_view);
    }

    private void initUrl ()
    {

        for (int i = 1; i <= 18; i++)
        {
            mUrlList.add(baseUrl + i + ".jpg");
        }

    }

    private void initAdapter ()
    {

        adapter = new GlideAdapter(this, mUrlList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int i, long l)
            {

                Intent intent = new Intent(GlideActivity.this, GlideImageShowActivity.class);
                intent.putExtra("imageUrl", mUrlList.get(i));
                startActivity(intent);
            }
        });
    }

    private void setUriImage ()
    {

        Uri imageUri = Uri.parse("http://pooyak.com/p/progjpeg/jpegload.cgi?o=3");
        Picasso.with(this).load(imageUri)
                .placeholder(R.mipmap.icon_placeholder)
                .error(R.mipmap.icon_failure);
        //.into(mImgView);

    }
}
