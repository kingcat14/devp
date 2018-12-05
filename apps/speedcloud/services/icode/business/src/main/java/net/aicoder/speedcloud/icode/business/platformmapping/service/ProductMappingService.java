package net.aicoder.speedcloud.icode.business.platformmapping.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.platformmapping.dao.ProductMappingDao;
import net.aicoder.speedcloud.icode.business.platformmapping.dao.ProductMappingSpecification;
import net.aicoder.speedcloud.icode.business.platformmapping.domain.ProductMapping;
import net.aicoder.speedcloud.icode.business.platformmapping.dto.ProductMappingCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("productMappingService")
@Slf4j
public class ProductMappingService  extends GenericCrudService<ProductMapping, String, ProductMappingCondition, ProductMappingDao> {

	public ProductMapping findByPlatformProductId(String platformProductId){
		return dao.findByPlatformProductId(platformProductId);
	}

	@Override
	public Specification<ProductMapping> getSpecification(ProductMappingCondition condition) {
		return new ProductMappingSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, ProductMapping.PROPERTY_MODIFY_AT);

		return sort;
	}
}