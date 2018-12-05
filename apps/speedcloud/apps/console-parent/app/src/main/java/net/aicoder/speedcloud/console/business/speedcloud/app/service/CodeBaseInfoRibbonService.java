package net.aicoder.speedcloud.console.business.speedcloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoEditDto;
import net.aicoder.speedcloud.business.app.vo.CodeBaseInfoVO;
import net.aicoder.speedcloud.client.app.CodeBaseInfoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("codeBaseInfoRibbonService")
public class CodeBaseInfoRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeBaseInfoRibbonService.class);


	@Autowired
	private CodeBaseInfoClient codeBaseInfoClient;


	public CodeBaseInfoVO add(CodeBaseInfoAddDto addDto){
		RestResponse<CodeBaseInfoVO> result = codeBaseInfoClient.add(addDto);

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
		RestResponse<CodeBaseInfoVO> result = codeBaseInfoClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public CodeBaseInfoVO merge(String id, CodeBaseInfoEditDto editDto){
		RestResponse<CodeBaseInfoVO> result = codeBaseInfoClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public CodeBaseInfoVO find(String id){
		RestResponse<CodeBaseInfoVO> result = codeBaseInfoClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<CodeBaseInfoVO> list(PageSearchRequest<CodeBaseInfoCondition> pageSearchRequest) {
		RestResponse<PageContent<CodeBaseInfoVO>> result = codeBaseInfoClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
