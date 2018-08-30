package com.kingzoo.kingcat.project.icode4.business.system.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Product;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ProductCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Product的数据库操作
 * @author icode
 */
@Repository("productDao")
public class ProductDao extends SimpleJpaRepository<Product, String, ProductCondition> {

	@Override
	public Specification<Product> buildQuery(final ProductCondition condition){

		return new ProductSpecification(condition);
	}
}

class ProductSpecification implements Specification<Product>{

	ProductCondition condition;

	public ProductSpecification(ProductCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddProductNamePredicate(predicateList, root, cb);
		tryAddProductCodePredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
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
}

