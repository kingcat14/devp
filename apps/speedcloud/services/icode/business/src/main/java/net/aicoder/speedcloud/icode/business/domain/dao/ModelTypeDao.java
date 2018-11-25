package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.ModelType;
import org.springframework.stereotype.Repository;


/**
 * 模型类型的数据库操作
 * @author icode
 */
@Repository("modelTypeDao")
public interface ModelTypeDao extends BaseDao<ModelType, String>{


}
