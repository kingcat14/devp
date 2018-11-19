package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.app.dao.SecurityConfigDao;
import net.aicoder.speedcloud.business.app.dao.SecurityConfigSpecification;
import net.aicoder.speedcloud.business.app.domain.SecurityConfig;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("securityConfigService")
public class SecurityConfigService  extends GenericCrudService<SecurityConfig, Long, SecurityConfigCondition, SecurityConfigDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfigService.class);

	@Override
	public Specification<SecurityConfig> getSpecification(SecurityConfigCondition condition) {
		return new SecurityConfigSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, SecurityConfig.PROPERTY_TID);
		return sort;
	}
}