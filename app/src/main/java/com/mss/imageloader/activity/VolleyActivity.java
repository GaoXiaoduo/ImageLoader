package com.mss.imageloader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mss.imageloader.R;
import com.mss.imageloader.adapter.VolleyAdapter;

import java.util.ArrayList;

/**
 * Volley框架
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/14下午2:36
 */
public class VolleyActivity extends BaseActivity
{

    private VolleyAdapter adapter;

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

        return R.layout.activity_volley;
    }

    private void initView ()
    {

        gridView = (GridView) findViewById(R.id.volley_grid_view);
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

        adapter = new VolleyAdapter(this, mUrlList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int i, long l)
            {

                Intent intent = new Intent(VolleyActivity.this, VolleyImageShowActivity.class);
                intent.putExtra("imageUrl", mUrlList.get(i));
                startActivity(intent);
            }
        });
    }
}
