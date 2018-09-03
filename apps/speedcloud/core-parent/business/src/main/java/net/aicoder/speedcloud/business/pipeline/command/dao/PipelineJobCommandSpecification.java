package net.aicoder.speedcloud.business.pipeline.command.dao;

import net.aicoder.speedcloud.business.pipeline.command.dto.PipelineJobCommandCondition;
import net.aicoder.speedcloud.business.pipeline.command.domain.PipelineJobCommand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class PipelineJobCommandSpecification implements Specification<PipelineJobCommand>{

	private PipelineJobCommandCondition condition;

	public PipelineJobCommandSpecification(PipelineJobCommandCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineJobCommand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddTaskPredicate(predicateList, root, cb);
		tryAddCreateTimePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddResultPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineJobCommand> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(PipelineJobCommand.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineJobCommand.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineJobCommand.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddTaskPredicate(List<Predicate> predicateList, Root<PipelineJobCommand> root, CriteriaBuilder cb){
	    if (null != condition.getTask() ) {
            predicateList.add(cb.equal(root.get(PipelineJobCommand.PROPERTY_TASK).as(Long.class), condition.getTask()));
        }
	}
	private void tryAddCreateTimePredicate(List<Predicate> predicateList, Root<PipelineJobCommand> root, CriteriaBuilder cb){

		if (null != condition.getCreateTime() ) {
			predicateList.add(cb.equal(root.get(PipelineJobCommand.PROPERTY_CREATE_TIME).as(Date.class), condition.getCreateTime()));
		}

		if (null != condition.getCreateTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineJobCommand.PROPERTY_CREATE_TIME).as(Date.class), condition.getCreateTimeStart()));
		}

		if (null != condition.getCreateTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(PipelineJobCommand.PROPERTY_CREATE_TIME).as(Date.class), condition.getCreateTimeEnd()));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<PipelineJobCommand> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(PipelineJobCommand.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<PipelineJobCommand> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(PipelineJobCommand.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddResultPredicate(List<Predicate> predicateList, Root<PipelineJobCommand> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getResult())){
			predicateList.add(cb.like(root.get(PipelineJobCommand.PROPERTY_RESULT).as(String.class), "%"+condition.getResult()+"%"));
		}
	}
}


