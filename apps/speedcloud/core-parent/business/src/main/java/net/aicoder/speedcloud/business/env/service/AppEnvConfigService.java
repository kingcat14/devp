package net.aicoder.speedcloud.business.env.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.env.dao.AppEnvConfigDao;
import net.aicoder.speedcloud.business.env.dao.AppEnvConfigSpecification;
import net.aicoder.speedcloud.business.env.domain.AppEnvConfig;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appEnvConfigService")
public class AppEnvConfigService  extends GenericCrudService<AppEnvConfig, Long, AppEnvConfigCondition, AppEnvConfigDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppEnvConfigService.class);

	@Override
	public Specification<AppEnvConfig> getSpecification(AppEnvConfigCondition condition) {
		return new AppEnvConfigSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AppEnvConfig.PROPERTY_TID);
		return sort;
	}
}