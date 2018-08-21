package net.aicoder.devp.product.business.sys.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.sys.dao.DevpSysCmpModuleDao;
import net.aicoder.devp.product.business.sys.dao.DevpSysCmpModuleSpecification;
import net.aicoder.devp.product.business.sys.domain.DevpSysCmpModule;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpModuleCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysCmpModuleService")
public class DevpSysCmpModuleService  extends CrudService<DevpSysCmpModule, DevpSysCmpModuleCondition, DevpSysCmpModuleDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysCmpModuleService.class);

	@Override
	public Specification<DevpSysCmpModule> getSpecification(DevpSysCmpModuleCondition condition) {
		return new DevpSysCmpModuleSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysCmpModule.PROPERTY_TID);
		return sort;
	}
}