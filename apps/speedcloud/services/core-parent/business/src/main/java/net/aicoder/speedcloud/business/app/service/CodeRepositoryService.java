package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.app.dao.CodeRepositoryDao;
import net.aicoder.speedcloud.business.app.dao.CodeRepositorySpecification;
import net.aicoder.speedcloud.business.app.domain.CodeRepository;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("codeRepositoryService")
@Slf4j
public class CodeRepositoryService  extends GenericCrudService<CodeRepository, String, CodeRepositoryCondition, CodeRepositoryDao> {

	/**
	 * APP_ID
	 * @param appId
	 * @return
	 */
	public CodeRepository findByApp(String appId){
		return dao.findByApp(appId);
	}

	@Override
	public Specification<CodeRepository> getSpecification(CodeRepositoryCondition condition) {
		return new CodeRepositorySpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, CodeRepository.PROPERTY_MODIFY_AT);

		return sort;
	}
}