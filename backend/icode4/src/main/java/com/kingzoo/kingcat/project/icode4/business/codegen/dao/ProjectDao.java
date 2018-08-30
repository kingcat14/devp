package com.kingzoo.kingcat.project.icode4.business.codegen.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.Project;
import com.kingzoo.kingcat.project.icode4.business.codegen.vo.ProjectCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 工程的数据库操作
 * @author icode
 */
@Repository("projectDao")
public class ProjectDao extends SimpleJpaRepository<Project, String, ProjectCondition> {

	@Override
	public Specification<Project> buildQuery(final ProjectCondition condition){

		return new ProjectSpecification(condition);
	}
}

class ProjectSpecification implements Specification<Project>{

	ProjectCondition condition;

	public ProjectSpecification(ProjectCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddBasePackagePredicate(predicateList, root, cb);
		tryAddTplSetIdPredicate(predicateList, root, cb);
		tryAddProjectPathPredicate(predicateList, root, cb);
		tryAddGroupCodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Project.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Project.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(Project.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddBasePackagePredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBasePackage())){
			predicateList.add(cb.like(root.get(Project.PROPERTY_BASE_PACKAGE).as(String.class), "%"+condition.getBasePackage()+"%"));
		}
	}
	private void tryAddTplSetIdPredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTplSetId())){
			predicateList.add(cb.equal(root.get(Project.PROPERTY_TPL_SET_ID).as(String.class), condition.getTplSetId()));
		}
	}
	private void tryAddProjectPathPredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProjectPath())){
			predicateList.add(cb.like(root.get(Project.PROPERTY_PROJECT_PATH).as(String.class), "%"+condition.getProjectPath()+"%"));
		}
	}
	private void tryAddGroupCodePredicate(List<Predicate> predicateList, Root<Project> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getGroupCode())){
			predicateList.add(cb.like(root.get(Project.PROPERTY_GROUP_CODE).as(String.class), "%"+condition.getGroupCode()+"%"));
		}
	}
}

