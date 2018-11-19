package net.aicoder.speedcloud.asset.business.asset.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetType;
import org.springframework.stereotype.Repository;


/**
 * 资产分类的数据库操作
 * @author icode
 */
@Repository("assetTypeDao")
public interface AssetTypeDao extends BaseDao<AssetType, Long>{

    AssetType findByAssetCategoryCodeAndCode(String categoryCode, String typeCode);
}
