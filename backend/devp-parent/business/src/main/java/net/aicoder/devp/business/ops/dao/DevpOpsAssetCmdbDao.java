package net.aicoder.devp.business.ops.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.ops.domain.DevpOpsAssetCmdb;
import org.springframework.stereotype.Repository;



/**
 * 资产定义的数据库操作
 * @author icode
 */
@Repository("devpOpsAssetCmdbDao")
public interface DevpOpsAssetCmdbDao extends BaseDao<DevpOpsAssetCmdb, Long>{


}
