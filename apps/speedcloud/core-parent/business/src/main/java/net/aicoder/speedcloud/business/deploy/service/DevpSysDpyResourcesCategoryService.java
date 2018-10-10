package net.aicoder.speedcloud.business.deploy.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourcesCategoryDao;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourcesCategorySpecification;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourcesCategory;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResourcesCategoryService")
public class DevpSysDpyResourcesCategoryService  extends GenericCrudService<DevpSysDpyResourcesCategory, Long, DevpSysDpyResourcesCategoryCondition, DevpSysDpyResourcesCategoryDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesCategoryService.class);

	@Override
	public Specification<DevpSysDpyResourcesCategory> getSpecification(DevpSysDpyResourcesCategoryCondition condition) {
		return new DevpSysDpyResourcesCategorySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysDpyResourcesCategory.PROPERTY_NAME);
		return sort;
	}
}