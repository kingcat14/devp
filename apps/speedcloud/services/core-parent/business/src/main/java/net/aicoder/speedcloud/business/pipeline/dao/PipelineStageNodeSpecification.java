package net.aicoder.speedcloud.business.pipeline.dao;

import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PipelineStageNodeSpecification implements Specification<PipelineStageNode>{

	private PipelineStageNodeCondition condition;

	public PipelineStageNodeSpecification(PipelineStageNodeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineStageNode> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddStagePredicate(predicateList, root, cb);
		tryAddObjTypePredicate(predicateList, root, cb);
		tryAddObjIdPredicate(predicateList, root, cb);
		tryAddExecOrderPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineStageNode> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(PipelineStageNode.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineStageNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineStageNode.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddStagePredicate(List<Predicate> predicateList, Root<PipelineStageNode> root, CriteriaBuilder cb){
	    if (null != condition.getStage() ) {
            predicateList.add(cb.equal(root.get(PipelineStageNode.PROPERTY_STAGE).as(Long.class), condition.getStage()));
        }
	}
	private void tryAddObjTypePredicate(List<Predicate> predicateList, Root<PipelineStageNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getObjType())){
			predicateList.add(cb.equal(root.get(PipelineStageNode.PROPERTY_OBJ_TYPE).as(String.class), condition.getObjType()));
		}
	}
	private void tryAddObjIdPredicate(List<Predicate> predicateList, Root<PipelineStageNode> root, CriteriaBuilder cb){

		if (null != condition.getObjId() ) {
			predicateList.add(cb.equal(root.get(PipelineStageNode.PROPERTY_OBJ_ID).as(Long.class), condition.getObjId()));
		}

		if (null != condition.getObjIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineStageNode.PROPERTY_OBJ_ID).as(Long.class), condition.getObjIdMax()));
		}

		if (null != condition.getObjIdMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineStageNode.PROPERTY_OBJ_ID).as(Long.class), condition.getObjIdMin()));
		}
	}
	private void tryAddExecOrderPredicate(List<Predicate> predicateList, Root<PipelineStageNode> root, CriteriaBuilder cb){

		if (null != condition.getExecOrder() ) {
			predicateList.add(cb.equal(root.get(PipelineStageNode.PROPERTY_EXEC_ORDER).as(Integer.class), condition.getExecOrder()));
		}

		if (null != condition.getExecOrderMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineStageNode.PROPERTY_EXEC_ORDER).as(Integer.class), condition.getExecOrderMax()));
		}

		if (null != condition.getExecOrderMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineStageNode.PROPERTY_EXEC_ORDER).as(Integer.class), condition.getExecOrderMin()));
		}
	}
}


