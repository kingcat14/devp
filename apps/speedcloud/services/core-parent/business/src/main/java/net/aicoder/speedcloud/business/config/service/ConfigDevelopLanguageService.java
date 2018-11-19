package net.aicoder.speedcloud.business.config.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.config.dao.ConfigDevelopLanguageDao;
import net.aicoder.speedcloud.business.config.dao.ConfigDevelopLanguageSpecification;
import net.aicoder.speedcloud.business.config.domain.ConfigDevelopLanguage;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("configDevelopLanguageService")
public class ConfigDevelopLanguageService  extends GenericCrudService<ConfigDevelopLanguage, Long, ConfigDevelopLanguageCondition, ConfigDevelopLanguageDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDevelopLanguageService.class);

	@Override
	public Specification<ConfigDevelopLanguage> getSpecification(ConfigDevelopLanguageCondition condition) {
		return new ConfigDevelopLanguageSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ConfigDevelopLanguage.PROPERTY_NAME);
		return sort;
	}
}