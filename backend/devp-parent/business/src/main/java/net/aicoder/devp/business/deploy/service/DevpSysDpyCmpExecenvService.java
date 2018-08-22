package net.aicoder.devp.business.deploy.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.deploy.dao.DevpSysDpyCmpExecenvDao;
import net.aicoder.devp.business.deploy.dao.DevpSysDpyCmpExecenvSpecification;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyCmpExecenv;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpExecenvCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyCmpExecenvService")
public class DevpSysDpyCmpExecenvService  extends GenericCrudService<DevpSysDpyCmpExecenv, Long, DevpSysDpyCmpExecenvCondition, DevpSysDpyCmpExecenvDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyCmpExecenvService.class);

	@Override
	public Specification<DevpSysDpyCmpExecenv> getSpecification(DevpSysDpyCmpExecenvCondition condition) {
		return new DevpSysDpyCmpExecenvSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysDpyCmpExecenv.PROPERTY_TID);
		return sort;
	}
}