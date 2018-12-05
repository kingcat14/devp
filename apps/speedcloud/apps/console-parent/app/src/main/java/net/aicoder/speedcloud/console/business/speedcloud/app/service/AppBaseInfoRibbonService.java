package net.aicoder.speedcloud.console.business.speedcloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoEditDto;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;
import net.aicoder.speedcloud.client.app.AppBaseInfoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("appBaseInfoRibbonService")
public class AppBaseInfoRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppBaseInfoRibbonService.class);


	@Autowired
	private AppBaseInfoClient appBaseInfoClient;


	public AppBaseInfoVO add(AppBaseInfoAddDto addDto){
		RestResponse<AppBaseInfoVO> result = appBaseInfoClient.add(addDto);

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
		RestResponse<AppBaseInfoVO> result = appBaseInfoClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public AppBaseInfoVO merge(String id, AppBaseInfoEditDto editDto){
		RestResponse<AppBaseInfoVO> result = appBaseInfoClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AppBaseInfoVO find(String id){
		RestResponse<AppBaseInfoVO> result = appBaseInfoClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AppBaseInfoVO> list(PageSearchRequest<AppBaseInfoCondition> pageSearchRequest) {
		RestResponse<PageContent<AppBaseInfoVO>> result = appBaseInfoClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
