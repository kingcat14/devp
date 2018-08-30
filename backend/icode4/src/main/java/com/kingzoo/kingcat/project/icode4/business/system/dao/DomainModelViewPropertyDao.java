package com.kingzoo.kingcat.project.icode4.business.system.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelViewProperty;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelViewPropertyCondition;
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
 * 属性展现配置的数据库操作
 * @author icode
 */
@Repository("domainModelViewPropertyDao")
public class DomainModelViewPropertyDao extends SimpleJpaRepository<DomainModelViewProperty, String, DomainModelViewPropertyCondition> {

	@Override
	public Specification<DomainModelViewProperty> buildQuery(final DomainModelViewPropertyCondition condition){

		return new DomainModelViewPropertySpecification(condition);
	}
}

class DomainModelViewPropertySpecification implements Specification<DomainModelViewProperty>{

	DomainModelViewPropertyCondition condition;

	public DomainModelViewPropertySpecification(DomainModelViewPropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DomainModelViewProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddIdPredicate(predicateList, root, cb);
		tryAddViewIndexPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddAddViewablePredicate(predicateList, root, cb);
		tryAddAddablePredicate(predicateList, root, cb);
		tryAddAddRequiredPredicate(predicateList, root, cb);
		tryAddEditViewablePredicate(predicateList, root, cb);
		tryAddEditablePredicate(predicateList, root, cb);
		tryAddEditRequiredPredicate(predicateList, root, cb);
		tryAddSearchConditionPredicate(predicateList, root, cb);
		tryAddGirdColumnPredicate(predicateList, root, cb);
		tryAddSimpleSearchConditionPredicate(predicateList, root, cb);
		tryAddDomainModelIdPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddIdPredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getId())){
			predicateList.add(cb.like(root.get(DomainModelViewProperty.PROPERTY_ID).as(String.class), "%"+condition.getId()+"%"));
		}
	}
	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getViewIndex() ) {
			predicateList.add(cb.equal(root.get(DomainModelViewProperty.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndex()));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DomainModelViewProperty.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DomainModelViewProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAddViewablePredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getAddViewable() ) {
			predicateList.add(cb.equal(root.get(DomainModelViewProperty.PROPERTY_ADD_VIEWABLE).as(Boolean.class), condition.getAddViewable()));
		}
	}
	private void tryAddAddablePredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getAddable() ) {
			predicateList.add(cb.equal(root.get(DomainModelViewProperty.PROPERTY_ADDABLE).as(Boolean.class), condition.getAddable()));
		}
	}
	private void tryAddAddRequiredPredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getAddRequired() ) {
			predicateList.add(cb.equal(root.get(DomainModelViewProperty.PROPERTY_ADD_REQUIRED).as(Boolean.class), condition.getAddRequired()));
		}
	}
	private void tryAddEditViewablePredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getEditViewable() ) {
			predicateList.add(cb.equal(root.get(DomainModelViewProperty.PROPERTY_EDIT_VIEWABLE).as(Boolean.class), condition.getEditViewable()));
		}
	}
	private void tryAddEditablePredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getEditable() ) {
			predicateList.add(cb.equal(root.get(DomainModelViewProperty.PROPERTY_EDITABLE).as(Boolean.class), condition.getEditable()));
		}
	}
	private void tryAddEditRequiredPredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getEditRequired() ) {
			predicateList.add(cb.equal(root.get(DomainModelViewProperty.PROPERTY_EDIT_REQUIRED).as(Boolean.class), condition.getEditRequired()));
		}
	}
	private void tryAddSearchConditionPredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getSearchCondition() ) {
			predicateList.add(cb.equal(root.get(DomainModelViewProperty.PROPERTY_SEARCH_CONDITION).as(Boolean.class), condition.getSearchCondition()));
		}
	}
	private void tryAddGirdColumnPredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getGirdColumn() ) {
			predicateList.add(cb.equal(root.get(DomainModelViewProperty.PROPERTY_GIRD_COLUMN).as(Boolean.class), condition.getGirdColumn()));
		}
	}
	private void tryAddSimpleSearchConditionPredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getSimpleSearchCondition() ) {
			predicateList.add(cb.equal(root.get(DomainModelViewProperty.PROPERTY_SIMPLE_SEARCH_CONDITION).as(Boolean.class), condition.getSimpleSearchCondition()));
		}
	}
	private void tryAddDomainModelIdPredicate(List<Predicate> predicateList, Root<DomainModelViewProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDomainModelId())){
			predicateList.add(cb.like(root.get(DomainModelViewProperty.PROPERTY_DOMAIN_MODEL_ID).as(String.class), "%"+condition.getDomainModelId()+"%"));
		}
	}
}

