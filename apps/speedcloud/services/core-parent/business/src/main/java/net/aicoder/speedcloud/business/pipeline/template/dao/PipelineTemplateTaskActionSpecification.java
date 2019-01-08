package net.aicoder.speedcloud.business.pipeline.template.dao;

import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskAction;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PipelineTemplateTaskActionSpecification implements Specification<PipelineTemplateTaskAction>{

	private PipelineTemplateTaskActionCondition condition;

	public PipelineTemplateTaskActionSpecification(PipelineTemplateTaskActionCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineTemplateTaskAction> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddTaskPredicate(predicateList, root, cb);
		tryAddMemoPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddExecIndexPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddContentPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineTemplateTaskAction> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(PipelineTemplateTaskAction.PROPERTY_TID).as(Long.class), condition.getTid()));
        }  
	}
	private void tryAddTaskPredicate(List<Predicate> predicateList, Root<PipelineTemplateTaskAction> root, CriteriaBuilder cb){
	    if (null != condition.getTask() ) {
            predicateList.add(cb.equal(root.get(PipelineTemplateTaskAction.PROPERTY_TASK).as(String.class), condition.getTask()));
        }
	}
	private void tryAddMemoPredicate(List<Predicate> predicateList, Root<PipelineTemplateTaskAction> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMemo())){
			predicateList.add(cb.like(root.get(PipelineTemplateTaskAction.PROPERTY_MEMO).as(String.class), "%"+condition.getMemo()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineTemplateTaskAction> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineTemplateTaskAction.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddExecIndexPredicate(List<Predicate> predicateList, Root<PipelineTemplateTaskAction> root, CriteriaBuilder cb){

		if (null != condition.getExecIndex() ) {
			predicateList.add(cb.equal(root.get(PipelineTemplateTaskAction.PROPERTY_EXEC_INDEX).as(Integer.class), condition.getExecIndex()));
		}

		if (null != condition.getExecIndexMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineTemplateTaskAction.PROPERTY_EXEC_INDEX).as(Integer.class), condition.getExecIndexMax()));
		}

		if (null != condition.getExecIndexMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineTemplateTaskAction.PROPERTY_EXEC_INDEX).as(Integer.class), condition.getExecIndexMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<PipelineTemplateTaskAction> root, CriteriaBuilder cb){
	    if (null != condition.getType() ) {
            predicateList.add(cb.equal(root.get(PipelineTemplateTaskAction.PROPERTY_TYPE).as(Long.class), condition.getType()));
        }
	}
	private void tryAddContentPredicate(List<Predicate> predicateList, Root<PipelineTemplateTaskAction> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getContent())){
			predicateList.add(cb.like(root.get(PipelineTemplateTaskAction.PROPERTY_CONTENT).as(String.class), "%"+condition.getContent()+"%"));
		}
	}
}


