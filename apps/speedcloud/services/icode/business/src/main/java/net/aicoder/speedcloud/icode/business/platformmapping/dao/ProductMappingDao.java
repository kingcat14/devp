package net.aicoder.speedcloud.icode.business.platformmapping.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.platformmapping.domain.ProductMapping;
import org.springframework.stereotype.Repository;


/**
 * 产品映射的数据库操作
 * @author icode
 */
@Repository("productMappingDao")
public interface ProductMappingDao extends BaseDao<ProductMapping, String>{

    ProductMapping findByPlatformProductId(String platformProductId);

}
