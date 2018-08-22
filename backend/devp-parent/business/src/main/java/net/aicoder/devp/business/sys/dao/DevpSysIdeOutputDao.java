package net.aicoder.devp.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.sys.domain.DevpSysIdeOutput;
import org.springframework.stereotype.Repository;



/**
 * 开发工程产出组件的数据库操作
 * @author icode
 */
@Repository("devpSysIdeOutputDao")
public interface DevpSysIdeOutputDao extends BaseDao<DevpSysIdeOutput, Long>{


}
