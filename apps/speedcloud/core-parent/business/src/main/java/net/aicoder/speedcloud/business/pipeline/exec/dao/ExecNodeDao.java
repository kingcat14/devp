package net.aicoder.speedcloud.business.pipeline.exec.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.domain.ExecNode;
import org.springframework.stereotype.Repository;


/**
 * 运行实例节点的数据库操作
 * @author icode
 */
@Repository("execNodeDao")
public interface ExecNodeDao extends BaseDao<ExecNode, Long>{


}
