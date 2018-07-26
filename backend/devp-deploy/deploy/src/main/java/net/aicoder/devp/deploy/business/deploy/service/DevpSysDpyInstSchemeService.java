package net.aicoder.devp.deploy.business.deploy.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.deploy.business.deploy.dao.DevpSysDpyInstSchemeDao;
import net.aicoder.devp.deploy.business.deploy.dao.DevpSysDpyInstSchemeSpecification;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyInstScheme;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyInstSchemeService")
public class DevpSysDpyInstSchemeService  extends CrudService<DevpSysDpyInstScheme, DevpSysDpyInstSchemeCondition, DevpSysDpyInstSchemeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyInstSchemeService.class);

	@Override
	public Specification<DevpSysDpyInstScheme> getSpecification(DevpSysDpyInstSchemeCondition condition) {
		return new DevpSysDpyInstSchemeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysDpyInstScheme.PROPERTY_TID);
		return sort;
	}
}