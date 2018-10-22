package net.aicoder.speedcloud.asset.business.asset.config.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.asset.business.asset.config.dao.AssetCategoryDao;
import net.aicoder.speedcloud.asset.business.asset.config.dao.AssetCategorySpecification;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetCategory;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("assetCategoryService")
public class AssetCategoryService  extends GenericCrudService<AssetCategory, Long, AssetCategoryCondition, AssetCategoryDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetCategoryService.class);

	@Override
	public Specification<AssetCategory> getSpecification(AssetCategoryCondition condition) {
		return new AssetCategorySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AssetCategory.PROPERTY_NUM);
		return sort;
	}
}