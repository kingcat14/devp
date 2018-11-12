package com.yunkang.saas.platform.monitor.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import org.springframework.stereotype.Repository;


/**
 * 程序实例的数据库操作
 * @author icode
 */
@Repository("applicationInstanceDao")
public interface ApplicationInstanceDao extends BaseDao<ApplicationInstance, String>{


}
