package net.aicoder.speedcloud.icode.business.domain.dao;

import net.aicoder.speedcloud.icode.business.domain.domain.PropertyType;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PropertyTypeSpecification implements Specification<PropertyType>{

	private PropertyTypeCondition condition;

	public PropertyTypeSpecification(PropertyTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PropertyType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddViewIndexPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
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
	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<PropertyType> root, CriteriaBuilder cb){

		if (null != condition.getViewIndex() ) {
			predicateList.add(cb.equal(root.get(PropertyType.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndex()));
		}

		if (null != condition.getViewIndexMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PropertyType.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndexMax()));
		}

		if (null != condition.getViewIndexMin() ) {
			predicateList.add(cb.lessThan(root.get(PropertyType.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndexMin()));
		}
	}
}


