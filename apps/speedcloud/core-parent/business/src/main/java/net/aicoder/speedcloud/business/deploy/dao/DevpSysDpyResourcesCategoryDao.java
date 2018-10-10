package net.aicoder.speedcloud.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourcesCategory;
import org.springframework.stereotype.Repository;


/**
 * 部署资源类别的数据库操作
 * @author icode
 */
@Repository("devpSysDpyResourcesCategoryDao")
public interface DevpSysDpyResourcesCategoryDao extends BaseDao<DevpSysDpyResourcesCategory, Long>{


}
