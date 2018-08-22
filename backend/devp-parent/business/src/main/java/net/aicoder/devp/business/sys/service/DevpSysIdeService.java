package net.aicoder.devp.business.sys.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.sys.dao.DevpSysIdeDao;
import net.aicoder.devp.business.sys.dao.DevpSysIdeSpecification;
import net.aicoder.devp.business.sys.domain.DevpSysIde;
import net.aicoder.devp.business.sys.dto.DevpSysIdeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysIdeService")
public class DevpSysIdeService  extends GenericCrudService<DevpSysIde, Long, DevpSysIdeCondition, DevpSysIdeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysIdeService.class);

	@Override
	public Specification<DevpSysIde> getSpecification(DevpSysIdeCondition condition) {
		return new DevpSysIdeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysIde.PROPERTY_TID);
		return sort;
	}
}