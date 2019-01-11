package com.yunkang.saas.application.business.application.dao;

import com.yunkang.saas.application.business.application.domain.App;
import com.yunkang.saas.application.business.application.dto.AppCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppSpecification implements Specification<App>{

	AppCondition condition;

	public AppSpecification(AppCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<App> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddTenantIdPredicate(predicateList, root, cb);
		tryAddAppCategoryCodePredicate(predicateList, root, cb);
		tryAddAppCategoryIdPredicate(predicateList, root, cb);
		tryAddLabelPredicate(predicateList, root, cb);
		tryAddEnablePredicate(predicateList, root, cb);
		tryAddOnBoardTimePredicate(predicateList, root, cb);
		tryAddUrlPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddVisiblePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(App.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(App.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddTenantIdPredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if (null != condition.getTenantId() ) {
			predicateList.add(cb.equal(root.get(App.PROPERTY_TENANT_ID).as(Long.class), condition.getTenantId()));
		}
	}
	private void tryAddAppCategoryCodePredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAppCategoryCode())){
			predicateList.add(cb.like(root.get(App.PROPERTY_APP_CATEGORY_CODE).as(String.class), "%"+condition.getAppCategoryCode()+"%"));
		}
	}
	private void tryAddAppCategoryIdPredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAppCategoryId())){
			predicateList.add(cb.like(root.get(App.PROPERTY_APP_CATEGORY_ID).as(String.class), "%"+condition.getAppCategoryId()+"%"));
		}
	}
	private void tryAddLabelPredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getLabel())){
			predicateList.add(cb.like(root.get(App.PROPERTY_LABEL).as(String.class), "%"+condition.getLabel()+"%"));
		}
	}
	private void tryAddEnablePredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if (null != condition.getEnable() ) {
			predicateList.add(cb.equal(root.get(App.PROPERTY_ENABLE).as(Boolean.class), condition.getEnable()));
		}
	}
	private void tryAddOnBoardTimePredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){

		if (null != condition.getOnBoardTime() ) {
			predicateList.add(cb.equal(root.get(App.PROPERTY_ON_BOARD_TIME).as(Date.class), condition.getOnBoardTime()));
		}

		if (null != condition.getOnBoardTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(App.PROPERTY_ON_BOARD_TIME).as(Date.class), condition.getOnBoardTimeStart()));
		}

		if (null != condition.getOnBoardTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(App.PROPERTY_ON_BOARD_TIME).as(Date.class), condition.getOnBoardTimeEnd()));
		}

	}
	private void tryAddUrlPredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUrl())){
			predicateList.add(cb.like(root.get(App.PROPERTY_URL).as(String.class), "%"+condition.getUrl()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(App.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddVisiblePredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if (null != condition.getVisible() ) {
			predicateList.add(cb.equal(root.get(App.PROPERTY_VISIBLE).as(Boolean.class), condition.getVisible()));
		}
	}
}


