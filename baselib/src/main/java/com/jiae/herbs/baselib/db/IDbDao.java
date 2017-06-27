package com.jiae.herbs.baselib.db;

/**
 * 标题：数据存储模型
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/6 11:33.
 */
public interface IDbDao {

    String getSql();

    int getVersion();
}
