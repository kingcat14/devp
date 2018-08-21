package net.aicoder.devp.product.business.product.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.product.dao.DevpPrdLinePrdDao;
import net.aicoder.devp.product.business.product.dao.DevpPrdLinePrdSpecification;
import net.aicoder.devp.product.business.product.domain.DevpPrdLinePrd;
import net.aicoder.devp.product.business.product.dto.DevpPrdLinePrdCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpPrdLinePrdService")
public class DevpPrdLinePrdService  extends CrudService<DevpPrdLinePrd, DevpPrdLinePrdCondition, DevpPrdLinePrdDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdLinePrdService.class);

	@Override
	public Specification<DevpPrdLinePrd> getSpecification(DevpPrdLinePrdCondition condition) {
		return new DevpPrdLinePrdSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpPrdLinePrd.PROPERTY_TID);
		return sort;
	}
}