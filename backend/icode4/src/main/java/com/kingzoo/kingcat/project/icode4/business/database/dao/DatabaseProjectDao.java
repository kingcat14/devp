package com.kingzoo.kingcat.project.icode4.business.database.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseProject;
import com.kingzoo.kingcat.project.icode4.business.database.vo.DatabaseProjectCondition;
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
 * 数据库项目的数据库操作
 * @author icode
 */
@Repository("databaseProjectDao")
public class DatabaseProjectDao extends SimpleJpaRepository<DatabaseProject, String, DatabaseProjectCondition> {

	@Override
	public Specification<DatabaseProject> buildQuery(final DatabaseProjectCondition condition){

		return new DatabaseProjectSpecification(condition);
	}
}

class DatabaseProjectSpecification implements Specification<DatabaseProject>{

	DatabaseProjectCondition condition;

	public DatabaseProjectSpecification(DatabaseProjectCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DatabaseProject> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddUrlPredicate(predicateList, root, cb);
		tryAddUsernamePredicate(predicateList, root, cb);
		tryAddPasswordPredicate(predicateList, root, cb);
		tryAddDriverNamePredicate(predicateList, root, cb);
		tryAddEditablePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DatabaseProject> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DatabaseProject.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddUrlPredicate(List<Predicate> predicateList, Root<DatabaseProject> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUrl())){
			predicateList.add(cb.like(root.get(DatabaseProject.PROPERTY_URL).as(String.class), "%"+condition.getUrl()+"%"));
		}
	}
	private void tryAddUsernamePredicate(List<Predicate> predicateList, Root<DatabaseProject> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUsername())){
			predicateList.add(cb.like(root.get(DatabaseProject.PROPERTY_USERNAME).as(String.class), "%"+condition.getUsername()+"%"));
		}
	}
	private void tryAddPasswordPredicate(List<Predicate> predicateList, Root<DatabaseProject> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPassword())){
			predicateList.add(cb.like(root.get(DatabaseProject.PROPERTY_PASSWORD).as(String.class), "%"+condition.getPassword()+"%"));
		}
	}
	private void tryAddDriverNamePredicate(List<Predicate> predicateList, Root<DatabaseProject> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDriverName())){
			predicateList.add(cb.like(root.get(DatabaseProject.PROPERTY_DRIVER_NAME).as(String.class), "%"+condition.getDriverName()+"%"));
		}
	}
	private void tryAddEditablePredicate(List<Predicate> predicateList, Root<DatabaseProject> root, CriteriaBuilder cb){
		if (null != condition.getEditable() ) {
			predicateList.add(cb.equal(root.get(DatabaseProject.PROPERTY_EDITABLE).as(Boolean.class), condition.getEditable()));
		}
	}
}

