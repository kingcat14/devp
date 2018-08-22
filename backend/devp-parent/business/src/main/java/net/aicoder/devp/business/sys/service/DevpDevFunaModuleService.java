package net.aicoder.devp.business.sys.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.sys.dao.DevpDevFunaModuleDao;
import net.aicoder.devp.business.sys.dao.DevpDevFunaModuleSpecification;
import net.aicoder.devp.business.sys.domain.DevpDevFunaModule;
import net.aicoder.devp.business.sys.dto.DevpDevFunaModuleCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpDevFunaModuleService")
public class DevpDevFunaModuleService  extends GenericCrudService<DevpDevFunaModule, Long, DevpDevFunaModuleCondition, DevpDevFunaModuleDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpDevFunaModuleService.class);

	@Override
	public Specification<DevpDevFunaModule> getSpecification(DevpDevFunaModuleCondition condition) {
		return new DevpDevFunaModuleSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpDevFunaModule.PROPERTY_TID);
		return sort;
	}
}