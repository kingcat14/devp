package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.app.dao.CodeRepertoryDao;
import net.aicoder.speedcloud.business.app.dao.CodeRepertorySpecification;
import net.aicoder.speedcloud.business.app.domain.CodeRepertory;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("codeRepertoryService")
public class CodeRepertoryService  extends GenericCrudService<CodeRepertory, Long, CodeRepertoryCondition, CodeRepertoryDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepertoryService.class);

	@Override
	public Specification<CodeRepertory> getSpecification(CodeRepertoryCondition condition) {
		return new CodeRepertorySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, CodeRepertory.PROPERTY_TYPE);
		return sort;
	}
}