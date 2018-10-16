package net.aicoder.speedcloud.console.business.speedcloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.app.CodeBaseInfoRibbon;
import net.aicoder.speedcloud.client.app.result.CodeBaseInfoPageResult;
import net.aicoder.speedcloud.client.app.result.CodeBaseInfoResult;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoEditDto;
import net.aicoder.speedcloud.business.app.vo.CodeBaseInfoVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("codeBaseInfoRibbonService")
public class CodeBaseInfoRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeBaseInfoRibbonService.class);


	@Autowired
	private CodeBaseInfoRibbon codeBaseInfoRibbon;


	public CodeBaseInfoVO add(CodeBaseInfoAddDto addDto){
		CodeBaseInfoResult result = codeBaseInfoRibbon.add(addDto);

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
		CodeBaseInfoResult result = codeBaseInfoRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public CodeBaseInfoVO merge(Long id, CodeBaseInfoEditDto editDto){
		CodeBaseInfoResult result = codeBaseInfoRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public CodeBaseInfoVO find(Long id){
		CodeBaseInfoResult result = codeBaseInfoRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<CodeBaseInfoVO> list(PageSearchRequest<CodeBaseInfoCondition> pageSearchRequest) {
		CodeBaseInfoPageResult result = codeBaseInfoRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
