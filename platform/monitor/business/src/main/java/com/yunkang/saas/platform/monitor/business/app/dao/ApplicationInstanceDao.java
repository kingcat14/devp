package com.yunkang.saas.platform.monitor.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 程序实例的数据库操作
 * @author icode
 */
@Repository("applicationInstanceDao")
public interface ApplicationInstanceDao extends BaseDao<ApplicationInstance, String>{

    ApplicationInstance findByAppAndHostAndPort(String app, String host, Integer port);

    List<ApplicationInstance> findByAppAndAlive(String app, Boolean alive);

    @Modifying
    int deleteByApp(String app);
}
