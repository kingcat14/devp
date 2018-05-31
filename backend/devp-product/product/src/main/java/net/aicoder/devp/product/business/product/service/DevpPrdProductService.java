package net.aicoder.devp.product.business.product.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.product.dao.DevpPrdProductDao;
import net.aicoder.devp.product.business.product.dao.DevpPrdProductSpecification;
import net.aicoder.devp.product.business.product.domain.DevpPrdProduct;
import net.aicoder.devp.product.business.product.dto.DevpPrdProductCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpPrdProductService")
public class DevpPrdProductService  extends CrudService<DevpPrdProduct, DevpPrdProductCondition, DevpPrdProductDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdProductService.class);

	@Override
	public Specification<DevpPrdProduct> getSpecification(DevpPrdProductCondition condition) {
		return new DevpPrdProductSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpPrdProduct.PROPERTY_TID);
		return sort;
	}
}