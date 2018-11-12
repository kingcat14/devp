package com.yunkang.saas.platform.monitor.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.app.dao.UnknownAppDao;
import com.yunkang.saas.platform.monitor.business.app.dao.UnknownAppSpecification;
import com.yunkang.saas.platform.monitor.business.app.domain.UnknownApp;
import com.yunkang.saas.platform.monitor.business.app.dto.UnknownAppCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("unknownAppService")
public class UnknownAppService  extends GenericCrudService<UnknownApp, String, UnknownAppCondition, UnknownAppDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UnknownAppService.class);

	@Override
	public Specification<UnknownApp> getSpecification(UnknownAppCondition condition) {
		return new UnknownAppSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, UnknownApp.PROPERTY_CODE);
		return sort;
	}
}