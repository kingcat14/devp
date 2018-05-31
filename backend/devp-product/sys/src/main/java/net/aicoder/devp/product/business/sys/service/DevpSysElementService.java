package net.aicoder.devp.product.business.sys.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.sys.dao.DevpSysElementDao;
import net.aicoder.devp.product.business.sys.dao.DevpSysElementSpecification;
import net.aicoder.devp.product.business.sys.domain.DevpSysElement;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysElementService")
public class DevpSysElementService  extends CrudService<DevpSysElement, DevpSysElementCondition, DevpSysElementDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElementService.class);

	@Override
	public Specification<DevpSysElement> getSpecification(DevpSysElementCondition condition) {
		return new DevpSysElementSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysElement.PROPERTY_TID);
		return sort;
	}
}