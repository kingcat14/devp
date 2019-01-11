package com.yunkang.saas.platform.monitor.business.indicator.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.indicator.dao.IndicatorValueDao;
import com.yunkang.saas.platform.monitor.business.indicator.dao.IndicatorValueSpecification;
import com.yunkang.saas.platform.monitor.business.indicator.domain.IndicatorValue;
import com.yunkang.saas.platform.monitor.business.indicator.dto.IndicatorValueCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;


@Service("indicatorValueService")
@Slf4j
public class IndicatorValueService  extends GenericCrudService<IndicatorValue, String, IndicatorValueCondition, IndicatorValueDao> {

	public void add(IndicatorValue value){
		value.setId(Md5Crypt.apr1Crypt((value.getIndicator()+value.getCollectTime()).getBytes(StandardCharsets.UTF_8), "1"));
		dao.save(value);
	}

	@Override
	public Specification<IndicatorValue> getSpecification(IndicatorValueCondition condition) {
		return new IndicatorValueSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, IndicatorValue.PROPERTY_INDICATOR);
		return sort;
	}
}