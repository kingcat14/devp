package com.kingzoo.kingcat.project.icode4.business.database.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseTableInfo;
import com.kingzoo.kingcat.project.icode4.business.database.vo.DatabaseTableInfoCondition;
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
 * 数据库表信息的数据库操作
 * @author icode
 */
@Repository("databaseTableInfoDao")
public class DatabaseTableInfoDao extends SimpleJpaRepository<DatabaseTableInfo, String, DatabaseTableInfoCondition> {

	@Override
	public Specification<DatabaseTableInfo> buildQuery(final DatabaseTableInfoCondition condition){

		return new DatabaseTableInfoSpecification(condition);
	}
}

class DatabaseTableInfoSpecification implements Specification<DatabaseTableInfo>{

	DatabaseTableInfoCondition condition;

	public DatabaseTableInfoSpecification(DatabaseTableInfoCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DatabaseTableInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddConnectionIdPredicate(predicateList, root, cb);
		tryAddConnectionUrlPredicate(predicateList, root, cb);
		tryAddModuleNamePredicate(predicateList, root, cb);
		tryAddTableNamePredicate(predicateList, root, cb);
		tryAddTableDisplayNamePredicate(predicateList, root, cb);
		tryAddEntityNamePredicate(predicateList, root, cb);
		tryAddTableDescPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddConnectionIdPredicate(List<Predicate> predicateList, Root<DatabaseTableInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getConnectionId())){
			predicateList.add(cb.like(root.get(DatabaseTableInfo.PROPERTY_CONNECTION_ID).as(String.class), "%"+condition.getConnectionId()+"%"));
		}
	}
	private void tryAddConnectionUrlPredicate(List<Predicate> predicateList, Root<DatabaseTableInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getConnectionUrl())){
			predicateList.add(cb.like(root.get(DatabaseTableInfo.PROPERTY_CONNECTION_URL).as(String.class), "%"+condition.getConnectionUrl()+"%"));
		}
	}
	private void tryAddModuleNamePredicate(List<Predicate> predicateList, Root<DatabaseTableInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModuleName())){
			predicateList.add(cb.like(root.get(DatabaseTableInfo.PROPERTY_MODULE_NAME).as(String.class), "%"+condition.getModuleName()+"%"));
		}
	}
	private void tryAddTableNamePredicate(List<Predicate> predicateList, Root<DatabaseTableInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTableName())){
			predicateList.add(cb.like(root.get(DatabaseTableInfo.PROPERTY_TABLE_NAME).as(String.class), "%"+condition.getTableName()+"%"));
		}
	}
	private void tryAddTableDisplayNamePredicate(List<Predicate> predicateList, Root<DatabaseTableInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTableDisplayName())){
			predicateList.add(cb.like(root.get(DatabaseTableInfo.PROPERTY_TABLE_DISPLAY_NAME).as(String.class), "%"+condition.getTableDisplayName()+"%"));
		}
	}
	private void tryAddEntityNamePredicate(List<Predicate> predicateList, Root<DatabaseTableInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEntityName())){
			predicateList.add(cb.like(root.get(DatabaseTableInfo.PROPERTY_ENTITY_NAME).as(String.class), "%"+condition.getEntityName()+"%"));
		}
	}
	private void tryAddTableDescPredicate(List<Predicate> predicateList, Root<DatabaseTableInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTableDesc())){
			predicateList.add(cb.like(root.get(DatabaseTableInfo.PROPERTY_TABLE_DESC).as(String.class), "%"+condition.getTableDesc()+"%"));
		}
	}
}

