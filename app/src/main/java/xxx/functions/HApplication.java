package xxx.functions;

import com.jiae.herbs.baselib.BaseApplication;
import com.jiae.herbs.baselib.db.DbDaoProvider;
import com.jiae.herbs.baselib.utils.LogUtil;
import com.jiae.herbs.baselib.utils.http.HttpClient;
import com.jiae.herbs.baselib.utils.imageloader.ImageLoader;
import com.jiae.herbs.baselib.utils.imageloader.LoaderConfig;

import xxx.functions.db.HDbDaoProvider;
import xxx.functions.manager.AppConfig;

/**
 * 自定义Application类
 * Created by kisen on 2017/5/15.
 */
public class HApplication extends BaseApplication {

    private AppConfig appConfig;

    public static HApplication getInstance() {
        return (HApplication) application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.setDebug(BuildConfig.DEBUG);
        HttpClient.init(this);
        setupImageLoader();
        setAppConfig();

    }

    private void setupImageLoader() {
        LoaderConfig config = new LoaderConfig();
        config.defaultImageResId = R.mipmap.ic_launcher;
        ImageLoader.getInstance().setLoaderConfig(config);
    }


    @Override
    protected DbDaoProvider setupDbDaoProvider() {
        return new HDbDaoProvider();
    }

    private void setAppConfig() {
        appConfig = new AppConfig((HApplication) application);
    }
}
