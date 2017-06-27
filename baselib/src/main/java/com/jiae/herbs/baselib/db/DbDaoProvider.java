package com.jiae.herbs.baselib.db;

import java.util.List;

/**
 * 标题：数据库Dao提供者
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/6 11:37.
 */
public interface DbDaoProvider {

    String getDbName();

    List<IDbDao> getDaoList();

    int getDbVersion();

}
