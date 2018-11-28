package net.aicoder.speedcloud.icode.business.project.dao;

import net.aicoder.speedcloud.icode.business.project.domain.ComponentType;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ComponentTypeSpecification implements Specification<ComponentType>{

	private ComponentTypeCondition condition;

	public ComponentTypeSpecification(ComponentTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ComponentType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddIdxPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCategoryPredicate(predicateList, root, cb);
		tryAddIconPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<ComponentType> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(ComponentType.PROPERTY_TID).as(Long.class), condition.getTid()));
        }  
	}
	private void tryAddIdxPredicate(List<Predicate> predicateList, Root<ComponentType> root, CriteriaBuilder cb){

		if (null != condition.getIdx() ) {
			predicateList.add(cb.equal(root.get(ComponentType.PROPERTY_IDX).as(Integer.class), condition.getIdx()));
		}

		if (null != condition.getIdxMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ComponentType.PROPERTY_IDX).as(Integer.class), condition.getIdxMax()));
		}

		if (null != condition.getIdxMin() ) {
			predicateList.add(cb.lessThan(root.get(ComponentType.PROPERTY_IDX).as(Integer.class), condition.getIdxMin()));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<ComponentType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(ComponentType.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<ComponentType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(ComponentType.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCategoryPredicate(List<Predicate> predicateList, Root<ComponentType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCategory())){
			predicateList.add(cb.like(root.get(ComponentType.PROPERTY_CATEGORY).as(String.class), "%"+condition.getCategory()+"%"));
		}
	}
	private void tryAddIconPredicate(List<Predicate> predicateList, Root<ComponentType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getIcon())){
			predicateList.add(cb.like(root.get(ComponentType.PROPERTY_ICON).as(String.class), "%"+condition.getIcon()+"%"));
		}
	}
}


