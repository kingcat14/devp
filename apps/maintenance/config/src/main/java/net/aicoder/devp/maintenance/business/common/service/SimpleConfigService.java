package net.aicoder.devp.maintenance.business.common.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.maintenance.business.common.dao.SimpleConfigDao;
import net.aicoder.devp.maintenance.business.common.dao.SimpleConfigSpecification;
import net.aicoder.devp.maintenance.business.common.domain.SimpleConfig;
import net.aicoder.devp.maintenance.business.common.dto.SimpleConfigCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("simpleConfigService")
public class SimpleConfigService  extends GenericCrudService<SimpleConfig, Long, SimpleConfigCondition, SimpleConfigDao> {

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