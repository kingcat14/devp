package net.aicoder.speedcloud.icode.business.project.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentDomainRelation;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 组件-领域-关系的数据库操作
 * @author icode
 */
@Repository("componentDomainRelationDao")
public interface ComponentDomainRelationDao extends BaseDao<ComponentDomainRelation, String>{


    List<ComponentDomainRelation> findByComponent(String component);
}
