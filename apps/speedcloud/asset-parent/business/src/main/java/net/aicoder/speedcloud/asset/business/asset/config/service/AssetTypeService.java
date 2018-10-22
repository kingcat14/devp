package net.aicoder.speedcloud.asset.business.asset.config.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.asset.business.asset.config.dao.AssetTypeDao;
import net.aicoder.speedcloud.asset.business.asset.config.dao.AssetTypeSpecification;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetType;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("assetTypeService")
public class AssetTypeService  extends GenericCrudService<AssetType, Long, AssetTypeCondition, AssetTypeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetTypeService.class);

	@Override
	public Specification<AssetType> getSpecification(AssetTypeCondition condition) {
		return new AssetTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AssetType.PROPERTY_NUM);
		return sort;
	}
}