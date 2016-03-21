package com.mss.imageloader.app;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 自定义Application类
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/18下午2:14
 */
public class MssApplication extends Application
{

    @Override
    public void onCreate ()
    {

        super.onCreate();
        initUniversalImageLoader();
    }

    /**
     * 初始化Universal框架,默认配置参数
     */
    private void initUniversalImageLoader ()
    {
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
    }

}
