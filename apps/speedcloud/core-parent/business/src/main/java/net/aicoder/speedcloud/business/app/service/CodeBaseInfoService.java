package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.app.dao.CodeBaseInfoDao;
import net.aicoder.speedcloud.business.app.dao.CodeBaseInfoSpecification;
import net.aicoder.speedcloud.business.app.domain.CodeBaseInfo;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("codeBaseInfoService")
public class CodeBaseInfoService  extends GenericCrudService<CodeBaseInfo, Long, CodeBaseInfoCondition, CodeBaseInfoDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeBaseInfoService.class);

	@Override
	public Specification<CodeBaseInfo> getSpecification(CodeBaseInfoCondition condition) {
		return new CodeBaseInfoSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, CodeBaseInfo.PROPERTY_TID);
		return sort;
	}
}