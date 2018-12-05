package net.aicoder.speedcloud.console.business.speedcloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryCondition;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryEditDto;
import net.aicoder.speedcloud.business.app.vo.CodeRepositoryVO;
import net.aicoder.speedcloud.client.app.CodeRepositoryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("codeRepositoryRibbonService")
public class CodeRepositoryRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepositoryRibbonService.class);


	@Autowired
	private CodeRepositoryClient codeRepositoryClient;


	public CodeRepositoryVO add(CodeRepositoryAddDto addDto){
		RestResponse<CodeRepositoryVO> result = codeRepositoryClient.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		RestResponse<CodeRepositoryVO> result = codeRepositoryClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public CodeRepositoryVO merge(String id, CodeRepositoryEditDto editDto){
		RestResponse<CodeRepositoryVO> result = codeRepositoryClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public CodeRepositoryVO find(String id){
		RestResponse<CodeRepositoryVO> result = codeRepositoryClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<CodeRepositoryVO> list(PageSearchRequest<CodeRepositoryCondition> pageSearchRequest) {
		RestResponse<PageContent<CodeRepositoryVO>> result = codeRepositoryClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
