package net.aicoder.devp.product.business.sys.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.sys.dao.DevpSysDgmElementDao;
import net.aicoder.devp.product.business.sys.dao.DevpSysDgmElementSpecification;
import net.aicoder.devp.product.business.sys.domain.DevpSysDgmElement;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDgmElementService")
public class DevpSysDgmElementService  extends CrudService<DevpSysDgmElement, DevpSysDgmElementCondition, DevpSysDgmElementDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDgmElementService.class);

	@Override
	public Specification<DevpSysDgmElement> getSpecification(DevpSysDgmElementCondition condition) {
		return new DevpSysDgmElementSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysDgmElement.PROPERTY_TID);
		return sort;
	}
}