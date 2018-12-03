package com.yunkang.saas.platform.authentication.business.platform.tenant.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dao.AppTenantRelationDao;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dao.AppTenantRelationSpecification;
import com.yunkang.saas.platform.authentication.business.platform.tenant.domain.AppTenantRelation;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.AppTenantRelationCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appTenantRelationService")
@Slf4j
public class AppTenantRelationService  extends GenericCrudService<AppTenantRelation, Long, AppTenantRelationCondition, AppTenantRelationDao> {

	@Override
	public Specification<AppTenantRelation> getSpecification(AppTenantRelationCondition condition) {
		return new AppTenantRelationSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC);

		return sort;
	}
}