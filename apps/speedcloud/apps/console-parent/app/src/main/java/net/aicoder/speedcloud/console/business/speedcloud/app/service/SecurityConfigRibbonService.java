package net.aicoder.speedcloud.console.business.speedcloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigCondition;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigEditDto;
import net.aicoder.speedcloud.business.app.vo.SecurityConfigVO;
import net.aicoder.speedcloud.client.app.SecurityConfigClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("securityConfigRibbonService")
public class SecurityConfigRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfigRibbonService.class);


	@Autowired
	private SecurityConfigClient securityConfigClient;


	public SecurityConfigVO add(SecurityConfigAddDto addDto){
		RestResponse<SecurityConfigVO> result = securityConfigClient.add(addDto);

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
		RestResponse<SecurityConfigVO> result = securityConfigClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public SecurityConfigVO merge(String id, SecurityConfigEditDto editDto){
		RestResponse<SecurityConfigVO> result = securityConfigClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public SecurityConfigVO find(String id){
		RestResponse<SecurityConfigVO> result = securityConfigClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<SecurityConfigVO> list(PageSearchRequest<SecurityConfigCondition> pageSearchRequest) {
		RestResponse<PageContent<SecurityConfigVO>> result = securityConfigClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
