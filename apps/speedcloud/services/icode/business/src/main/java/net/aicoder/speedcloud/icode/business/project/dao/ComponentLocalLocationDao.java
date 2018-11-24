package net.aicoder.speedcloud.icode.business.project.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentLocalLocation;
import org.springframework.stereotype.Repository;


/**
 * 组件本地路径的数据库操作
 * @author icode
 */
@Repository("componentLocalLocationDao")
public interface ComponentLocalLocationDao extends BaseDao<ComponentLocalLocation, String>{


}
