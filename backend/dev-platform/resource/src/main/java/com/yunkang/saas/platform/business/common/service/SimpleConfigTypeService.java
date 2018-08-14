package com.yunkang.saas.platform.business.common.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.business.common.dao.SimpleConfigTypeDao;
import com.yunkang.saas.platform.business.common.dao.SimpleConfigTypeSpecification;
import com.yunkang.saas.platform.business.common.domain.SimpleConfigType;
import com.yunkang.saas.platform.business.common.dto.SimpleConfigTypeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , SimpleConfigType.PROPERTY_TYPE_NAME);
		return sort;
	}
}