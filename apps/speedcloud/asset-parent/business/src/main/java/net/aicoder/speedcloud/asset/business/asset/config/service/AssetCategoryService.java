package net.aicoder.speedcloud.asset.business.asset.config.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.asset.business.asset.config.dao.AssetCategoryDao;
import net.aicoder.speedcloud.asset.business.asset.config.dao.AssetCategorySpecification;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetCategory;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetType;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("assetCategoryService")
public class AssetCategoryService  extends GenericCrudService<AssetCategory, Long, AssetCategoryCondition, AssetCategoryDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetCategoryService.class);

	@Autowired
	private AssetTypeService assetTypeService;

	@Transactional
	public void add(AssetCategory t) {
		t.fillId();
		this.dao.save(t);
		AssetType assetType = new AssetType();
		BeanUtils.copyProperties(t, assetType);
		assetType.setParentCode(assetType.getCode());

		//ID保持一致
		assetTypeService.merge(assetType);
	}

	@Override
	public Specification<AssetCategory> getSpecification(AssetCategoryCondition condition) {
		return new AssetCategorySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AssetCategory.PROPERTY_NUM);
		return sort;
	}
}