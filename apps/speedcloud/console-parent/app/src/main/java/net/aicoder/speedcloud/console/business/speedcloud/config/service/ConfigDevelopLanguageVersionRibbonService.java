package net.aicoder.speedcloud.console.business.speedcloud.config.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.config.ConfigDevelopLanguageVersionRibbon;
import net.aicoder.speedcloud.client.config.result.ConfigDevelopLanguageVersionPageResult;
import net.aicoder.speedcloud.client.config.result.ConfigDevelopLanguageVersionResult;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionCondition;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionAddDto;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionEditDto;
import net.aicoder.speedcloud.business.config.vo.ConfigDevelopLanguageVersionVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("configDevelopLanguageVersionRibbonService")
public class ConfigDevelopLanguageVersionRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDevelopLanguageVersionRibbonService.class);


	@Autowired
	private ConfigDevelopLanguageVersionRibbon configDevelopLanguageVersionRibbon;


	public ConfigDevelopLanguageVersionVO add(ConfigDevelopLanguageVersionAddDto addDto){
		ConfigDevelopLanguageVersionResult result = configDevelopLanguageVersionRibbon.add(addDto);

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
		ConfigDevelopLanguageVersionResult result = configDevelopLanguageVersionRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
	}
	public ConfigDevelopLanguageVersionVO merge(Long id, ConfigDevelopLanguageVersionEditDto editDto){
		ConfigDevelopLanguageVersionResult result = configDevelopLanguageVersionRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ConfigDevelopLanguageVersionVO find(Long id){
		ConfigDevelopLanguageVersionResult result = configDevelopLanguageVersionRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ConfigDevelopLanguageVersionVO> list(PageSearchRequest<ConfigDevelopLanguageVersionCondition> pageSearchRequest) {
		ConfigDevelopLanguageVersionPageResult result = configDevelopLanguageVersionRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
