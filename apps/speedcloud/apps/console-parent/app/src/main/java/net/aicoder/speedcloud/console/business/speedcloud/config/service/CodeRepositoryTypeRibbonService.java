package net.aicoder.speedcloud.console.business.speedcloud.config.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeCondition;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeEditDto;
import net.aicoder.speedcloud.business.config.vo.CodeRepositoryTypeVO;
import net.aicoder.speedcloud.client.config.CodeRepositoryTypeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("codeRepositoryTypeRibbonService")
public class CodeRepositoryTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepositoryTypeRibbonService.class);


	@Autowired
	private CodeRepositoryTypeClient codeRepositoryTypeClient;


	public CodeRepositoryTypeVO add(CodeRepositoryTypeAddDto addDto){
		RestResponse<CodeRepositoryTypeVO> result = codeRepositoryTypeClient.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		RestResponse<CodeRepositoryTypeVO> result = codeRepositoryTypeClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
	}
	public CodeRepositoryTypeVO merge(String id, CodeRepositoryTypeEditDto editDto){
		RestResponse<CodeRepositoryTypeVO> result = codeRepositoryTypeClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public CodeRepositoryTypeVO find(String id){
		RestResponse<CodeRepositoryTypeVO> result = codeRepositoryTypeClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<CodeRepositoryTypeVO> list(PageSearchRequest<CodeRepositoryTypeCondition> pageSearchRequest) {
		RestResponse<PageContent<CodeRepositoryTypeVO>> result = codeRepositoryTypeClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
