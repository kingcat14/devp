package net.aicoder.devp.business.ops.dao;

import com.yunkang.saas.common.jpa.BaseDao;

import net.aicoder.devp.business.ops.domain.DevpOpsRequirement;

import org.springframework.stereotype.Repository;



/**
 * 需求定义的数据库操作
 * @author icode
 */
@Repository("devpOpsRequirementDao")
public interface DevpOpsRequirementDao extends BaseDao<DevpOpsRequirement, Long>{


}
