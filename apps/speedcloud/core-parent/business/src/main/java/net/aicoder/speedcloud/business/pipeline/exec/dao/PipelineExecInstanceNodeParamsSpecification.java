package net.aicoder.speedcloud.business.pipeline.exec.dao;

import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsCondition;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class PipelineExecInstanceNodeParamsSpecification implements Specification<PipelineExecInstanceNodeParams>{

	private PipelineExecInstanceNodeParamsCondition condition;

	public PipelineExecInstanceNodeParamsSpecification(PipelineExecInstanceNodeParamsCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineExecInstanceNodeParams> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddTaskTypePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddValuePredicate(predicateList, root, cb);
		tryAddViewOrderPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineExecInstanceNodeParams> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(PipelineExecInstanceNodeParams.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineExecInstanceNodeParams.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineExecInstanceNodeParams.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddTaskTypePredicate(List<Predicate> predicateList, Root<PipelineExecInstanceNodeParams> root, CriteriaBuilder cb){
	    if (null != condition.getTaskType() ) {
            predicateList.add(cb.equal(root.get(PipelineExecInstanceNodeParams.PROPERTY_TASK_TYPE).as(Long.class), condition.getTaskType()));
        }
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineExecInstanceNodeParams> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineExecInstanceNodeParams.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<PipelineExecInstanceNodeParams> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(PipelineExecInstanceNodeParams.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddValuePredicate(List<Predicate> predicateList, Root<PipelineExecInstanceNodeParams> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getValue())){
			predicateList.add(cb.like(root.get(PipelineExecInstanceNodeParams.PROPERTY_VALUE).as(String.class), "%"+condition.getValue()+"%"));
		}
	}
	private void tryAddViewOrderPredicate(List<Predicate> predicateList, Root<PipelineExecInstanceNodeParams> root, CriteriaBuilder cb){

		if (null != condition.getViewOrder() ) {
			predicateList.add(cb.equal(root.get(PipelineExecInstanceNodeParams.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrder()));
		}

		if (null != condition.getViewOrderMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineExecInstanceNodeParams.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrderMax()));
		}

		if (null != condition.getViewOrderMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineExecInstanceNodeParams.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrderMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<PipelineExecInstanceNodeParams> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(PipelineExecInstanceNodeParams.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
}


