package net.aicoder.speedcloud.icode.business.platformmapping.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.platformmapping.domain.ComponentMapping;
import org.springframework.stereotype.Repository;


/**
 * 组件映射的数据库操作
 * @author icode
 */
@Repository("componentMappingDao")
public interface ComponentMappingDao extends BaseDao<ComponentMapping, String>{


}
