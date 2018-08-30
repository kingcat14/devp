package net.aicoder.speedcloud.business.config.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.config.dao.ConfigDevelopLanguageVersionDao;
import net.aicoder.speedcloud.business.config.dao.ConfigDevelopLanguageVersionSpecification;
import net.aicoder.speedcloud.business.config.domain.ConfigDevelopLanguageVersion;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("configDevelopLanguageVersionService")
public class ConfigDevelopLanguageVersionService  extends GenericCrudService<ConfigDevelopLanguageVersion, Long, ConfigDevelopLanguageVersionCondition, ConfigDevelopLanguageVersionDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDevelopLanguageVersionService.class);

	@Override
	public Specification<ConfigDevelopLanguageVersion> getSpecification(ConfigDevelopLanguageVersionCondition condition) {
		return new ConfigDevelopLanguageVersionSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ConfigDevelopLanguageVersion.PROPERTY_NAME);
		return sort;
	}
}