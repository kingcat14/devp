package net.aicoder.devp.deploy.business.ops.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsCiGroup;
import org.springframework.stereotype.Repository;



/**
 * 资产项目分组的数据库操作
 * @author icode
 */
@Repository("devpOpsCiGroupDao")
public interface DevpOpsCiGroupDao extends BaseDao<DevpOpsCiGroup, Long>{


}
