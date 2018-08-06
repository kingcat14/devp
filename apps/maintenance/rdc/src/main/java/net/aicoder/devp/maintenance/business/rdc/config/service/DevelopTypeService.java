package net.aicoder.devp.maintenance.business.rdc.config.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.maintenance.business.rdc.config.dao.DevelopTypeDao;
import net.aicoder.devp.maintenance.business.rdc.config.dao.DevelopTypeSpecification;
import net.aicoder.devp.maintenance.business.rdc.config.domain.DevelopType;
import net.aicoder.devp.maintenance.business.rdc.config.dto.DevelopTypeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("developTypeService")
public class DevelopTypeService  extends GenericCrudService<DevelopType, String, DevelopTypeCondition, DevelopTypeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevelopTypeService.class);

	@Override
	public Specification<DevelopType> getSpecification(DevelopTypeCondition condition) {
		return new DevelopTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevelopType.PROPERTY_NAME);
		return sort;
	}
}