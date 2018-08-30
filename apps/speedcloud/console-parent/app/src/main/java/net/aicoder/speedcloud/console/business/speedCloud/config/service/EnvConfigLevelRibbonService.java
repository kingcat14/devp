package net.aicoder.speedcloud.console.business.speedCloud.config.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.config.EnvConfigLevelRibbon;
import net.aicoder.speedcloud.client.config.result.EnvConfigLevelPageResult;
import net.aicoder.speedcloud.client.config.result.EnvConfigLevelResult;
import net.aicoder.speedcloud.business.config.dto.EnvConfigLevelCondition;
import net.aicoder.speedcloud.business.config.dto.EnvConfigLevelAddDto;
import net.aicoder.speedcloud.business.config.dto.EnvConfigLevelEditDto;
import net.aicoder.speedcloud.business.config.vo.EnvConfigLevelVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("envConfigLevelRibbonService")
public class EnvConfigLevelRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvConfigLevelRibbonService.class);


	@Autowired
	private EnvConfigLevelRibbon envConfigLevelRibbon;


	public EnvConfigLevelVO add(EnvConfigLevelAddDto addDto){
		EnvConfigLevelResult result = envConfigLevelRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		EnvConfigLevelResult result = envConfigLevelRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
	}
	public EnvConfigLevelVO merge(Long id, EnvConfigLevelEditDto editDto){
		EnvConfigLevelResult result = envConfigLevelRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public EnvConfigLevelVO find(Long id){
		EnvConfigLevelResult result = envConfigLevelRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<EnvConfigLevelVO> list(PageSearchRequest<EnvConfigLevelCondition> pageSearchRequest) {
		EnvConfigLevelPageResult result = envConfigLevelRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
