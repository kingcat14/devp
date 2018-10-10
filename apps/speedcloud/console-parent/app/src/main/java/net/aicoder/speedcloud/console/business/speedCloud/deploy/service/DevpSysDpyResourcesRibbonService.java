package net.aicoder.speedcloud.console.business.speedCloud.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.DevpSysDpyResourcesRibbon;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcesPageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcesResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesEditDto;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResourcesRibbonService")
public class DevpSysDpyResourcesRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesRibbonService.class);


	@Autowired
	private DevpSysDpyResourcesRibbon devpSysDpyResourcesRibbon;


	public DevpSysDpyResourcesVO add(DevpSysDpyResourcesAddDto addDto){
		DevpSysDpyResourcesResult result = devpSysDpyResourcesRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysDpyResourcesResult result = devpSysDpyResourcesRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpyResourcesVO merge(Long id, DevpSysDpyResourcesEditDto editDto){
		DevpSysDpyResourcesResult result = devpSysDpyResourcesRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpyResourcesVO find(Long id){
		DevpSysDpyResourcesResult result = devpSysDpyResourcesRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpyResourcesVO> list(PageSearchRequest<DevpSysDpyResourcesCondition> pageSearchRequest) {
		DevpSysDpyResourcesPageResult result = devpSysDpyResourcesRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
