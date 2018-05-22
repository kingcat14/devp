package net.aicoder.devp.product.business.product.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.product.dao.DevpSysExtcmpDao;
import net.aicoder.devp.product.business.product.dao.DevpSysExtcmpSpecification;
import net.aicoder.devp.product.business.product.domain.DevpSysExtcmp;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysExtcmpService")
public class DevpSysExtcmpService  extends CrudService<DevpSysExtcmp, DevpSysExtcmpCondition, DevpSysExtcmpDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysExtcmp.class);

	@Override
	public Specification<DevpSysExtcmp> getSpecification(DevpSysExtcmpCondition condition) {
		return new DevpSysExtcmpSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysExtcmp.PROPERTY_TID);
		return sort;
	}
}