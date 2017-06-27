package com.jiae.herbs.baselib.utils.imageloader;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;
import com.jiae.herbs.baselib.utils.cache.FileUtil;

import java.io.File;

/**
 * 标题：自定义Glide配置类
 * 作者：kisen
 * 版本：v1.0.0
 * 创建时间：on 2017/5/15 13:30.
 */
public class HGlideModule implements GlideModule {

    private static final int sizeInBytes = 100 * 1024 * 1024;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        File cacheLocation = FileUtil.getInstance().getCacheImageDir();
        cacheLocation.mkdirs();
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, dir, DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE));
        builder.setDiskCache(new DiskLruCacheFactory(FileUtil.getInstance().getCacheImageDir().getPath(), DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE));

        builder.setMemoryCache(new LruResourceCache(sizeInBytes / 2));
        builder.setBitmapPool(new LruBitmapPool(sizeInBytes));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
