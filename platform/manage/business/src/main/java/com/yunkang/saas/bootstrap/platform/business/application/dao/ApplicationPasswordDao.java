package com.yunkang.saas.bootstrap.platform.business.application.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.bootstrap.platform.business.application.domain.ApplicationPassword;
import org.springframework.stereotype.Repository;


/**
 * 应用密码的数据库操作
 * @author icode
 */
@Repository("applicationPasswordDao")
public interface ApplicationPasswordDao extends BaseDao<ApplicationPassword, Long>{


}
