package net.aicoder.maintenance.business.asset.info.dao;

import com.yunkang.saas.common.jpa.BaseDao;

import net.aicoder.maintenance.business.asset.info.domain.AssetType;

import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 资产分类的数据库操作
 * @author icode
 */
@Repository("assetTypeDao")
public interface AssetTypeDao extends BaseDao<AssetType, Long>{

    AssetType findByCodeAndTid(String code, Long tid);
    List<AssetType> findByParentCodeAndTid(String parentCode, Long tid);

    int countAllByParentCodeAndTid(String parentCode, Long tid);
}
