package com.jiae.herbs.baselib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.jiae.herbs.baselib.db.DbDaoProvider;

/**
 * 标题：baselib库Application，必须使用
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/15 13:30.
 */
public abstract class BaseApplication extends Application {

    protected static BaseApplication application;
    private DbDaoProvider provider;

    public static BaseApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
//        setupCallback();
        provider = setupDbDaoProvider();
    }

    protected abstract DbDaoProvider setupDbDaoProvider();

    public DbDaoProvider getDbDaoProvider(){
        return provider;
    }

    private void setupCallback() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

}
