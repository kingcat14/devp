package com.yunkang.saas.platform.monitor.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import org.springframework.stereotype.Repository;


/**
 * 程序的数据库操作
 * @author icode
 */
@Repository("applicationDao")
public interface ApplicationDao extends BaseDao<Application, String>{


}
