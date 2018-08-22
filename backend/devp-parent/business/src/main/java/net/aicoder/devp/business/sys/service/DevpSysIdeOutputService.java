package net.aicoder.devp.business.sys.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.sys.dao.DevpSysIdeOutputDao;
import net.aicoder.devp.business.sys.dao.DevpSysIdeOutputSpecification;
import net.aicoder.devp.business.sys.domain.DevpSysIdeOutput;
import net.aicoder.devp.business.sys.dto.DevpSysIdeOutputCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysIdeOutputService")
public class DevpSysIdeOutputService  extends GenericCrudService<DevpSysIdeOutput, Long, DevpSysIdeOutputCondition, DevpSysIdeOutputDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysIdeOutputService.class);

	@Override
	public Specification<DevpSysIdeOutput> getSpecification(DevpSysIdeOutputCondition condition) {
		return new DevpSysIdeOutputSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysIdeOutput.PROPERTY_TID);
		return sort;
	}
}