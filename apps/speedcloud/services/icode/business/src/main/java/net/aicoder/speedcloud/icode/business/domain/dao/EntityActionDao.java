package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityAction;
import org.springframework.stereotype.Repository;


/**
 * 领域对象行为的数据库操作
 * @author icode
 */
@Repository("entityActionDao")
public interface EntityActionDao extends BaseDao<EntityAction, String>{


}
