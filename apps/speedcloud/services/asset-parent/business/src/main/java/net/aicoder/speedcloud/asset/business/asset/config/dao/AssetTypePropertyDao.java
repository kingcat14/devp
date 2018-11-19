package net.aicoder.speedcloud.asset.business.asset.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetTypeProperty;
import org.springframework.stereotype.Repository;


/**
 * 资产分类属性的数据库操作
 * @author icode
 */
@Repository("assetTypePropertyDao")
public interface AssetTypePropertyDao extends BaseDao<AssetTypeProperty, Long>{


}
