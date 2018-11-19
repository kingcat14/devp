package net.aicoder.speedcloud.asset.business.asset.info.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.asset.business.asset.info.dao.AssetOwnershipDao;
import net.aicoder.speedcloud.asset.business.asset.info.dao.AssetOwnershipSpecification;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetOwnership;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("assetOwnershipService")
public class AssetOwnershipService  extends GenericCrudService<AssetOwnership, Long, AssetOwnershipCondition, AssetOwnershipDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetOwnershipService.class);

	@Override
	public Specification<AssetOwnership> getSpecification(AssetOwnershipCondition condition) {
		return new AssetOwnershipSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AssetOwnership.PROPERTY_NAME);
		return sort;
	}
}