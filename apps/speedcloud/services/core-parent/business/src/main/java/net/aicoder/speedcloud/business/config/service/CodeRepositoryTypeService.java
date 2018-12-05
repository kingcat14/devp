package net.aicoder.speedcloud.business.config.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.config.dao.CodeRepositoryTypeDao;
import net.aicoder.speedcloud.business.config.dao.CodeRepositoryTypeSpecification;
import net.aicoder.speedcloud.business.config.domain.CodeRepositoryType;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("codeRepositoryTypeService")
@Slf4j
public class CodeRepositoryTypeService  extends GenericCrudService<CodeRepositoryType, String, CodeRepositoryTypeCondition, CodeRepositoryTypeDao> {

	@Transactional
	public void add(CodeRepositoryType t){
		t.setId(t.getCode());
		dao.save(t);
	}

	@Override
	public Specification<CodeRepositoryType> getSpecification(CodeRepositoryTypeCondition condition) {
		return new CodeRepositoryTypeSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, CodeRepositoryType.PROPERTY_CODE, CodeRepositoryType.PROPERTY_MODIFY_AT);

		return sort;
	}
}