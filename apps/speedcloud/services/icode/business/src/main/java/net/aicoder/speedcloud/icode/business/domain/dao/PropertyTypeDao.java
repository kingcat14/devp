package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.PropertyType;
import org.springframework.stereotype.Repository;


/**
 * 属性类型的数据库操作
 * @author icode
 */
@Repository("propertyTypeDao")
public interface PropertyTypeDao extends BaseDao<PropertyType, String>{


}
