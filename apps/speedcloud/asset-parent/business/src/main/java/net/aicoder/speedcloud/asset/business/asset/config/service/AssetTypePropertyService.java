package net.aicoder.speedcloud.asset.business.asset.config.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.asset.business.asset.config.dao.AssetTypePropertyDao;
import net.aicoder.speedcloud.asset.business.asset.config.dao.AssetTypePropertySpecification;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetTypeProperty;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("assetTypePropertyService")
public class AssetTypePropertyService  extends GenericCrudService<AssetTypeProperty, Long, AssetTypePropertyCondition, AssetTypePropertyDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetTypePropertyService.class);

	@Override
	public Specification<AssetTypeProperty> getSpecification(AssetTypePropertyCondition condition) {
		return new AssetTypePropertySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AssetTypeProperty.PROPERTY_ASSET_TYPE);
		return sort;
	}
}