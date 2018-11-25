package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityActionParameter;
import org.springframework.stereotype.Repository;


/**
 * 领域对象行为参数的数据库操作
 * @author icode
 */
@Repository("entityActionParameterDao")
public interface EntityActionParameterDao extends BaseDao<EntityActionParameter, String>{


}
