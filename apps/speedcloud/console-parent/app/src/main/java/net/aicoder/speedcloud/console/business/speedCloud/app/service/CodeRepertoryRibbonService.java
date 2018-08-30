package net.aicoder.speedcloud.console.business.speedCloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.app.CodeRepertoryRibbon;
import net.aicoder.speedcloud.client.app.result.CodeRepertoryPageResult;
import net.aicoder.speedcloud.client.app.result.CodeRepertoryResult;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryCondition;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryEditDto;
import net.aicoder.speedcloud.business.app.vo.CodeRepertoryVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("codeRepertoryRibbonService")
public class CodeRepertoryRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepertoryRibbonService.class);


	@Autowired
	private CodeRepertoryRibbon codeRepertoryRibbon;


	public CodeRepertoryVO add(CodeRepertoryAddDto addDto){
		CodeRepertoryResult result = codeRepertoryRibbon.add(addDto);

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
		CodeRepertoryResult result = codeRepertoryRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public CodeRepertoryVO merge(Long id, CodeRepertoryEditDto editDto){
		CodeRepertoryResult result = codeRepertoryRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public CodeRepertoryVO find(Long id){
		CodeRepertoryResult result = codeRepertoryRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<CodeRepertoryVO> list(PageSearchRequest<CodeRepertoryCondition> pageSearchRequest) {
		CodeRepertoryPageResult result = codeRepertoryRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
