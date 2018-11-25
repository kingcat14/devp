package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityProperty;
import org.springframework.stereotype.Repository;


/**
 * 领域对象属性的数据库操作
 * @author icode
 */
@Repository("entityPropertyDao")
public interface EntityPropertyDao extends BaseDao<EntityProperty, String>{


}
