package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskHostDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskHostSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskHost;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskHostService")
public class DevpSysOpsTaskHostService  extends GenericCrudService<DevpSysOpsTaskHost, Long, DevpSysOpsTaskHostCondition, DevpSysOpsTaskHostDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskHostService.class);

	@Override
	public Specification<DevpSysOpsTaskHost> getSpecification(DevpSysOpsTaskHostCondition condition) {
		return new DevpSysOpsTaskHostSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsTaskHost.PROPERTY_TID);
		return sort;
	}
}