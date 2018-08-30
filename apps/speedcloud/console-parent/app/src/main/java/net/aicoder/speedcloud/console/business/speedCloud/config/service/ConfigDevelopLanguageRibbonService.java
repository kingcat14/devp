package net.aicoder.speedcloud.console.business.speedCloud.config.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.config.ConfigDevelopLanguageRibbon;
import net.aicoder.speedcloud.client.config.result.ConfigDevelopLanguagePageResult;
import net.aicoder.speedcloud.client.config.result.ConfigDevelopLanguageResult;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageCondition;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageAddDto;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageEditDto;
import net.aicoder.speedcloud.business.config.vo.ConfigDevelopLanguageVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("configDevelopLanguageRibbonService")
public class ConfigDevelopLanguageRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDevelopLanguageRibbonService.class);


	@Autowired
	private ConfigDevelopLanguageRibbon configDevelopLanguageRibbon;


	public ConfigDevelopLanguageVO add(ConfigDevelopLanguageAddDto addDto){
		ConfigDevelopLanguageResult result = configDevelopLanguageRibbon.add(addDto);

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
		ConfigDevelopLanguageResult result = configDevelopLanguageRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
	}
	public ConfigDevelopLanguageVO merge(Long id, ConfigDevelopLanguageEditDto editDto){
		ConfigDevelopLanguageResult result = configDevelopLanguageRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ConfigDevelopLanguageVO find(Long id){
		ConfigDevelopLanguageResult result = configDevelopLanguageRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ConfigDevelopLanguageVO> list(PageSearchRequest<ConfigDevelopLanguageCondition> pageSearchRequest) {
		ConfigDevelopLanguagePageResult result = configDevelopLanguageRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
