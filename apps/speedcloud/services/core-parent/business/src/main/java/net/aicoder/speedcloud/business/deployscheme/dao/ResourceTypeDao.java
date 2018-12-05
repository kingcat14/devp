package net.aicoder.speedcloud.business.deployscheme.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceType;
import org.springframework.stereotype.Repository;


/**
 * 部署资源类型的数据库操作
 * @author icode
 */
@Repository("resourceTypeDao")
public interface ResourceTypeDao extends BaseDao<ResourceType, String>{

    ResourceType findByCode(String code);

}
