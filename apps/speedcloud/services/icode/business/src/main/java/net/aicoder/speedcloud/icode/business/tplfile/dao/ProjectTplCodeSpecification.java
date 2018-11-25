package net.aicoder.speedcloud.icode.business.tplfile.dao;

import net.aicoder.speedcloud.icode.business.tplfile.domain.ProjectTplCode;
import net.aicoder.speedcloud.icode.business.tplfile.dto.ProjectTplCodeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProjectTplCodeSpecification implements Specification<ProjectTplCode>{

	private ProjectTplCodeCondition condition;

	public ProjectTplCodeSpecification(ProjectTplCodeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ProjectTplCode> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddFileNamePredicate(predicateList, root, cb);
		tryAddFilePathPredicate(predicateList, root, cb);
		tryAddContentPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddAcceptModelTypePredicate(predicateList, root, cb);
		tryAddOverridablePredicate(predicateList, root, cb);
		tryAddComponentPredicate(predicateList, root, cb);
		tryAddTplCodePredicate(predicateList, root, cb);
		tryAddAutoUpdatePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(ProjectTplCode.PROPERTY_TID).as(Long.class), condition.getTid()));
        }
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(ProjectTplCode.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(ProjectTplCode.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddFileNamePredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFileName())){
			predicateList.add(cb.like(root.get(ProjectTplCode.PROPERTY_FILE_NAME).as(String.class), "%"+condition.getFileName()+"%"));
		}
	}
	private void tryAddFilePathPredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFilePath())){
			predicateList.add(cb.like(root.get(ProjectTplCode.PROPERTY_FILE_PATH).as(String.class), "%"+condition.getFilePath()+"%"));
		}
	}
	private void tryAddContentPredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getContent())){
			predicateList.add(cb.like(root.get(ProjectTplCode.PROPERTY_CONTENT).as(String.class), "%"+condition.getContent()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(ProjectTplCode.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddAcceptModelTypePredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAcceptModelType())){
			predicateList.add(cb.like(root.get(ProjectTplCode.PROPERTY_ACCEPT_MODEL_TYPE).as(String.class), "%"+condition.getAcceptModelType()+"%"));
		}
	}
	private void tryAddOverridablePredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){

		if (null != condition.getOverridable() ) {
			predicateList.add(cb.equal(root.get(ProjectTplCode.PROPERTY_OVERRIDABLE).as(Integer.class), condition.getOverridable()));
		}

		if (null != condition.getOverridableMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ProjectTplCode.PROPERTY_OVERRIDABLE).as(Integer.class), condition.getOverridableMax()));
		}

		if (null != condition.getOverridableMin() ) {
			predicateList.add(cb.lessThan(root.get(ProjectTplCode.PROPERTY_OVERRIDABLE).as(Integer.class), condition.getOverridableMin()));
		}
	}
	private void tryAddComponentPredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){
	    if (null != condition.getComponent() ) {
            predicateList.add(cb.equal(root.get(ProjectTplCode.PROPERTY_COMPONENT).as(String.class), condition.getComponent()));
        }  
	}
	private void tryAddTplCodePredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){
	    if (null != condition.getTplCode() ) {
            predicateList.add(cb.equal(root.get(ProjectTplCode.PROPERTY_TPL_CODE).as(String.class), condition.getTplCode()));
        }  
	}
	private void tryAddAutoUpdatePredicate(List<Predicate> predicateList, Root<ProjectTplCode> root, CriteriaBuilder cb){
		if (null != condition.getAutoUpdate() ) {
			predicateList.add(cb.equal(root.get(ProjectTplCode.PROPERTY_AUTO_UPDATE).as(Boolean.class), condition.getAutoUpdate()));
		}
	}
}


