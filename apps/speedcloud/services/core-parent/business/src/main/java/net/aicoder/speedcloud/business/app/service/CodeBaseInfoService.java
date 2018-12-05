package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.app.dao.CodeBaseInfoDao;
import net.aicoder.speedcloud.business.app.dao.CodeBaseInfoSpecification;
import net.aicoder.speedcloud.business.app.domain.CodeBaseInfo;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("codeBaseInfoService")
@Slf4j
public class CodeBaseInfoService  extends GenericCrudService<CodeBaseInfo, String, CodeBaseInfoCondition, CodeBaseInfoDao> {

	@Override
	public Specification<CodeBaseInfo> getSpecification(CodeBaseInfoCondition condition) {
		return new CodeBaseInfoSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, CodeBaseInfo.PROPERTY_MODIFY_AT);

		return sort;
	}
}