package net.aicoder.speedcloud.console.business.speedCloud.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.DevpSysDpyResourcePropertyRibbon;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcePropertyPageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcePropertyResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcePropertyCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcePropertyAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcePropertyEditDto;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcePropertyVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResourcePropertyRibbonService")
public class DevpSysDpyResourcePropertyRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcePropertyRibbonService.class);


	@Autowired
	private DevpSysDpyResourcePropertyRibbon devpSysDpyResourcePropertyRibbon;


	public DevpSysDpyResourcePropertyVO add(DevpSysDpyResourcePropertyAddDto addDto){
		DevpSysDpyResourcePropertyResult result = devpSysDpyResourcePropertyRibbon.add(addDto);

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
		DevpSysDpyResourcePropertyResult result = devpSysDpyResourcePropertyRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpyResourcePropertyVO merge(Long id, DevpSysDpyResourcePropertyEditDto editDto){
		DevpSysDpyResourcePropertyResult result = devpSysDpyResourcePropertyRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpyResourcePropertyVO find(Long id){
		DevpSysDpyResourcePropertyResult result = devpSysDpyResourcePropertyRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpyResourcePropertyVO> list(PageSearchRequest<DevpSysDpyResourcePropertyCondition> pageSearchRequest) {
		DevpSysDpyResourcePropertyPageResult result = devpSysDpyResourcePropertyRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
