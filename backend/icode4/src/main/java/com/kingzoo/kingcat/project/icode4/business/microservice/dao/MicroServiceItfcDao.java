package com.kingzoo.kingcat.project.icode4.business.microservice.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfc;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceItfcCondition;
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
 * 微服务接口的数据库操作
 * @author icode
 */
@Repository("microServiceItfcDao")
public class MicroServiceItfcDao extends SimpleJpaRepository<MicroServiceItfc, String, MicroServiceItfcCondition> {

	@Override
	public Specification<MicroServiceItfc> buildQuery(final MicroServiceItfcCondition condition){

		return new MicroServiceItfcSpecification(condition);
	}
}

class MicroServiceItfcSpecification implements Specification<MicroServiceItfc>{

	MicroServiceItfcCondition condition;

	public MicroServiceItfcSpecification(MicroServiceItfcCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<MicroServiceItfc> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddUrlPredicate(predicateList, root, cb);
		tryAddMethodPredicate(predicateList, root, cb);
		tryAddMicroServicePredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddResponsePredicate(predicateList, root, cb);
		tryAddRequestPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<MicroServiceItfc> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(MicroServiceItfc.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<MicroServiceItfc> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(MicroServiceItfc.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddUrlPredicate(List<Predicate> predicateList, Root<MicroServiceItfc> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUrl())){
			predicateList.add(cb.like(root.get(MicroServiceItfc.PROPERTY_URL).as(String.class), "%"+condition.getUrl()+"%"));
		}
	}
	private void tryAddMethodPredicate(List<Predicate> predicateList, Root<MicroServiceItfc> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMethod())){
			predicateList.add(cb.like(root.get(MicroServiceItfc.PROPERTY_METHOD).as(String.class), "%"+condition.getMethod()+"%"));
		}
	}
	private void tryAddMicroServicePredicate(List<Predicate> predicateList, Root<MicroServiceItfc> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMicroServiceId())){
			predicateList.add(cb.equal(root.get(MicroServiceItfc.PROPERTY_MICRO_SERVICE_ID).as(String.class), condition.getMicroServiceId()));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<MicroServiceItfc> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(MicroServiceItfc.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddResponsePredicate(List<Predicate> predicateList, Root<MicroServiceItfc> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getResponseId())){
			predicateList.add(cb.equal(root.get(MicroServiceItfc.PROPERTY_RESPONSE_ID).as(String.class), condition.getResponseId()));
		}
	}
	private void tryAddRequestPredicate(List<Predicate> predicateList, Root<MicroServiceItfc> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRequestId())){
			predicateList.add(cb.equal(root.get(MicroServiceItfc.PROPERTY_REQUEST_ID).as(String.class), condition.getRequestId()));
		}
	}
}

