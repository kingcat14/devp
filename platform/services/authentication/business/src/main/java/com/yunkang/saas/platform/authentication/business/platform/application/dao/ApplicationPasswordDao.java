package com.yunkang.saas.platform.authentication.business.platform.application.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.authentication.business.platform.application.domain.ApplicationPassword;
import org.springframework.stereotype.Repository;


/**
 * 应用密码的数据库操作
 * @author icode
 */
@Repository("applicationPasswordDao")
public interface ApplicationPasswordDao extends BaseDao<ApplicationPassword, Long>{


}
