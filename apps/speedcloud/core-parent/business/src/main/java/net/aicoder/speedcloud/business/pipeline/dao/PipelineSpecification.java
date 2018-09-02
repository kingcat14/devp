package net.aicoder.speedcloud.business.pipeline.dao;

import net.aicoder.speedcloud.business.pipeline.dto.PipelineCondition;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class PipelineSpecification implements Specification<Pipeline>{

	private PipelineCondition condition;

	public PipelineSpecification(PipelineCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Pipeline> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddProjectPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<Pipeline> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(Pipeline.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Pipeline.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(Pipeline.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Pipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Pipeline.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<Pipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.equal(root.get(Pipeline.PROPERTY_TYPE).as(String.class), condition.getType()));
		}
	}
	private void tryAddProjectPredicate(List<Predicate> predicateList, Root<Pipeline> root, CriteriaBuilder cb){
	    if (null != condition.getProject() ) {
            predicateList.add(cb.equal(root.get(Pipeline.PROPERTY_PROJECT).as(Long.class), condition.getProject()));
        }
	}
}


