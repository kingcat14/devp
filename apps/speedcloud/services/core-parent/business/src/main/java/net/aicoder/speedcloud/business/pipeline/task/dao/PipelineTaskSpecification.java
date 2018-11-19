package net.aicoder.speedcloud.business.pipeline.task.dao;

import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTaskTypePredicate(predicateList, root, cb);
		tryAddExecTypePredicate(predicateList, root, cb);
		tryAddTaskStartTimePredicate(predicateList, root, cb);
		tryAddTaskDayOfWeeksPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddProjectPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(PipelineTask.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineTask.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineTask.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineTask.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTaskTypePredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTaskType())){
			predicateList.add(cb.equal(root.get(PipelineTask.PROPERTY_TASK_TYPE).as(String.class), condition.getTaskType()));
		}
	}
	private void tryAddExecTypePredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getExecType())){
			predicateList.add(cb.equal(root.get(PipelineTask.PROPERTY_EXEC_TYPE).as(String.class), condition.getExecType()));
		}
	}
	private void tryAddTaskStartTimePredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTaskStartTime())){
			predicateList.add(cb.like(root.get(PipelineTask.PROPERTY_TASK_START_TIME).as(String.class), "%"+condition.getTaskStartTime()+"%"));
		}
	}
	private void tryAddTaskDayOfWeeksPredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTaskDayOfWeeks())){
			predicateList.add(cb.like(root.get(PipelineTask.PROPERTY_TASK_DAY_OF_WEEKS).as(String.class), "%"+condition.getTaskDayOfWeeks()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(PipelineTask.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddProjectPredicate(List<Predicate> predicateList, Root<PipelineTask> root, CriteriaBuilder cb){
	    if (null != condition.getProject() ) {
            predicateList.add(cb.equal(root.get(PipelineTask.PROPERTY_PROJECT).as(Long.class), condition.getProject()));
        }
	}
}


