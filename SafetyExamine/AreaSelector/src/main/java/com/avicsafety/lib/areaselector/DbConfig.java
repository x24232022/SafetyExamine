package com.avicsafety.lib.areaselector;

import org.xutils.DbManager;

import java.io.File;

/**
 * Created by Administrator on 2017/3/28.
 */

public class DbConfig {
    public static DbManager.DaoConfig getDbConfig(String db_Name, String db_Path){
        DbManager.DaoConfig dConfig = new DbManager.DaoConfig()
                .setDbName(db_Name)
                .setDbDir(new File(db_Path))
                .setDbVersion(3)
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {}
                });
        return dConfig;
    }
}
