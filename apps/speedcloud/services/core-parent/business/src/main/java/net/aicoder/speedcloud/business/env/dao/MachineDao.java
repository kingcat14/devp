package net.aicoder.speedcloud.business.env.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.env.domain.Machine;
import org.springframework.stereotype.Repository;


/**
 * 服务器的数据库操作
 * @author icode
 */
@Repository("machineDao")
public interface MachineDao extends BaseDao<Machine, Long>{


}
