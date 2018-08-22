package net.aicoder.devp.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyScheme;
import org.springframework.stereotype.Repository;



/**
 * 产品部署方案的数据库操作
 * @author icode
 */
@Repository("devpSysDpySchemeDao")
public interface DevpSysDpySchemeDao extends BaseDao<DevpSysDpyScheme, Long>{


}
