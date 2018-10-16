package net.aicoder.speedcloud.console.business.speedcloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.app.AppBaseInfoRibbon;
import net.aicoder.speedcloud.client.app.result.AppBaseInfoPageResult;
import net.aicoder.speedcloud.client.app.result.AppBaseInfoResult;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoEditDto;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("appBaseInfoRibbonService")
public class AppBaseInfoRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppBaseInfoRibbonService.class);


	@Autowired
	private AppBaseInfoRibbon appBaseInfoRibbon;


	public AppBaseInfoVO add(AppBaseInfoAddDto addDto){
		AppBaseInfoResult result = appBaseInfoRibbon.add(addDto);

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
		AppBaseInfoResult result = appBaseInfoRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public AppBaseInfoVO merge(Long id, AppBaseInfoEditDto editDto){
		AppBaseInfoResult result = appBaseInfoRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AppBaseInfoVO find(Long id){
		AppBaseInfoResult result = appBaseInfoRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AppBaseInfoVO> list(PageSearchRequest<AppBaseInfoCondition> pageSearchRequest) {
		AppBaseInfoPageResult result = appBaseInfoRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
