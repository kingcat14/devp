package com.yunkang.saas.platform.monitor.business.supporter.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.monitor.business.supporter.domain.Supporter;
import org.springframework.stereotype.Repository;


/**
 * 运维人员的数据库操作
 * @author icode
 */
@Repository("supporterDao")
public interface SupporterDao extends BaseDao<Supporter, String>{


}
