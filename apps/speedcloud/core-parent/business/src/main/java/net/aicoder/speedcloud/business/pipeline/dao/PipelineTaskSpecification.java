package net.aicoder.speedcloud.business.pipeline.dao;

import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskCondition;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTask;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class PipelineTaskSpecification implements Specification<PipelineTask>{

	private PipelineTaskCondition condition;

	public PipelineTaskSpecification(PipelineTaskCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddNamePredicate(predicateList, root, cb);
		tryAddStagePredicate(predicateList, root, cb);
		tryAddExecOrderPredicate(predicateList, root, cb);
		tryAddTaskTypePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineTask.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddStagePredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){
	    if (null != condition.getStage() ) {
            predicateList.add(cb.equal(root.get(PipelineTask.PROPERTY_STAGE).as(Long.class), condition.getStage()));
        }
	}
	private void tryAddExecOrderPredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){

		if (null != condition.getExecOrder() ) {
			predicateList.add(cb.equal(root.get(PipelineTask.PROPERTY_EXEC_ORDER).as(Integer.class), condition.getExecOrder()));
		}

		if (null != condition.getExecOrderMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineTask.PROPERTY_EXEC_ORDER).as(Integer.class), condition.getExecOrderMax()));
		}

		if (null != condition.getExecOrderMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineTask.PROPERTY_EXEC_ORDER).as(Integer.class), condition.getExecOrderMin()));
		}
	}
	private void tryAddTaskTypePredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){
	    if (null != condition.getTaskType() ) {
            predicateList.add(cb.equal(root.get(PipelineTask.PROPERTY_TASK_TYPE).as(Long.class), condition.getTaskType()));
        }
	}
}


