package net.aicoder.devp.business.product.service;


import com.yunkang.saas.common.jpa.CrudService;

import net.aicoder.devp.business.product.dao.DevpPrdPrdlineDao;
import net.aicoder.devp.business.product.dao.DevpPrdPrdlineSpecification;
import net.aicoder.devp.business.product.domain.DevpPrdPrdline;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineCondition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpPrdPrdlineService")
public class DevpPrdPrdlineService  extends CrudService<DevpPrdPrdline, DevpPrdPrdlineCondition, DevpPrdPrdlineDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPrdlineService.class);

	@Override
	public Specification<DevpPrdPrdline> getSpecification(DevpPrdPrdlineCondition condition) {
		return new DevpPrdPrdlineSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpPrdPrdline.PROPERTY_CODE);
		return sort;
	}
}