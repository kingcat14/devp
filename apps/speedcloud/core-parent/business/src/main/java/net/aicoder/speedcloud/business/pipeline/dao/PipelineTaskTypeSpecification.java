package net.aicoder.speedcloud.business.pipeline.dao;

import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeCondition;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTaskType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class PipelineTaskTypeSpecification implements Specification<PipelineTaskType>{

	private PipelineTaskTypeCondition condition;

	public PipelineTaskTypeSpecification(PipelineTaskTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineTaskType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddCodePredicate(List<Predicate> predicateList, Root<PipelineTaskType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(PipelineTaskType.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineTaskType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineTaskType.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
}


