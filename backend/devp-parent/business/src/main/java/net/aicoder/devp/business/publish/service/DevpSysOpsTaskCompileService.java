package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskCompileDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskCompileSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskCompile;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskCompileService")
public class DevpSysOpsTaskCompileService  extends GenericCrudService<DevpSysOpsTaskCompile, Long, DevpSysOpsTaskCompileCondition, DevpSysOpsTaskCompileDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskCompileService.class);

	@Override
	public Specification<DevpSysOpsTaskCompile> getSpecification(DevpSysOpsTaskCompileCondition condition) {
		return new DevpSysOpsTaskCompileSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsTaskCompile.PROPERTY_TID);
		return sort;
	}
}