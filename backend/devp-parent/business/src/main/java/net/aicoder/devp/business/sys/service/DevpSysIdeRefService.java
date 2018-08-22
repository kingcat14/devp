package net.aicoder.devp.business.sys.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.sys.dao.DevpSysIdeRefDao;
import net.aicoder.devp.business.sys.dao.DevpSysIdeRefSpecification;
import net.aicoder.devp.business.sys.domain.DevpSysIdeRef;
import net.aicoder.devp.business.sys.dto.DevpSysIdeRefCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysIdeRefService")
public class DevpSysIdeRefService  extends GenericCrudService<DevpSysIdeRef, Long, DevpSysIdeRefCondition, DevpSysIdeRefDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysIdeRefService.class);

	@Override
	public Specification<DevpSysIdeRef> getSpecification(DevpSysIdeRefCondition condition) {
		return new DevpSysIdeRefSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysIdeRef.PROPERTY_TID);
		return sort;
	}
}