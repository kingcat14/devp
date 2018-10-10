package net.aicoder.speedcloud.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyScheme;
import org.springframework.stereotype.Repository;


/**
 * 部署方案的数据库操作
 * @author icode
 */
@Repository("devpSysDpySchemeDao")
public interface DevpSysDpySchemeDao extends BaseDao<DevpSysDpyScheme, Long>{


}
