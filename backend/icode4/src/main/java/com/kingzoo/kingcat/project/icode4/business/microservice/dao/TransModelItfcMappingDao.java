package com.kingzoo.kingcat.project.icode4.business.microservice.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModelItfcMapping;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelItfcMappingCondition;
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
 * 传输对象接口映射的数据库操作
 * @author icode
 */
@Repository("transModelItfcMappingDao")
public class TransModelItfcMappingDao extends SimpleJpaRepository<TransModelItfcMapping, String, TransModelItfcMappingCondition> {

	@Override
	public Specification<TransModelItfcMapping> buildQuery(final TransModelItfcMappingCondition condition){

		return new TransModelItfcMappingSpecification(condition);
	}
}

class TransModelItfcMappingSpecification implements Specification<TransModelItfcMapping>{

	TransModelItfcMappingCondition condition;

	public TransModelItfcMappingSpecification(TransModelItfcMappingCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<TransModelItfcMapping> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddTransModelIdPredicate(predicateList, root, cb);
		tryAddMicroServiceItfcIdPredicate(predicateList, root, cb);
		tryAddRelationTypePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddTransModelIdPredicate(List<Predicate> predicateList, Root<TransModelItfcMapping> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTransModelId())){
			predicateList.add(cb.like(root.get(TransModelItfcMapping.PROPERTY_TRANS_MODEL_ID).as(String.class), "%"+condition.getTransModelId()+"%"));
		}
	}
	private void tryAddMicroServiceItfcIdPredicate(List<Predicate> predicateList, Root<TransModelItfcMapping> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMicroServiceItfcId())){
			predicateList.add(cb.like(root.get(TransModelItfcMapping.PROPERTY_MICRO_SERVICE_ITFC_ID).as(String.class), "%"+condition.getMicroServiceItfcId()+"%"));
		}
	}
	private void tryAddRelationTypePredicate(List<Predicate> predicateList, Root<TransModelItfcMapping> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRelationType())){
			predicateList.add(cb.like(root.get(TransModelItfcMapping.PROPERTY_RELATION_TYPE).as(String.class), "%"+condition.getRelationType()+"%"));
		}
	}
}

