package com.mss.imageloader.activity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.mss.imageloader.R;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Fresco框架
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 16/3/14下午2:36
 */
public class FrescoImageShowActivity extends BaseActivity
{
    private SimpleDraweeView mSDView = null;

    private TextView mTextView = null;

    Uri mImageUrl = null;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {

        Fresco.initialize(this);//初始化框架,需在布局调用前初始化
        //initFresco();
        //initOkHttpFresco();
        super.onCreate(savedInstanceState);
        initView();
        getIntentUrl();
        //initDefault();
        //setDraweeHierarchy();
        //setImagePipeline();
        setControllerListener();
    }

    @Override
    protected int contentView ()
    {

        return R.layout.activity_fresco_image_show;
    }

    private void getIntentUrl ()
    {
        //获取传递过来的imageUrl，并将image显示出来，注意设置placeholer
        Intent intent = getIntent();
        mImageUrl = Uri.parse(intent.getExtras().getString("imageUrl"));
    }

    /**
     * 初始化框架,渐进方式显示图片时使用此方法
     */
    private void initFresco ()
    {

        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .build();
        Fresco.initialize(this, imagePipelineConfig);
    }

    /**
     * 初始化框架,使用okhttpw网络请求库,渐进方式显示图片时使用此方法
     */
    private void initOkHttpFresco ()
    {

        OkHttpClient okHttpClient = new OkHttpClient();
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, okHttpClient)
                        //. // other setters
                        //. // setNetworkFetchProducer is already called for you
                .build();
        Fresco.initialize(this, config);
    }

    /**
     * 初始化对象
     */
    private void initView ()
    {
        //创建SimpleDraweeView对象
        mSDView = (SimpleDraweeView) this.findViewById(R.id.fresco_imgv);
        mTextView = (TextView) this.findViewById(R.id.fresco_tv);
    }

    /**
     * 使用布局定义属性
     */
    private void initDefault ()
    {

        //http://my.csdn.net/uploads/avatar/4/E/8/1_y1scp.jpg
        //http://pooyak.com/p/progjpeg/jpegload.cgi?o=3
        //Uri imageUri = Uri.parse();
        //创建将要下载的图片的URI
        Uri imageUri = Uri.parse("http://pooyak.com/p/progjpeg/jpegload.cgi?o=3");
        //开始下载
        mSDView.setImageURI(mImageUrl);
        //setDraweeHierarchy();
        //创建DraweeController
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //加载的图片URI地址
                .setUri(imageUri)
                        //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                        //设置旧的Controller
                .setOldController(mSDView.getController())
                        //构建
                .build();
        //设置DraweeController
        mSDView.setController(controller);
    }

    /**
     * java 代码设置属性
     */
    private void setDraweeHierarchy ()
    {
        //初始化多张背景图集合
        List<Drawable> bgs = new ArrayList<Drawable>();
        bgs.add(ContextCompat.getDrawable(this, R.mipmap.bg_zero));
        bgs.add(ContextCompat.getDrawable(this, R.mipmap.bg_one));
        bgs.add(ContextCompat.getDrawable(this, R.mipmap.bg_two));


        //初始化多张叠加图集合
        List<Drawable> overlays = new ArrayList<Drawable>();
        overlays.add(ContextCompat.getDrawable(this, R.mipmap.overlay_one));
        overlays.add(ContextCompat.getDrawable(this, R.mipmap.overlay_two));
        overlays.add(ContextCompat.getDrawable(this, R.mipmap.overlay_three));


        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                //设置淡入淡出动画持续时间
                .setFadeDuration(5000)
                        //设置单张背景图
                .setBackground(ContextCompat.getDrawable(this, R.mipmap.bg_zero))
                        //设置多张背景图
                .setBackgrounds(bgs)
                        //设置单张叠加图
                        //.setOverlay(ContextCompat.getDrawable(this,R.mipmap.overlay_one))
                        //设置多张叠加图
                        //.setOverlays(overlays)
                        //设置占位图及它的缩放类型
                .setPlaceholderImage(ContextCompat.getDrawable(this, R.mipmap.icon_placeholder),
                        ScalingUtils.ScaleType.FOCUS_CROP)
                        //设置正在加载图及其缩放类型
                .setProgressBarImage(ContextCompat.getDrawable(this, R.mipmap.icon_progress_bar),
                        ScalingUtils.ScaleType.FOCUS_CROP)
                        //设置失败图及其缩放类型
                .setFailureImage(ContextCompat.getDrawable(this, R.mipmap.icon_failure),
                        ScalingUtils.ScaleType.FOCUS_CROP)
                        //设置重试图及其缩放类型
                .setRetryImage(ContextCompat.getDrawable(this, R.mipmap.icon_retry), ScalingUtils
                        .ScaleType.FOCUS_CROP)
                        //构建
                .build();

        //设置GenericDraweeHierarchy
        mSDView.setHierarchy(hierarchy);
    }

    /**
     * 使用渐进式显示图片
     */
    private void setImagePipeline ()
    {

        //Uri imageUri = Uri.parse("http://pooyak.com/p/progjpeg/jpegload.cgi?o=3");
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(mImageUrl)
                .setProgressiveRenderingEnabled(true)
                .build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco
                .newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(mSDView.getController())
                .build();
        mSDView.setController(controller);
    }

    /**
     * 设置图片下载监听
     */
    private void setControllerListener ()
    {

        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>()
        {
            @Override
            public void onFinalImageSet (
                    String id,
                    @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim)
            {

                if (imageInfo == null)
                {
                    return;
                }
                QualityInfo qualityInfo = imageInfo.getQualityInfo();
                Toast.makeText(FrescoImageShowActivity.this, "图片下载成功", Toast.LENGTH_LONG);
                mTextView.setText("图片下载成功");
                FLog.d("Final image received! " +
                                "Size %d x %d",
                        "Quality level %d, good enough: %s, full quality: %s",
                        imageInfo.getWidth(),
                        imageInfo.getHeight(),
                        qualityInfo.getQuality(),
                        qualityInfo.isOfGoodEnoughQuality(),
                        qualityInfo.isOfFullQuality());
            }

            @Override
            public void onIntermediateImageSet (String id, @Nullable ImageInfo imageInfo)
            {
                //FLog.d("Intermediate image received");
                mTextView.setText("onIntermediateImageSet");
            }

            @Override
            public void onFailure (String id, Throwable throwable)
            {
                //FLog.e(getClass(), throwable, "Error loading %s", id)
                Toast.makeText(FrescoImageShowActivity.this, "图片下载失败", Toast.LENGTH_LONG);
                mTextView.setText("图片下载失败");
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                        //.setAutoPlayAnimations(true)
                .setUri(mImageUrl)
                .setOldController(mSDView.getController())
                .build();

        mSDView.setController(controller);
    }
}
