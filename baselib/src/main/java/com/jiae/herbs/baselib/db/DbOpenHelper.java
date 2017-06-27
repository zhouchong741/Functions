package com.jiae.herbs.baselib.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

/**
 * 标题：自定义数据库管理类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/6 10:35.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private DbConfig dbConfig;

    public DbOpenHelper(Context context, DbConfig config) {
        super(context, config.getDbName(), null, config.getDbVersion());
        dbConfig = config;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (dbConfig == null || dbConfig.getTable() == null)
            return;
        for (IDbDao dao : dbConfig.getTable()) {
            String sql = dao.getSql();
            if (TextUtils.isEmpty(sql))
                return;
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (dbConfig == null || dbConfig.getTable() == null)
            return;
        for (IDbDao dao : dbConfig.getTable()) {
            String sql = dao.getSql();
            if (TextUtils.isEmpty(sql))
                return;
            if (oldVersion < dao.getVersion()) {
                db.execSQL(sql);
            }
        }
    }
}
