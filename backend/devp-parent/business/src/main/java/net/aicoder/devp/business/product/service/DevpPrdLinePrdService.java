package net.aicoder.devp.business.product.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.product.dao.DevpPrdLinePrdDao;
import net.aicoder.devp.business.product.dao.DevpPrdLinePrdSpecification;
import net.aicoder.devp.business.product.domain.DevpPrdLinePrd;
import net.aicoder.devp.business.product.dto.DevpPrdLinePrdCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpPrdLinePrdService")
public class DevpPrdLinePrdService  extends GenericCrudService<DevpPrdLinePrd, Long, DevpPrdLinePrdCondition, DevpPrdLinePrdDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdLinePrdService.class);

	@Override
	public Specification<DevpPrdLinePrd> getSpecification(DevpPrdLinePrdCondition condition) {
		return new DevpPrdLinePrdSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpPrdLinePrd.PROPERTY_TID);
		return sort;
	}
}