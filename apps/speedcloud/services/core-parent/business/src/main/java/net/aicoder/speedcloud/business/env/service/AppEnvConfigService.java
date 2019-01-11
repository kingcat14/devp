package net.aicoder.speedcloud.business.env.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.env.dao.AppEnvConfigDao;
import net.aicoder.speedcloud.business.env.dao.AppEnvConfigSpecification;
import net.aicoder.speedcloud.business.env.domain.AppEnvConfig;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appEnvConfigService")
@Slf4j
public class AppEnvConfigService  extends GenericCrudService<AppEnvConfig, String, AppEnvConfigCondition, AppEnvConfigDao> {

	@Override
	public Specification<AppEnvConfig> getSpecification(AppEnvConfigCondition condition) {
		return new AppEnvConfigSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.ASC, AppEnvConfig.PROPERTY_PROJECT, AppEnvConfig.PROPERTY_SEQ);

		return sort;
	}
}