package net.aicoder.speedcloud.icode.business.project.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentType;
import org.springframework.stereotype.Repository;


/**
 * 组件类型的数据库操作
 * @author icode
 */
@Repository("componentTypeDao")
public interface ComponentTypeDao extends BaseDao<ComponentType, String>{


}
