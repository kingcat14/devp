package net.aicoder.devp.maintenance.business.config.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.maintenance.business.config.dao.SimpleConfigDao;
import net.aicoder.devp.maintenance.business.config.dao.SimpleConfigSpecification;
import net.aicoder.devp.maintenance.business.config.domain.SimpleConfig;
import net.aicoder.devp.maintenance.business.config.dto.SimpleConfigCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("simpleConfigService")
public class SimpleConfigService  extends CrudService<SimpleConfig, SimpleConfigCondition, SimpleConfigDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleConfigService.class);

	@Override
	public Specification<SimpleConfig> getSpecification(SimpleConfigCondition condition) {
		return new SimpleConfigSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , SimpleConfig.PROPERTY_TID);
		return sort;
	}
}