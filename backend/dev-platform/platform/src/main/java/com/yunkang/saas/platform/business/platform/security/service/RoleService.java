package com.yunkang.saas.platform.business.platform.security.service;


import com.yunkang.saas.common.jpa.CrudService;
import com.yunkang.saas.platform.business.platform.security.dao.RoleDao;
import com.yunkang.saas.platform.business.platform.security.dao.RoleSpecification;
import com.yunkang.saas.platform.business.platform.security.domain.Role;
import com.yunkang.saas.platform.business.platform.security.dto.RoleCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("roleService")
public class RoleService  extends CrudService<Role, RoleCondition, RoleDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Role.class);

	@Override
	public Specification<Role> getSpecification(RoleCondition condition) {
		return new RoleSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , Role.PROPERTY_NAME);
		return sort;
	}
}