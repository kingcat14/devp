package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.app.dao.SecurityConfigDao;
import net.aicoder.speedcloud.business.app.dao.SecurityConfigSpecification;
import net.aicoder.speedcloud.business.app.domain.SecurityConfig;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("securityConfigService")
@Slf4j
public class SecurityConfigService  extends GenericCrudService<SecurityConfig, String, SecurityConfigCondition, SecurityConfigDao> {

	@Override
	public Specification<SecurityConfig> getSpecification(SecurityConfigCondition condition) {
		return new SecurityConfigSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, SecurityConfig.PROPERTY_MODIFY_AT);

		return sort;
	}
}