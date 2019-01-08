package net.aicoder.speedcloud.business.pipeline.template.dao;

import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTask;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PipelineTemplateTaskSpecification implements Specification<PipelineTemplateTask>{

	private PipelineTemplateTaskCondition condition;

	public PipelineTemplateTaskSpecification(PipelineTemplateTaskCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineTemplateTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineTemplateTask> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(PipelineTemplateTask.PROPERTY_TID).as(Long.class), condition.getTid()));
        }  
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineTemplateTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineTemplateTask.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTaskTypePredicate(List<Predicate> predicateList, Root<PipelineTemplateTask> root, CriteriaBuilder cb){
	    if (null != condition.getTaskType() ) {
            predicateList.add(cb.equal(root.get(PipelineTemplateTask.PROPERTY_TASK_TYPE).as(String.class), condition.getTaskType()));
        }
	}
	private void tryAddExecTypePredicate(List<Predicate> predicateList, Root<PipelineTemplateTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getExecType())){
			predicateList.add(cb.equal(root.get(PipelineTemplateTask.PROPERTY_EXEC_TYPE).as(String.class), condition.getExecType()));
		}
	}
	private void tryAddTaskStartTimePredicate(List<Predicate> predicateList, Root<PipelineTemplateTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTaskStartTime())){
			predicateList.add(cb.like(root.get(PipelineTemplateTask.PROPERTY_TASK_START_TIME).as(String.class), "%"+condition.getTaskStartTime()+"%"));
		}
	}
	private void tryAddTaskDayOfWeeksPredicate(List<Predicate> predicateList, Root<PipelineTemplateTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTaskDayOfWeeks())){
			predicateList.add(cb.like(root.get(PipelineTemplateTask.PROPERTY_TASK_DAY_OF_WEEKS).as(String.class), "%"+condition.getTaskDayOfWeeks()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<PipelineTemplateTask> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(PipelineTemplateTask.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
}


