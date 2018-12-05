package net.aicoder.speedcloud.console.business.speedcloud.env.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigAddDto;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigCondition;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigEditDto;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.client.env.AppEnvConfigClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("appEnvConfigRibbonService")
public class AppEnvConfigRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppEnvConfigRibbonService.class);


	@Autowired
	private AppEnvConfigClient appEnvConfigClient;


	public AppEnvConfigVO add(AppEnvConfigAddDto addDto){
		RestResponse<AppEnvConfigVO> result = appEnvConfigClient.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		RestResponse<AppEnvConfigVO> result = appEnvConfigClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}
	}
	public AppEnvConfigVO merge(String id, AppEnvConfigEditDto editDto){
		RestResponse<AppEnvConfigVO> result = appEnvConfigClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AppEnvConfigVO find(String id){
		RestResponse<AppEnvConfigVO> result = appEnvConfigClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AppEnvConfigVO> list(PageSearchRequest<AppEnvConfigCondition> pageSearchRequest) {
		RestResponse<PageContent<AppEnvConfigVO>> result = appEnvConfigClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
