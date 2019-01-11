package com.yunkang.saas.platform.monitor.business.supporter.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.supporter.dao.SupporterDao;
import com.yunkang.saas.platform.monitor.business.supporter.dao.SupporterSpecification;
import com.yunkang.saas.platform.monitor.business.supporter.domain.Supporter;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("supporterService")
@Slf4j
public class SupporterService  extends GenericCrudService<Supporter, String, SupporterCondition, SupporterDao> {

	@Override
	public Specification<Supporter> getSpecification(SupporterCondition condition) {
		return new SupporterSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, Supporter.PROPERTY_MOBILE, Supporter.PROPERTY_EMAIL);

		return sort;
	}
}