package net.aicoder.speedcloud.business.pipeline.exec.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.domain.Exec;
import org.springframework.stereotype.Repository;


/**
 * 运行实例的数据库操作
 * @author icode
 */
@Repository("execDao")
public interface ExecDao extends BaseDao<Exec, Long>{


}
