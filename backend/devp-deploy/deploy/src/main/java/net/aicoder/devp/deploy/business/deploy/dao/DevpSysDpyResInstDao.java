package net.aicoder.devp.deploy.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyResInst;
import org.springframework.stereotype.Repository;



/**
 * 部署关联资源实例定义的数据库操作
 * @author icode
 */
@Repository("devpSysDpyResInstDao")
public interface DevpSysDpyResInstDao extends BaseDao<DevpSysDpyResInst, Long>{


}
