package xxx.functions.db;

import com.jiae.herbs.baselib.db.DbDaoProvider;
import com.jiae.herbs.baselib.db.IDbDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 标题：数据库Dao提供者
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/6 11:45.
 */
public class HDbDaoProvider implements DbDaoProvider {

    private static final String DB_NAME = "gycs_db";
    private static final int DB_VERSION = 2;
    private List<IDbDao> list;

    public HDbDaoProvider() {
        list = new ArrayList<>();
    }

    @Override
    public String getDbName() {
        return DB_NAME;
    }

    @Override
    public List<IDbDao> getDaoList() {
        return list;
    }

    @Override
    public int getDbVersion() {
        return DB_VERSION;
    }
}
