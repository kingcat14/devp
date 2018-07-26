package net.aicoder.devp.deploy.business.deploy.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.deploy.business.deploy.dao.DevpSysDpyResourcesDao;
import net.aicoder.devp.deploy.business.deploy.dao.DevpSysDpyResourcesSpecification;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyResources;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResourcesService")
public class DevpSysDpyResourcesService  extends CrudService<DevpSysDpyResources, DevpSysDpyResourcesCondition, DevpSysDpyResourcesDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesService.class);

	@Override
	public Specification<DevpSysDpyResources> getSpecification(DevpSysDpyResourcesCondition condition) {
		return new DevpSysDpyResourcesSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysDpyResources.PROPERTY_TID);
		return sort;
	}
}