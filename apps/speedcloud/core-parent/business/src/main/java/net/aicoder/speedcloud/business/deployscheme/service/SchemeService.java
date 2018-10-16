package net.aicoder.speedcloud.business.deployscheme.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.dao.SchemeDao;
import net.aicoder.speedcloud.business.deployscheme.dao.SchemeSpecification;
import net.aicoder.speedcloud.business.deployscheme.domain.Scheme;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("schemeService")
public class SchemeService  extends GenericCrudService<Scheme, Long, SchemeCondition, SchemeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchemeService.class);

	@Override
	public Specification<Scheme> getSpecification(SchemeCondition condition) {
		return new SchemeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Scheme.PROPERTY_NAME);
		return sort;
	}
}