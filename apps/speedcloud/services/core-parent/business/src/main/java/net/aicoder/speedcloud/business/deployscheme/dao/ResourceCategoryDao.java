package net.aicoder.speedcloud.business.deployscheme.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceCategory;
import org.springframework.stereotype.Repository;


/**
 * 部署资源类别的数据库操作
 * @author icode
 */
@Repository("resourceCategoryDao")
public interface ResourceCategoryDao extends BaseDao<ResourceCategory, String>{

    ResourceCategory findByCode(String code);

}
