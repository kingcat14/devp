package com.yunkang.saas.bootstrap.common.business.simpleconfig.service;


import com.yunkang.saas.bootstrap.common.business.simpleconfig.dao.SimpleConfigTypeDao;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.dao.SimpleConfigTypeSpecification;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfigType;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.dto.SimpleConfigTypeCondition;
import com.yunkang.saas.common.jpa.GenericCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("simpleConfigTypeService")
public class SimpleConfigTypeService  extends GenericCrudService<SimpleConfigType, Long, SimpleConfigTypeCondition, SimpleConfigTypeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleConfigTypeService.class);

	@Override
	public Specification<SimpleConfigType> getSpecification(SimpleConfigTypeCondition condition) {
		return new SimpleConfigTypeSpecification(condition);
	}

	public SimpleConfigType findByCode(String code){

		return dao.findByTypeCode(code);

	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , SimpleConfigType.PROPERTY_TYPE_NAME);
		return sort;
	}
}