package net.aicoder.devp.maintenance.business.asset.info.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.maintenance.business.asset.info.domain.AssetCategory;
import org.springframework.stereotype.Repository;



/**
 * 资产大类的数据库操作
 * @author icode
 */
@Repository("assetCategoryDao")
public interface AssetCategoryDao extends BaseDao<AssetCategory, Long>{


}
