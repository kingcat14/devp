package net.aicoder.speedcloud.business.project.dao;

import net.aicoder.speedcloud.business.project.domain.Project;
import net.aicoder.speedcloud.business.project.dto.ProjectCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProjectSpecification implements Specification<Project>{

	private ProjectCondition condition;

	public ProjectSpecification(ProjectCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddScopePredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddParentPredicate(predicateList, root, cb);
		tryAddProjectSetPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(Project.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Project.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(Project.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddScopePredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScope())){
			predicateList.add(cb.like(root.get(Project.PROPERTY_SCOPE).as(String.class), "%"+condition.getScope()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(Project.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddParentPredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParent())){
			predicateList.add(cb.like(root.get(Project.PROPERTY_PARENT).as(String.class), "%"+condition.getParent()+"%"));
		}
	}
	private void tryAddProjectSetPredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
	    if (null != condition.getProjectSet() ) {
            predicateList.add(cb.equal(root.get(Project.PROPERTY_PROJECT_SET).as(Long.class), condition.getProjectSet()));
        }
	}
}


