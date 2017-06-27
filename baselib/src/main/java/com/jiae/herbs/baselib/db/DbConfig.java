package com.jiae.herbs.baselib.db;

import android.text.TextUtils;

import java.util.List;

/**
 * 标题：数据库配置类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/6 10:52.
 */
public class DbConfig {

    private String dbName;
    private int dbVersion = 1;
    private List<IDbDao> dbDaoList;
    private boolean isReady;

    public DbConfig(DbDaoProvider provider) {
        isReady = false;
        if (provider == null)
            return;
        if (TextUtils.isEmpty(provider.getDbName()))
            return;
        List<IDbDao> daoList = provider.getDaoList();
        if (daoList == null || daoList.size() < 1)
            return;
        isReady = true;
        dbVersion = provider.getDbVersion();
        dbDaoList = daoList;
        dbName = provider.getDbName();
    }

    public String getDbName() {
        return dbName;
    }

    public List<IDbDao> getTable() {
        return dbDaoList;
    }

    public int getDbVersion() {
        return dbVersion;
    }

    public boolean isReady() {
        return isReady;
    }
}
