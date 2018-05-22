package net.aicoder.devp.product.business.product.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.product.dao.DevpPrdPersonDao;
import net.aicoder.devp.product.business.product.dao.DevpPrdPersonSpecification;
import net.aicoder.devp.product.business.product.domain.DevpPrdPerson;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpPrdPersonService")
public class DevpPrdPersonService  extends CrudService<DevpPrdPerson, DevpPrdPersonCondition, DevpPrdPersonDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPerson.class);

	@Override
	public Specification<DevpPrdPerson> getSpecification(DevpPrdPersonCondition condition) {
		return new DevpPrdPersonSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpPrdPerson.PROPERTY_TID);
		return sort;
	}
}