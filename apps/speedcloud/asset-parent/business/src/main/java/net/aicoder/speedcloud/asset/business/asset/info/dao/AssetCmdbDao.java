package net.aicoder.speedcloud.asset.business.asset.info.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetCmdb;
import org.springframework.stereotype.Repository;


/**
 * IT资产的数据库操作
 * @author icode
 */
@Repository("assetCmdbDao")
public interface AssetCmdbDao extends BaseDao<AssetCmdb, Long>{


}
