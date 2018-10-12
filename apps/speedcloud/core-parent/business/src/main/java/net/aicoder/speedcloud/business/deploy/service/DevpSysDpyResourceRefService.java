package net.aicoder.speedcloud.business.deploy.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourceRefDao;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourceRefSpecification;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceRef;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResources;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("devpSysDpyResourceRefService")
public class DevpSysDpyResourceRefService  extends GenericCrudService<DevpSysDpyResourceRef, Long, DevpSysDpyResourceRefCondition, DevpSysDpyResourceRefDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourceRefService.class);

	@Autowired
	private DevpSysDpyResourcesService devpSysDpyResourcesService;


	@Transactional
	public void add(DevpSysDpyResourceRef relation){
		relation.fillId();

		DevpSysDpyResources resources = devpSysDpyResourcesService.find(relation.getResource());
		relation.setScheme(resources.getScheme());

		dao.save(relation);
	}

	@Override
	public Specification<DevpSysDpyResourceRef> getSpecification(DevpSysDpyResourceRefCondition condition) {
		return new DevpSysDpyResourceRefSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysDpyResourceRef.PROPERTY_RESOURCE);
		return sort;
	}
}