package net.aicoder.speedcloud.console.business.speedcloud.config.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.config.dto.EnvLevelAddDto;
import net.aicoder.speedcloud.business.config.dto.EnvLevelCondition;
import net.aicoder.speedcloud.business.config.dto.EnvLevelEditDto;
import net.aicoder.speedcloud.business.config.vo.EnvLevelVO;
import net.aicoder.speedcloud.client.config.EnvLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("envLevelRibbonService")
public class EnvLevelRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvLevelRibbonService.class);


	@Autowired
	private EnvLevelClient envLevelClient;


	public EnvLevelVO add(EnvLevelAddDto addDto){
		RestResponse<EnvLevelVO> result = envLevelClient.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		RestResponse<EnvLevelVO> result = envLevelClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
	}
	public EnvLevelVO merge(String id, EnvLevelEditDto editDto){
		RestResponse<EnvLevelVO> result = envLevelClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public EnvLevelVO find(String id){
		RestResponse<EnvLevelVO> result = envLevelClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<EnvLevelVO> list(PageSearchRequest<EnvLevelCondition> pageSearchRequest) {
		RestResponse<PageContent<EnvLevelVO>> result = envLevelClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
