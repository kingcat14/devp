package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.app.dao.CodeRepositoryDao;
import net.aicoder.speedcloud.business.app.dao.CodeRepositorySpecification;
import net.aicoder.speedcloud.business.app.domain.CodeRepository;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("codeRepositoryService")
public class CodeRepositoryService  extends GenericCrudService<CodeRepository, Long, CodeRepositoryCondition, CodeRepositoryDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepositoryService.class);

	@Override
	public Specification<CodeRepository> getSpecification(CodeRepositoryCondition condition) {
		return new CodeRepositorySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, CodeRepository.PROPERTY_NAME);
		return sort;
	}
}