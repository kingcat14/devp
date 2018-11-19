package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.app.dao.AppDevelopConfigDao;
import net.aicoder.speedcloud.business.app.dao.AppDevelopConfigSpecification;
import net.aicoder.speedcloud.business.app.domain.AppDevelopConfig;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appDevelopConfigService")
public class AppDevelopConfigService  extends GenericCrudService<AppDevelopConfig, Long, AppDevelopConfigCondition, AppDevelopConfigDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppDevelopConfigService.class);

	@Override
	public Specification<AppDevelopConfig> getSpecification(AppDevelopConfigCondition condition) {
		return new AppDevelopConfigSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AppDevelopConfig.PROPERTY_TID);
		return sort;
	}
}