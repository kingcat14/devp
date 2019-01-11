package com.yunkang.saas.platform.monitor.business.supporter.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.monitor.business.supporter.domain.SupporterAppRelation;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 支持应用的数据库操作
 * @author icode
 */
@Repository("supporterAppRelationDao")
public interface SupporterAppRelationDao extends BaseDao<SupporterAppRelation, String>{

    List<SupporterAppRelation> findByApplication(String application);

}
