package com.yunkang.saas.platform.monitor.business.supporter.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.supporter.dao.SupporterAppRelationDao;
import com.yunkang.saas.platform.monitor.business.supporter.dao.SupporterAppRelationSpecification;
import com.yunkang.saas.platform.monitor.business.supporter.domain.SupporterAppRelation;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterAppRelationCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("supporterAppRelationService")
@Slf4j
public class SupporterAppRelationService  extends GenericCrudService<SupporterAppRelation, String, SupporterAppRelationCondition, SupporterAppRelationDao> {

	public List<SupporterAppRelation> findByApplication(String application){
		return dao.findByApplication(application);
	}

	@Override
	public Specification<SupporterAppRelation> getSpecification(SupporterAppRelationCondition condition) {
		return new SupporterAppRelationSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, SupporterAppRelation.PROPERTY_SUPPORTER, SupporterAppRelation.PROPERTY_APPLICATION);

		return sort;
	}
}