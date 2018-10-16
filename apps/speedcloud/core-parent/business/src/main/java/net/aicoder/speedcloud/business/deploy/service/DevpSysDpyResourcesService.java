package net.aicoder.speedcloud.business.deploy.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourcesDao;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourcesSpecification;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResources;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("devpSysDpyResourcesService")
public class DevpSysDpyResourcesService  extends GenericCrudService<DevpSysDpyResources, Long, DevpSysDpyResourcesCondition, DevpSysDpyResourcesDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesService.class);

	@Autowired
	private DevpSysDpyResourceRefService devpSysDpyResourceRefService;

	@Transactional
	public void delete(Long id) {
		if (null == id) {
			this.LOGGER.warn("try delete T by empty id. Code need check");
		} else {
			this.LOGGER.debug("delete t:{}", id);
			this.dao.delete(id);
			devpSysDpyResourceRefService.deleteByResource(id);
			devpSysDpyResourceRefService.deleteByRefResource(id);
		}
	}

	@Override
	public Specification<DevpSysDpyResources> getSpecification(DevpSysDpyResourcesCondition condition) {
		return new DevpSysDpyResourcesSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC, DevpSysDpyResources.PROPERTY_SEQ);
		return sort;
	}
}