package net.aicoder.speedcloud.asset.business.asset.info.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetOwnership;
import org.springframework.stereotype.Repository;


/**
 * IT资产归属的数据库操作
 * @author icode
 */
@Repository("assetOwnershipDao")
public interface AssetOwnershipDao extends BaseDao<AssetOwnership, Long>{


}
