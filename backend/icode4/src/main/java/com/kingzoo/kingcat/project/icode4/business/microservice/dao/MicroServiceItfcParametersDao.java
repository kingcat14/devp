package com.kingzoo.kingcat.project.icode4.business.microservice.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfcParameters;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceItfcParametersCondition;
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
 * 微服务接口参数的数据库操作
 * @author icode
 */
@Repository("microServiceItfcParametersDao")
public class MicroServiceItfcParametersDao extends SimpleJpaRepository<MicroServiceItfcParameters, String, MicroServiceItfcParametersCondition> {

	@Override
	public Specification<MicroServiceItfcParameters> buildQuery(final MicroServiceItfcParametersCondition condition){

		return new MicroServiceItfcParametersSpecification(condition);
	}
}

class MicroServiceItfcParametersSpecification implements Specification<MicroServiceItfcParameters>{

	MicroServiceItfcParametersCondition condition;

	public MicroServiceItfcParametersSpecification(MicroServiceItfcParametersCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<MicroServiceItfcParameters> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddViewIndexPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddPathMappingPredicate(predicateList, root, cb);
		tryAddMemoPredicate(predicateList, root, cb);
		tryAddMicroServiceItfcPredicate(predicateList, root, cb);
		tryAddRequiredPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<MicroServiceItfcParameters> root, CriteriaBuilder cb){
		if (null != condition.getViewIndex() ) {
			predicateList.add(cb.equal(root.get(MicroServiceItfcParameters.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndex()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<MicroServiceItfcParameters> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(MicroServiceItfcParameters.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<MicroServiceItfcParameters> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(MicroServiceItfcParameters.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<MicroServiceItfcParameters> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(MicroServiceItfcParameters.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddPathMappingPredicate(List<Predicate> predicateList, Root<MicroServiceItfcParameters> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPathMapping())){
			predicateList.add(cb.like(root.get(MicroServiceItfcParameters.PROPERTY_PATH_MAPPING).as(String.class), "%"+condition.getPathMapping()+"%"));
		}
	}
	private void tryAddMemoPredicate(List<Predicate> predicateList, Root<MicroServiceItfcParameters> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMemo())){
			predicateList.add(cb.like(root.get(MicroServiceItfcParameters.PROPERTY_MEMO).as(String.class), "%"+condition.getMemo()+"%"));
		}
	}
	private void tryAddMicroServiceItfcPredicate(List<Predicate> predicateList, Root<MicroServiceItfcParameters> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMicroServiceItfcId())){
			predicateList.add(cb.equal(root.get(MicroServiceItfcParameters.PROPERTY_MICRO_SERVICE_ITFC_ID).as(String.class), condition.getMicroServiceItfcId()));
		}
	}
	private void tryAddRequiredPredicate(List<Predicate> predicateList, Root<MicroServiceItfcParameters> root, CriteriaBuilder cb){
		if (null != condition.getRequired() ) {
			predicateList.add(cb.equal(root.get(MicroServiceItfcParameters.PROPERTY_REQUIRED).as(Boolean.class), condition.getRequired()));
		}
	}
}

