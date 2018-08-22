package net.aicoder.devp.business.ops.dao;

import com.yunkang.saas.common.jpa.BaseDao;

import net.aicoder.devp.business.ops.domain.DevpOpsAssetGroup;

import org.springframework.stereotype.Repository;



/**
 * 资产分组的数据库操作
 * @author icode
 */
@Repository("devpOpsAssetGroupDao")
public interface DevpOpsAssetGroupDao extends BaseDao<DevpOpsAssetGroup, Long>{


}
