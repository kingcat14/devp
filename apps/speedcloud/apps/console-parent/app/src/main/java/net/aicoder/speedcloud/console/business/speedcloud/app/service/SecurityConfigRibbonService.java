package net.aicoder.speedcloud.console.business.speedcloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigCondition;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigEditDto;
import net.aicoder.speedcloud.business.app.vo.SecurityConfigVO;
import net.aicoder.speedcloud.client.app.SecurityConfigRibbon;
import net.aicoder.speedcloud.client.app.result.SecurityConfigPageResult;
import net.aicoder.speedcloud.client.app.result.SecurityConfigResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("securityConfigRibbonService")
public class SecurityConfigRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfigRibbonService.class);


	@Autowired
	private SecurityConfigRibbon securityConfigRibbon;


	public SecurityConfigVO add(SecurityConfigAddDto addDto){
		SecurityConfigResult result = securityConfigRibbon.add(addDto);

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
		SecurityConfigResult result = securityConfigRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public SecurityConfigVO merge(Long id, SecurityConfigEditDto editDto){
		SecurityConfigResult result = securityConfigRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public SecurityConfigVO find(Long id){
		SecurityConfigResult result = securityConfigRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<SecurityConfigVO> list(PageSearchRequest<SecurityConfigCondition> pageSearchRequest) {
		SecurityConfigPageResult result = securityConfigRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
