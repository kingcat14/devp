package net.aicoder.speedcloud.business.config.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.config.dao.EnvLevelDao;
import net.aicoder.speedcloud.business.config.dao.EnvLevelSpecification;
import net.aicoder.speedcloud.business.config.domain.EnvLevel;
import net.aicoder.speedcloud.business.config.dto.EnvLevelCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("envLevelService")
@Slf4j
public class EnvLevelService  extends GenericCrudService<EnvLevel, String, EnvLevelCondition, EnvLevelDao> {

	@Override
	public Specification<EnvLevel> getSpecification(EnvLevelCondition condition) {
		return new EnvLevelSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, EnvLevel.PROPERTY_MODIFY_AT);

		return sort;
	}
}