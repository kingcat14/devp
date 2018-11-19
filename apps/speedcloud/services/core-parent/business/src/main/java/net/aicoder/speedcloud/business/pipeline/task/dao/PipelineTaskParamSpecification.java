package net.aicoder.speedcloud.business.pipeline.task.dao;

import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PipelineTaskParamSpecification implements Specification<PipelineTaskParam>{

	private PipelineTaskParamCondition condition;

	public PipelineTaskParamSpecification(PipelineTaskParamCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineTaskParam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddTaskPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddDefaultValuePredicate(predicateList, root, cb);
		tryAddViewOrderPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddDeletablePredicate(predicateList, root, cb);
		tryAddEnumValuePredicate(predicateList, root, cb);
		tryAddValuePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineTaskParam> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(PipelineTaskParam.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineTaskParam.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineTaskParam.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddTaskPredicate(List<Predicate> predicateList, Root<PipelineTaskParam> root, CriteriaBuilder cb){
	    if (null != condition.getTask() ) {
            predicateList.add(cb.equal(root.get(PipelineTaskParam.PROPERTY_TASK).as(Long.class), condition.getTask()));
        }
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineTaskParam> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineTaskParam.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<PipelineTaskParam> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(PipelineTaskParam.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddDefaultValuePredicate(List<Predicate> predicateList, Root<PipelineTaskParam> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDefaultValue())){
			predicateList.add(cb.like(root.get(PipelineTaskParam.PROPERTY_DEFAULT_VALUE).as(String.class), "%"+condition.getDefaultValue()+"%"));
		}
	}
	private void tryAddViewOrderPredicate(List<Predicate> predicateList, Root<PipelineTaskParam> root, CriteriaBuilder cb){

		if (null != condition.getViewOrder() ) {
			predicateList.add(cb.equal(root.get(PipelineTaskParam.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrder()));
		}

		if (null != condition.getViewOrderMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineTaskParam.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrderMax()));
		}

		if (null != condition.getViewOrderMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineTaskParam.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrderMin()));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<PipelineTaskParam> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(PipelineTaskParam.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddDeletablePredicate(List<Predicate> predicateList, Root<PipelineTaskParam> root, CriteriaBuilder cb){
		if (null != condition.getDeletable() ) {
			predicateList.add(cb.equal(root.get(PipelineTaskParam.PROPERTY_DELETABLE).as(Boolean.class), condition.getDeletable()));
		}
	}
	private void tryAddEnumValuePredicate(List<Predicate> predicateList, Root<PipelineTaskParam> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEnumValue())){
			predicateList.add(cb.like(root.get(PipelineTaskParam.PROPERTY_ENUM_VALUE).as(String.class), "%"+condition.getEnumValue()+"%"));
		}
	}
	private void tryAddValuePredicate(List<Predicate> predicateList, Root<PipelineTaskParam> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getValue())){
			predicateList.add(cb.like(root.get(PipelineTaskParam.PROPERTY_VALUE).as(String.class), "%"+condition.getValue()+"%"));
		}
	}
}


