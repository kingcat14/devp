package net.aicoder.speedcloud.console.business.speedcloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigCondition;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigEditDto;
import net.aicoder.speedcloud.business.app.vo.AppDevelopConfigVO;
import net.aicoder.speedcloud.client.app.AppDevelopConfigClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("appDevelopConfigRibbonService")
public class AppDevelopConfigRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppDevelopConfigRibbonService.class);


	@Autowired
	private AppDevelopConfigClient appDevelopConfigClient;


	public AppDevelopConfigVO add(AppDevelopConfigAddDto addDto){
		RestResponse<AppDevelopConfigVO> result = appDevelopConfigClient.add(addDto);

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
		RestResponse<AppDevelopConfigVO> result = appDevelopConfigClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public AppDevelopConfigVO merge(String id, AppDevelopConfigEditDto editDto){
		RestResponse<AppDevelopConfigVO> result = appDevelopConfigClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AppDevelopConfigVO find(String id){
		RestResponse<AppDevelopConfigVO> result = appDevelopConfigClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AppDevelopConfigVO> list(PageSearchRequest<AppDevelopConfigCondition> pageSearchRequest) {
		RestResponse<PageContent<AppDevelopConfigVO>> result = appDevelopConfigClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
