package com.yunkang.saas.bootstrap.application.business.security.service;


import com.yunkang.saas.bootstrap.application.business.security.dao.RoleResourceRelationDao;
import com.yunkang.saas.bootstrap.application.business.security.dao.RoleResourceRelationSpecification;
import com.yunkang.saas.bootstrap.application.business.security.domain.RoleResourceRelation;
import com.yunkang.saas.bootstrap.platform.business.account.dto.RoleResourceRelationCondition;
import com.yunkang.saas.common.jpa.GenericCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("roleResourceRelationService")
public class RoleResourceRelationService  extends GenericCrudService<RoleResourceRelation, Long, RoleResourceRelationCondition, RoleResourceRelationDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleResourceRelation.class);

	@Transactional(readOnly=true)
	public List<RoleResourceRelation> findAllForRole(Long roleId){

		RoleResourceRelationCondition condition = new RoleResourceRelationCondition();
		condition.setRoleId(roleId);
		List<RoleResourceRelation> roleResourceRelationList = dao.findAll(getSpecification(condition), getDefaultSort());

		return roleResourceRelationList;
	}

	public int countByRoleIdAndResourceId(Long roleId, Long resourceId){
		return dao.countByRoleIdAndResourceId(roleId, resourceId);
	}


	@Transactional
	public void deleteForRole(Long roleId){

		if(null == (roleId)){
			LOGGER.error("try delete RoleResourceRelation for empty roleId, please check your code!", new RuntimeException("empty roleId"));

			return ;
		}

		RoleResourceRelationCondition condition = new RoleResourceRelationCondition();
		condition.setRoleId(roleId);
		LOGGER.debug("delete roleResourceRelation:{}", condition);

		List<RoleResourceRelation> relations = dao.findAll(getSpecification(condition));
		delete(relations);
	}

	@Transactional
	public void deleteForResource(Long resourceId){

		if(null == resourceId){
			LOGGER.error("try delete RoleResourceRelation for empty resourceId, please check your code!", new RuntimeException("empty resourceId"));

			return ;
		}

		RoleResourceRelationCondition condition = new RoleResourceRelationCondition();
		condition.setResourceId(resourceId);
		LOGGER.debug("delete roleResourceRelation:{}", condition);
		List<RoleResourceRelation> relations = dao.findAll(getSpecification(condition));
		delete(relations);
	}

	@Override
	public Specification<RoleResourceRelation> getSpecification(RoleResourceRelationCondition condition) {
		return new RoleResourceRelationSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , RoleResourceRelation.PROPERTY_ROLE_ID);
		return sort;
	}
}