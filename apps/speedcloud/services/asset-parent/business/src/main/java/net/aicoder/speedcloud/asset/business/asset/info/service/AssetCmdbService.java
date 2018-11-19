package net.aicoder.speedcloud.asset.business.asset.info.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.asset.business.asset.info.dao.AssetCmdbDao;
import net.aicoder.speedcloud.asset.business.asset.info.dao.AssetCmdbSpecification;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetCmdb;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("assetCmdbService")
public class AssetCmdbService  extends GenericCrudService<AssetCmdb, Long, AssetCmdbCondition, AssetCmdbDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetCmdbService.class);

	@Override
	public Specification<AssetCmdb> getSpecification(AssetCmdbCondition condition) {
		return new AssetCmdbSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AssetCmdb.PROPERTY_NAME);
		return sort;
	}
}