package com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils;

import android.database.sqlite.SQLiteDatabase;

import com.avicsafety.NewShenYangTowerComService.PowerManager.push.PositionBean;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig positionBeanDaoConfig;

    private final PositionBeanDao positionBeanDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        positionBeanDaoConfig = daoConfigMap.get(PositionBeanDao.class).clone();
        positionBeanDaoConfig.initIdentityScope(type);

        positionBeanDao = new PositionBeanDao(positionBeanDaoConfig, this);

        registerDao(PositionBean.class, positionBeanDao);
    }
    
    public void clear() {
        positionBeanDaoConfig.getIdentityScope().clear();
    }

    public PositionBeanDao getPositionBeanDao() {
        return positionBeanDao;
    }

}
