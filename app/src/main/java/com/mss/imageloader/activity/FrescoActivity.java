package com.mss.imageloader.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mss.imageloader.R;
import com.mss.imageloader.adapter.FrescoAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Fresco框架
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/14下午2:36
 */
public class FrescoActivity extends BaseActivity
{

    private FrescoAdapter adapter;

    private GridView gridView;

    private ArrayList<String> mUrlList = new ArrayList<>();

    private final String baseUrl = "http://www.jycoder.com/json/Image/";

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        Fresco.initialize(this);//初始化框架,需在布局调用前初始化
        initView();
        initUrl();
        initAdapter();
        //setUriImage();
    }

    @Override
    protected int contentView ()
    {

        return R.layout.activity_fresco;
    }

    private void initView ()
    {

        gridView = (GridView) findViewById(R.id.fresco_grid_view);
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

        adapter = new FrescoAdapter(this, mUrlList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int i, long l)
            {

                Intent intent = new Intent(FrescoActivity.this, FrescoImageShowActivity.class);
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
