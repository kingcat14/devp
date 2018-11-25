package net.aicoder.speedcloud.icode.business.project.dao;

import net.aicoder.speedcloud.icode.business.project.domain.Product;
import net.aicoder.speedcloud.icode.business.project.dto.ProductCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product>{

	private ProductCondition condition;

	public ProductSpecification(ProductCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddProductNamePredicate(predicateList, root, cb);
		tryAddProductCodePredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddDisabledPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<Product> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(Product.PROPERTY_TID).as(Long.class), condition.getTid()));
        }
	}
	private void tryAddProductNamePredicate(List<Predicate> predicateList, Root<Product> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProductName())){
			predicateList.add(cb.like(root.get(Product.PROPERTY_PRODUCT_NAME).as(String.class), "%"+condition.getProductName()+"%"));
		}
	}
	private void tryAddProductCodePredicate(List<Predicate> predicateList, Root<Product> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProductCode())){
			predicateList.add(cb.like(root.get(Product.PROPERTY_PRODUCT_CODE).as(String.class), "%"+condition.getProductCode()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<Product> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(Product.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddDisabledPredicate(List<Predicate> predicateList, Root<Product> root, CriteriaBuilder cb){
		if (null != condition.getDisabled() ) {
			predicateList.add(cb.equal(root.get(Product.PROPERTY_DISABLED).as(Boolean.class), condition.getDisabled()));
		}
	}
}


