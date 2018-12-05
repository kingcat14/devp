package net.aicoder.speedcloud.icode.business.platformmapping.dao;

import net.aicoder.speedcloud.icode.business.platformmapping.domain.ProductMapping;
import net.aicoder.speedcloud.icode.business.platformmapping.dto.ProductMappingCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductMappingSpecification implements Specification<ProductMapping>{

	private ProductMappingCondition condition;

	public ProductMappingSpecification(ProductMappingCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ProductMapping> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddProductPredicate(predicateList, root, cb);
		tryAddPlatformProductNamePredicate(predicateList, root, cb);
		tryAddPlatformProductIdPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddProductPredicate(List<Predicate> predicateList, Root<ProductMapping> root, CriteriaBuilder cb){
	    if (null != condition.getProduct() ) {
            predicateList.add(cb.equal(root.get(ProductMapping.PROPERTY_PRODUCT).as(String.class), condition.getProduct()));
        }
	}
	private void tryAddPlatformProductNamePredicate(List<Predicate> predicateList, Root<ProductMapping> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPlatformProductName())){
			predicateList.add(cb.like(root.get(ProductMapping.PROPERTY_PLATFORM_PRODUCT_NAME).as(String.class), "%"+condition.getPlatformProductName()+"%"));
		}
	}
	private void tryAddPlatformProductIdPredicate(List<Predicate> predicateList, Root<ProductMapping> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPlatformProductId())){
			predicateList.add(cb.like(root.get(ProductMapping.PROPERTY_PLATFORM_PRODUCT_ID).as(String.class), "%"+condition.getPlatformProductId()+"%"));
		}
	}
}


