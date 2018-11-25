package net.aicoder.speedcloud.business.pipeline.task.dao;

import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionTypeProperty;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PipelineTaskActionTypePropertySpecification implements Specification<PipelineTaskActionTypeProperty>{

	private PipelineTaskActionTypePropertyCondition condition;

	public PipelineTaskActionTypePropertySpecification(PipelineTaskActionTypePropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineTaskActionTypeProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddTaskTypePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddViewOrderPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddOptionValuePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineTaskActionTypeProperty> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(PipelineTaskActionTypeProperty.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddTaskTypePredicate(List<Predicate> predicateList, Root<PipelineTaskActionTypeProperty> root, CriteriaBuilder cb){
	    if (null != condition.getTaskType() ) {
            predicateList.add(cb.equal(root.get(PipelineTaskActionTypeProperty.PROPERTY_TASK_TYPE).as(Long.class), condition.getTaskType()));
        }
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineTaskActionTypeProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineTaskActionTypeProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<PipelineTaskActionTypeProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(PipelineTaskActionTypeProperty.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddViewOrderPredicate(List<Predicate> predicateList, Root<PipelineTaskActionTypeProperty> root, CriteriaBuilder cb){

		if (null != condition.getViewOrder() ) {
			predicateList.add(cb.equal(root.get(PipelineTaskActionTypeProperty.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrder()));
		}

		if (null != condition.getViewOrderMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineTaskActionTypeProperty.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrderMax()));
		}

		if (null != condition.getViewOrderMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineTaskActionTypeProperty.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrderMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<PipelineTaskActionTypeProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.equal(root.get(PipelineTaskActionTypeProperty.PROPERTY_TYPE).as(String.class), condition.getType()));
		}
	}
	private void tryAddOptionValuePredicate(List<Predicate> predicateList, Root<PipelineTaskActionTypeProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getOptionValue())){
			predicateList.add(cb.like(root.get(PipelineTaskActionTypeProperty.PROPERTY_OPTION_VALUE).as(String.class), "%"+condition.getOptionValue()+"%"));
		}
	}
}


