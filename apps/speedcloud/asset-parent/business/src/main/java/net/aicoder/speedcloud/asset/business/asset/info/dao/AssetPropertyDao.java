package net.aicoder.speedcloud.asset.business.asset.info.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetProperty;
import org.springframework.stereotype.Repository;


/**
 * 资产属性的数据库操作
 * @author icode
 */
@Repository("assetPropertyDao")
public interface AssetPropertyDao extends BaseDao<AssetProperty, String>{


}
