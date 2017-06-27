package com.jiae.herbs.baselib.db;

import com.jiae.herbs.baselib.BaseApplication;

/**
 * 标题：数据库管理类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/6 11:03.
 */
public class DbManager {

    private static DbManager instance;
    private DbOpenHelper dbOpenHelper;
    private DbConfig dbConfig;

    public static DbManager getInstance() {
        if (instance == null) {
            synchronized (DbManager.class) {
                if (instance == null)
                    return new DbManager();
            }
        }
        return instance;
    }

    private DbManager() {
        config();
        if (dbConfig.isReady())
            dbOpenHelper = new DbOpenHelper(BaseApplication.getInstance(), dbConfig);
    }

    private void config() {
        dbConfig = new DbConfig(BaseApplication.getInstance().getDbDaoProvider());
    }

    public DbOpenHelper getDbOpenHelper() {
        return dbOpenHelper;
    }
}
