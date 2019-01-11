package com.yunkang.saas.application.business.application.dao;

import com.yunkang.saas.application.business.application.domain.App;
import com.yunkang.saas.common.jpa.BaseDao;
import org.springframework.stereotype.Repository;



/**
 * 应用的数据库操作
 * @author icode
 */
@Repository("appDao")
public interface AppDao extends BaseDao<App, Long>{


}
