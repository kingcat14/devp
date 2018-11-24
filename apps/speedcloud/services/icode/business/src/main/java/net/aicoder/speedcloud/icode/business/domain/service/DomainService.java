package net.aicoder.speedcloud.icode.business.domain.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.dao.DomainDao;
import net.aicoder.speedcloud.icode.business.domain.dao.DomainSpecification;
import net.aicoder.speedcloud.icode.business.domain.domain.Domain;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("domainService")
@Slf4j
public class DomainService  extends GenericCrudService<Domain, String, DomainCondition, DomainDao> {

	@Override
	public Specification<Domain> getSpecification(DomainCondition condition) {
		return new DomainSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Domain.PROPERTY_NAME);
		return sort;
	}
}