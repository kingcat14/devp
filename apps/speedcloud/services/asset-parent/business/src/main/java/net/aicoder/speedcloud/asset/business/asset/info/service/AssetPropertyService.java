package net.aicoder.speedcloud.asset.business.asset.info.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.asset.business.asset.info.dao.AssetPropertyDao;
import net.aicoder.speedcloud.asset.business.asset.info.dao.AssetPropertySpecification;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetProperty;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("assetPropertyService")
public class AssetPropertyService  extends GenericCrudService<AssetProperty, String, AssetPropertyCondition, AssetPropertyDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetPropertyService.class);

	@Override
	public Specification<AssetProperty> getSpecification(AssetPropertyCondition condition) {
		return new AssetPropertySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AssetProperty.PROPERTY_NAME);
		return sort;
	}
}