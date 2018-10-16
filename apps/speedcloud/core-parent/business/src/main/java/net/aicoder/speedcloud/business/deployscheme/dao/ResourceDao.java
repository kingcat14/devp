package net.aicoder.speedcloud.business.deployscheme.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.domain.Resource;
import org.springframework.stereotype.Repository;


/**
 * 方案资源的数据库操作
 * @author icode
 */
@Repository("resourceDao")
public interface ResourceDao extends BaseDao<Resource, Long>{


}
