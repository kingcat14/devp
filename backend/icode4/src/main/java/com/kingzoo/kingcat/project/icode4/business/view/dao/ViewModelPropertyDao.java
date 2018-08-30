package com.kingzoo.kingcat.project.icode4.business.view.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModelProperty;
import com.kingzoo.kingcat.project.icode4.business.view.vo.ViewModelPropertyCondition;
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
 * 展现对象属性的数据库操作
 * @author icode
 */
@Repository("viewModelPropertyDao")
public class ViewModelPropertyDao extends SimpleJpaRepository<ViewModelProperty, String, ViewModelPropertyCondition> {

	@Override
	public Specification<ViewModelProperty> buildQuery(final ViewModelPropertyCondition condition){

		return new ViewModelPropertySpecification(condition);
	}
}

class ViewModelPropertySpecification implements Specification<ViewModelProperty>{

	ViewModelPropertyCondition condition;

	public ViewModelPropertySpecification(ViewModelPropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ViewModelProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddViewModelPredicate(predicateList, root, cb);
		tryAddEditablePredicate(predicateList, root, cb);
		tryAddNullablePredicate(predicateList, root, cb);
		tryAddMemoPredicate(predicateList, root, cb);
		tryAddCoreRelationPredicate(predicateList, root, cb);
		tryAddDomainModelPredicate(predicateList, root, cb);
		tryAddDomainModelPropertyPredicate(predicateList, root, cb);
		tryAddPropertyGroupPredicate(predicateList, root, cb);
		tryAddGridIndexPredicate(predicateList, root, cb);
		tryAddGridHiddenPredicate(predicateList, root, cb);
		tryAddQuickSearchConditionPredicate(predicateList, root, cb);
		tryAddSearchConditionPredicate(predicateList, root, cb);
		tryAddFormIndexPredicate(predicateList, root, cb);
		tryAddFormHiddenPredicate(predicateList, root, cb);
		tryAddViewIndexPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(ViewModelProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(ViewModelProperty.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(ViewModelProperty.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddViewModelPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getViewModelId())){
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_VIEW_MODEL_ID).as(String.class), condition.getViewModelId()));
		}
	}
	private void tryAddEditablePredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getEditable() ) {
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_EDITABLE).as(Boolean.class), condition.getEditable()));
		}
	}
	private void tryAddNullablePredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getNullable() ) {
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_NULLABLE).as(Boolean.class), condition.getNullable()));
		}
	}
	private void tryAddMemoPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMemo())){
			predicateList.add(cb.like(root.get(ViewModelProperty.PROPERTY_MEMO).as(String.class), "%"+condition.getMemo()+"%"));
		}
	}
	private void tryAddCoreRelationPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getCoreRelation() ) {
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_CORE_RELATION).as(Boolean.class), condition.getCoreRelation()));
		}
	}
	private void tryAddDomainModelPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDomainModelId())){
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_DOMAIN_MODEL_ID).as(String.class), condition.getDomainModelId()));
		}
	}
	private void tryAddDomainModelPropertyPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDomainModelPropertyId())){
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_DOMAIN_MODEL_PROPERTY_ID).as(String.class), condition.getDomainModelPropertyId()));
		}
	}
	private void tryAddPropertyGroupPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPropertyGroup())){
			predicateList.add(cb.like(root.get(ViewModelProperty.PROPERTY_PROPERTY_GROUP).as(String.class), "%"+condition.getPropertyGroup()+"%"));
		}
	}
	private void tryAddGridIndexPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getGridIndex() ) {
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_GRID_INDEX).as(Integer.class), condition.getGridIndex()));
		}
	}
	private void tryAddGridHiddenPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getGridHidden() ) {
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_GRID_HIDDEN).as(Boolean.class), condition.getGridHidden()));
		}
	}
	private void tryAddQuickSearchConditionPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getQuickSearchCondition() ) {
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_QUICK_SEARCH_CONDITION).as(Boolean.class), condition.getQuickSearchCondition()));
		}
	}
	private void tryAddSearchConditionPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getSearchCondition() ) {
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_SEARCH_CONDITION).as(Boolean.class), condition.getSearchCondition()));
		}
	}
	private void tryAddFormIndexPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getFormIndex() ) {
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_FORM_INDEX).as(Integer.class), condition.getFormIndex()));
		}
	}
	private void tryAddFormHiddenPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getFormHidden() ) {
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_FORM_HIDDEN).as(Boolean.class), condition.getFormHidden()));
		}
	}
	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<ViewModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getViewIndex() ) {
			predicateList.add(cb.equal(root.get(ViewModelProperty.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndex()));
		}
	}
}

