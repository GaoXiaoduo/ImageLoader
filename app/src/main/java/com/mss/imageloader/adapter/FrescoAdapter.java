package com.mss.imageloader.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;

/**
 * Frsco适配器
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/17下午3:55
 */
public class FrescoAdapter extends BaseAdapter
{

    private Context context;


    private ArrayList<String> urls;

    public FrescoAdapter (Context context, ArrayList<String> urls)
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

        SimpleDraweeView imageView = new SimpleDraweeView(context);

        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(450, 450));

        setImagePipeline(urls.get(i), imageView);
        return imageView;
    }

    /**
     * 使用渐进式显示图片
     */
    private void setImagePipeline (String url, SimpleDraweeView imageView)
    {

        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url))
                .setProgressiveRenderingEnabled(true)
                .build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco
                .newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(imageView.getController())
                .build();
        imageView.setController(controller);
    }

}

