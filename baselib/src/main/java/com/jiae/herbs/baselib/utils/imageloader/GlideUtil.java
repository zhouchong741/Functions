package com.jiae.herbs.baselib.utils.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.IllegalFormatConversionException;

/**
 * 标题：
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/15 13:15.
 */

class GlideUtil implements ILoader {

    private LoaderConfig mConfig = null;

    @Override
    public void setLoaderConfig(LoaderConfig config) {
        mConfig = config;
    }

    @Override
    public void display(Context context, String url, ImageView target, LoaderConfig config) {
        DrawableTypeRequest<String> request = getRequestManager(context)
                .load(url);
        setupConfig(request, config, target);
    }

    @Override
    public void display(Fragment fragment, String url, ImageView target, LoaderConfig config) {
        DrawableTypeRequest<String> request = getRequestManager(fragment)
                .load(url);
        setupConfig(request, config, target);
    }

    @Override
    public void display(android.app.Fragment fragment, String url, ImageView target, LoaderConfig config) {
        DrawableTypeRequest<String> request = getRequestManager(fragment)
                .load(url);
        setupConfig(request, config, target);
    }

    @Override
    public void display(Context context, String url, ImageView target) {
        display(context, url, target, mConfig);
    }

    @Override
    public void display(Fragment fragment, String url, ImageView target) {
        display(fragment, url, target, mConfig);
    }

    @Override
    public void display(android.app.Fragment fragment, String url, ImageView target) {
        display(fragment, url, target, mConfig);
    }

    private void setupConfig(DrawableTypeRequest<String> request, LoaderConfig config, ImageView target) {
        if (config == null)
            config = mConfig;
        if (config == null) {
            request.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .listener(getListener(target))
                    .into(target);
        } else {
            if (config.circleImage) {
                request.asBitmap()
                        .placeholder(config.defaultImageResId)
                        .error(config.errorImageResId)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .listener(getBitmapListener(target))
                        .into(target);
            } else {
                request.placeholder(config.defaultImageResId)
                        .error(config.errorImageResId)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .listener(getListener(target))
                        .into(target);
            }
        }
    }

    @Override
    public void pause(Object target) {
        getRequestManager(target).pauseRequests();
    }

    @Override
    public void resume(Object target) {
        getRequestManager(target).resumeRequests();
    }

    @Override
    public void stop(Object target) {
        getRequestManager(target).onStop();
    }

    @Override
    public void start(Object target) {
        getRequestManager(target).onStart();
    }

    @Override
    public void destroy(Object target) {
        getRequestManager(target).onDestroy();
    }

    private static RequestListener<? super String, GlideDrawable> getListener(final ImageView target) {
        return new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                // 如果加载失败，进行失败处理
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                // 图片资源下载成功，可以在这里获取到图片的参数
                return false;
            }
        };
    }

    private RequestListener<? super String, Bitmap> getBitmapListener(ImageView target) {
        return new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                // 如果加载失败，进行失败处理
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                // 图片资源下载成功，可以在这里获取到图片的参数
                return false;
            }
        };
    }

    private static RequestManager getRequestManager(Object target) {
        if (target instanceof Context) {
            return Glide.with((Context) target);
        } else if (target instanceof Fragment) {
            return Glide.with((Fragment) target);
        } else if (target instanceof android.app.Fragment) {
            return Glide.with((android.app.Fragment) target);
        } else {
            throw new IllegalFormatConversionException('e', target.getClass());
        }
    }

}
