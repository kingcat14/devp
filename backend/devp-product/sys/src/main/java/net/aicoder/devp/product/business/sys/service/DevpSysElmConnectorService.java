package net.aicoder.devp.product.business.sys.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.sys.dao.DevpSysElmConnectorDao;
import net.aicoder.devp.product.business.sys.dao.DevpSysElmConnectorSpecification;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmConnector;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysElmConnectorService")
public class DevpSysElmConnectorService  extends CrudService<DevpSysElmConnector, DevpSysElmConnectorCondition, DevpSysElmConnectorDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmConnectorService.class);

	@Override
	public Specification<DevpSysElmConnector> getSpecification(DevpSysElmConnectorCondition condition) {
		return new DevpSysElmConnectorSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysElmConnector.PROPERTY_TID);
		return sort;
	}
}