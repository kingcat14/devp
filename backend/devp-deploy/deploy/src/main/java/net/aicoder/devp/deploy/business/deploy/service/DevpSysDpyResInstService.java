package net.aicoder.devp.deploy.business.deploy.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.deploy.business.deploy.dao.DevpSysDpyResInstDao;
import net.aicoder.devp.deploy.business.deploy.dao.DevpSysDpyResInstSpecification;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyResInst;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResInstService")
public class DevpSysDpyResInstService  extends CrudService<DevpSysDpyResInst, DevpSysDpyResInstCondition, DevpSysDpyResInstDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResInstService.class);

	@Override
	public Specification<DevpSysDpyResInst> getSpecification(DevpSysDpyResInstCondition condition) {
		return new DevpSysDpyResInstSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysDpyResInst.PROPERTY_TID);
		return sort;
	}
}