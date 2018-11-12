package com.yunkang.saas.platform.monitor.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.app.domain.UnknownApp;
import org.springframework.stereotype.Repository;


/**
 * 未知程序的数据库操作
 * @author icode
 */
@Repository("unknownAppDao")
public interface UnknownAppDao extends BaseDao<UnknownApp, String>{



}
