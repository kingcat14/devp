package net.aicoder.speedcloud.asset.business.asset.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetCategory;
import org.springframework.stereotype.Repository;


/**
 * 资产大类的数据库操作
 * @author icode
 */
@Repository("assetCategoryDao")
public interface AssetCategoryDao extends BaseDao<AssetCategory, Long>{


    AssetCategory findByCode(String code);
}
