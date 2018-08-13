package com.yunkang.saas.platform.business.platform.application.dao;

import com.yunkang.saas.platform.business.platform.application.dto.AppCondition;
import com.yunkang.saas.platform.business.platform.application.domain.App;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class AppSpecification implements Specification<App>{

	private AppCondition condition;

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
		tryAddAppCategoryIdPredicate(predicateList, root, cb);
		tryAddLabelPredicate(predicateList, root, cb);
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
	private void tryAddAppCategoryIdPredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if (null != condition.getAppCategoryId() ) {
			predicateList.add(cb.equal(root.get(App.PROPERTY_APP_CATEGORY_ID).as(Long.class), condition.getAppCategoryId()));
		}
	}
	private void tryAddLabelPredicate(List<Predicate> predicateList, Root<App> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getLabel())){
			predicateList.add(cb.like(root.get(App.PROPERTY_LABEL).as(String.class), "%"+condition.getLabel()+"%"));
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


