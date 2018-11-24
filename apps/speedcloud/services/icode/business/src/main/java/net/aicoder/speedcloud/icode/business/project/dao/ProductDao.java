package net.aicoder.speedcloud.icode.business.project.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.project.domain.Product;
import org.springframework.stereotype.Repository;


/**
 * 产品的数据库操作
 * @author icode
 */
@Repository("productDao")
public interface ProductDao extends BaseDao<Product, String>{


}
