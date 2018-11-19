package net.aicoder.speedcloud.business.env.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.env.domain.EnvMachine;
import org.springframework.stereotype.Repository;


/**
 * 环境设备关联的数据库操作
 * @author icode
 */
@Repository("envMachineDao")
public interface EnvMachineDao extends BaseDao<EnvMachine, Long>{


}
