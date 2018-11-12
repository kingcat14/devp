package com.yunkang.saas.platform.monitor.business.indicator.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.indicator.dao.IndicatorDao;
import com.yunkang.saas.platform.monitor.business.indicator.dao.IndicatorSpecification;
import com.yunkang.saas.platform.monitor.business.indicator.domain.Indicator;
import com.yunkang.saas.platform.monitor.business.indicator.dto.IndicatorCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("indicatorService")
public class IndicatorService  extends GenericCrudService<Indicator, String, IndicatorCondition, IndicatorDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorService.class);

	@Override
	public Specification<Indicator> getSpecification(IndicatorCondition condition) {
		return new IndicatorSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Indicator.PROPERTY_CODE);
		return sort;
	}
}