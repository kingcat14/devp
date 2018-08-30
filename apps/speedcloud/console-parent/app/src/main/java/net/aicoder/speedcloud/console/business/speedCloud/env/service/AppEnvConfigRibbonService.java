package net.aicoder.speedcloud.console.business.speedCloud.env.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.env.AppEnvConfigRibbon;
import net.aicoder.speedcloud.client.env.result.AppEnvConfigPageResult;
import net.aicoder.speedcloud.client.env.result.AppEnvConfigResult;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigCondition;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigAddDto;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigEditDto;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("appEnvConfigRibbonService")
public class AppEnvConfigRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppEnvConfigRibbonService.class);


	@Autowired
	private AppEnvConfigRibbon appEnvConfigRibbon;


	public AppEnvConfigVO add(AppEnvConfigAddDto addDto){
		AppEnvConfigResult result = appEnvConfigRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		AppEnvConfigResult result = appEnvConfigRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}
	}
	public AppEnvConfigVO merge(Long id, AppEnvConfigEditDto editDto){
		AppEnvConfigResult result = appEnvConfigRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AppEnvConfigVO find(Long id){
		AppEnvConfigResult result = appEnvConfigRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AppEnvConfigVO> list(PageSearchRequest<AppEnvConfigCondition> pageSearchRequest) {
		AppEnvConfigPageResult result = appEnvConfigRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
