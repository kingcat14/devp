package net.aicoder.speedcloud.console.business.speedcloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeAddDto;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeCondition;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeEditDto;
import net.aicoder.speedcloud.business.app.vo.ApplicationTypeVO;
import net.aicoder.speedcloud.client.app.ApplicationTypeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("applicationTypeRibbonService")
public class ApplicationTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTypeRibbonService.class);


	@Autowired
	private ApplicationTypeClient applicationTypeClient;


	public ApplicationTypeVO add(ApplicationTypeAddDto addDto){
		RestResponse<ApplicationTypeVO> result = applicationTypeClient.add(addDto);

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
		RestResponse<ApplicationTypeVO> result = applicationTypeClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public ApplicationTypeVO merge(String id, ApplicationTypeEditDto editDto){
		RestResponse<ApplicationTypeVO> result = applicationTypeClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ApplicationTypeVO find(String id){
		RestResponse<ApplicationTypeVO> result = applicationTypeClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ApplicationTypeVO> list(PageSearchRequest<ApplicationTypeCondition> pageSearchRequest) {
		RestResponse<PageContent<ApplicationTypeVO>> result = applicationTypeClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
