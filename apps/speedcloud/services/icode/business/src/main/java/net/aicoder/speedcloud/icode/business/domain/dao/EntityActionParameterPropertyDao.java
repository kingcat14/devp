package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityActionParameterProperty;
import org.springframework.stereotype.Repository;


/**
 * 领域对象行为参数属性的数据库操作
 * @author icode
 */
@Repository("entityActionParameterPropertyDao")
public interface EntityActionParameterPropertyDao extends BaseDao<EntityActionParameterProperty, String>{


}
