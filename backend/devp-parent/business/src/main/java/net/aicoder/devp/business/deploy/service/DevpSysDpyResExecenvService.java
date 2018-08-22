package net.aicoder.devp.business.deploy.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.deploy.dao.DevpSysDpyResExecenvDao;
import net.aicoder.devp.business.deploy.dao.DevpSysDpyResExecenvSpecification;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyResExecenv;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResExecenvCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResExecenvService")
public class DevpSysDpyResExecenvService  extends GenericCrudService<DevpSysDpyResExecenv, Long, DevpSysDpyResExecenvCondition, DevpSysDpyResExecenvDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResExecenvService.class);

	@Override
	public Specification<DevpSysDpyResExecenv> getSpecification(DevpSysDpyResExecenvCondition condition) {
		return new DevpSysDpyResExecenvSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysDpyResExecenv.PROPERTY_TID);
		return sort;
	}
}