package net.aicoder.speedcloud.business.pipeline.dao;

import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskParamValueCondition;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTaskParamValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class PipelineTaskParamValueSpecification implements Specification<PipelineTaskParamValue>{

	private PipelineTaskParamValueCondition condition;

	public PipelineTaskParamValueSpecification(PipelineTaskParamValueCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineTaskParamValue> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTaskTypePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddViewOrderPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTaskTypePredicate(List<Predicate> predicateList, Root<PipelineTaskParamValue> root, CriteriaBuilder cb){
	    if (null != condition.getTaskType() ) {
            predicateList.add(cb.equal(root.get(PipelineTaskParamValue.PROPERTY_TASK_TYPE).as(Long.class), condition.getTaskType()));
        }
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineTaskParamValue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineTaskParamValue.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<PipelineTaskParamValue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(PipelineTaskParamValue.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddViewOrderPredicate(List<Predicate> predicateList, Root<PipelineTaskParamValue> root, CriteriaBuilder cb){

		if (null != condition.getViewOrder() ) {
			predicateList.add(cb.equal(root.get(PipelineTaskParamValue.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrder()));
		}

		if (null != condition.getViewOrderMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineTaskParamValue.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrderMax()));
		}

		if (null != condition.getViewOrderMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineTaskParamValue.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrderMin()));
		}
	}
}


