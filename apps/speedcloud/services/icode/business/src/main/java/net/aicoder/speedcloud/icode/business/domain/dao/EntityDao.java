package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.Entity;
import org.springframework.stereotype.Repository;


/**
 * 领域对象的数据库操作
 * @author icode
 */
@Repository("entityDao")
public interface EntityDao extends BaseDao<Entity, String>{


}
