package net.aicoder.devp.product.business.sys.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.sys.dao.DevpSysElmInstanceDao;
import net.aicoder.devp.product.business.sys.dao.DevpSysElmInstanceSpecification;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmInstance;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysElmInstanceService")
public class DevpSysElmInstanceService  extends CrudService<DevpSysElmInstance, DevpSysElmInstanceCondition, DevpSysElmInstanceDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInstanceService.class);

	@Override
	public Specification<DevpSysElmInstance> getSpecification(DevpSysElmInstanceCondition condition) {
		return new DevpSysElmInstanceSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysElmInstance.PROPERTY_TID);
		return sort;
	}
}