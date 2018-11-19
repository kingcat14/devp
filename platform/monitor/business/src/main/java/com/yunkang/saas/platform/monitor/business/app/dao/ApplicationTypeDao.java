package com.yunkang.saas.platform.monitor.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationType;
import org.springframework.stereotype.Repository;


/**
 * 程序类型的数据库操作
 * @author icode
 */
@Repository("applicationTypeDao")
public interface ApplicationTypeDao extends BaseDao<ApplicationType, String>{


}
