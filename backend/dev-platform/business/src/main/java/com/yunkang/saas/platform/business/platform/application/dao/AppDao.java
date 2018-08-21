package com.yunkang.saas.platform.business.platform.application.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.business.platform.application.domain.App;
import org.springframework.stereotype.Repository;


/**
 * 应用的数据库操作
 * @author icode
 */
@Repository("appDao")
public interface AppDao extends BaseDao<App, Long>{

    App findByCode(String code);

}
