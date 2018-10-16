package net.aicoder.speedcloud.console.business.speedcloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.app.CodeRepositoryRibbon;
import net.aicoder.speedcloud.client.app.result.CodeRepositoryPageResult;
import net.aicoder.speedcloud.client.app.result.CodeRepositoryResult;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryCondition;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryEditDto;
import net.aicoder.speedcloud.business.app.vo.CodeRepositoryVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("codeRepositoryRibbonService")
public class CodeRepositoryRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepositoryRibbonService.class);


	@Autowired
	private CodeRepositoryRibbon codeRepositoryRibbon;


	public CodeRepositoryVO add(CodeRepositoryAddDto addDto){
		CodeRepositoryResult result = codeRepositoryRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		CodeRepositoryResult result = codeRepositoryRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public CodeRepositoryVO merge(Long id, CodeRepositoryEditDto editDto){
		CodeRepositoryResult result = codeRepositoryRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public CodeRepositoryVO find(Long id){
		CodeRepositoryResult result = codeRepositoryRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<CodeRepositoryVO> list(PageSearchRequest<CodeRepositoryCondition> pageSearchRequest) {
		CodeRepositoryPageResult result = codeRepositoryRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
