package net.aicoder.speedcloud.console.business.speedcloud.app.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.app.AppDevelopConfigRibbon;
import net.aicoder.speedcloud.client.app.result.AppDevelopConfigPageResult;
import net.aicoder.speedcloud.client.app.result.AppDevelopConfigResult;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigCondition;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigEditDto;
import net.aicoder.speedcloud.business.app.vo.AppDevelopConfigVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("appDevelopConfigRibbonService")
public class AppDevelopConfigRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppDevelopConfigRibbonService.class);


	@Autowired
	private AppDevelopConfigRibbon appDevelopConfigRibbon;


	public AppDevelopConfigVO add(AppDevelopConfigAddDto addDto){
		AppDevelopConfigResult result = appDevelopConfigRibbon.add(addDto);

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
		AppDevelopConfigResult result = appDevelopConfigRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}
	}
	public AppDevelopConfigVO merge(Long id, AppDevelopConfigEditDto editDto){
		AppDevelopConfigResult result = appDevelopConfigRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AppDevelopConfigVO find(Long id){
		AppDevelopConfigResult result = appDevelopConfigRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AppDevelopConfigVO> list(PageSearchRequest<AppDevelopConfigCondition> pageSearchRequest) {
		AppDevelopConfigPageResult result = appDevelopConfigRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "APP", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
