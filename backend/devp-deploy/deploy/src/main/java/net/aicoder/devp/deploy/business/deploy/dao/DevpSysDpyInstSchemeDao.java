package net.aicoder.devp.deploy.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyInstScheme;
import org.springframework.stereotype.Repository;



/**
 * 资源实例所属的部署方案的数据库操作
 * @author icode
 */
@Repository("devpSysDpyInstSchemeDao")
public interface DevpSysDpyInstSchemeDao extends BaseDao<DevpSysDpyInstScheme, Long>{


}
