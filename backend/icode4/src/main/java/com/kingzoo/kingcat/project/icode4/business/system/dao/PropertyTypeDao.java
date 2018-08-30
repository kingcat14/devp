package com.kingzoo.kingcat.project.icode4.business.system.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.system.domain.PropertyType;
import com.kingzoo.kingcat.project.icode4.business.system.vo.PropertyTypeCondition;
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
 * 属性类型的数据库操作
 * @author icode
 */
@Repository("propertyTypeDao")
public class PropertyTypeDao extends SimpleJpaRepository<PropertyType, String, PropertyTypeCondition> {

	@Override
	public Specification<PropertyType> buildQuery(final PropertyTypeCondition condition){

		return new PropertyTypeSpecification(condition);
	}
}

class PropertyTypeSpecification implements Specification<PropertyType>{

	PropertyTypeCondition condition;

	public PropertyTypeSpecification(PropertyTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PropertyType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddViewIndexPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<PropertyType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getViewIndex())){
			predicateList.add(cb.like(root.get(PropertyType.PROPERTY_VIEW_INDEX).as(String.class), "%"+condition.getViewIndex()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<PropertyType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(PropertyType.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PropertyType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PropertyType.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
}

