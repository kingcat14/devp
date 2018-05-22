package net.aicoder.devp.maintenanceui.business.security.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.maintenanceui.business.security.dao.RoleDao;
import net.aicoder.devp.maintenanceui.business.security.dao.RoleSpecification;
import net.aicoder.devp.maintenanceui.business.security.domain.Role;
import net.aicoder.devp.maintenanceui.business.security.dto.RoleCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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