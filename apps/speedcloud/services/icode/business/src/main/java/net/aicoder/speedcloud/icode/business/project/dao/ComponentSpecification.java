package net.aicoder.speedcloud.icode.business.project.dao;

import net.aicoder.speedcloud.icode.business.project.domain.Component;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ComponentSpecification implements Specification<Component>{

	private ComponentCondition condition;

	public ComponentSpecification(ComponentCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Component> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddProductPredicate(predicateList, root, cb);
		tryAddNumberPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddBasePackagePredicate(predicateList, root, cb);
		tryAddTplSetPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddRunnablePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<Component> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(Component.PROPERTY_TID).as(Long.class), condition.getTid()));
        }
	}
	private void tryAddProductPredicate(List<Predicate> predicateList, Root<Component> root, CriteriaBuilder cb){
	    if (null != condition.getProduct() ) {
            predicateList.add(cb.equal(root.get(Component.PROPERTY_PRODUCT).as(String.class), condition.getProduct()));
        }  
	}
	private void tryAddNumberPredicate(List<Predicate> predicateList, Root<Component> root, CriteriaBuilder cb){

		if (null != condition.getNumber() ) {
			predicateList.add(cb.equal(root.get(Component.PROPERTY_NUMBER).as(Integer.class), condition.getNumber()));
		}

		if (null != condition.getNumberMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Component.PROPERTY_NUMBER).as(Integer.class), condition.getNumberMax()));
		}

		if (null != condition.getNumberMin() ) {
			predicateList.add(cb.lessThan(root.get(Component.PROPERTY_NUMBER).as(Integer.class), condition.getNumberMin()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Component> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Component.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Component> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Component.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddBasePackagePredicate(List<Predicate> predicateList, Root<Component> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBasePackage())){
			predicateList.add(cb.like(root.get(Component.PROPERTY_BASE_PACKAGE).as(String.class), "%"+condition.getBasePackage()+"%"));
		}
	}
	private void tryAddTplSetPredicate(List<Predicate> predicateList, Root<Component> root, CriteriaBuilder cb){
	    if (null != condition.getTplSet() ) {
            predicateList.add(cb.equal(root.get(Component.PROPERTY_TPL_SET).as(String.class), condition.getTplSet()));
        }  
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<Component> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(Component.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<Component> root, CriteriaBuilder cb){
	    if (null != condition.getType() ) {
            predicateList.add(cb.equal(root.get(Component.PROPERTY_TYPE).as(String.class), condition.getType()));
        }  
	}
	private void tryAddRunnablePredicate(List<Predicate> predicateList, Root<Component> root, CriteriaBuilder cb){
		if (null != condition.getRunnable() ) {
			predicateList.add(cb.equal(root.get(Component.PROPERTY_RUNNABLE).as(Boolean.class), condition.getRunnable()));
		}
	}
}


