package net.aicoder.speedcloud.business.deployscheme.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceProperty;
import org.springframework.stereotype.Repository;


/**
 * 资源属性的数据库操作
 * @author icode
 */
@Repository("resourcePropertyDao")
public interface ResourcePropertyDao extends BaseDao<ResourceProperty, Long>{


}
