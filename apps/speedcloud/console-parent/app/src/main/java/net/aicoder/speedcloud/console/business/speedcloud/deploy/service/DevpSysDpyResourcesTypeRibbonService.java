package net.aicoder.speedcloud.console.business.speedcloud.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.DevpSysDpyResourcesTypeRibbon;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcesTypePageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcesTypeResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeEditDto;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesTypeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResourcesTypeRibbonService")
public class DevpSysDpyResourcesTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesTypeRibbonService.class);


	@Autowired
	private DevpSysDpyResourcesTypeRibbon devpSysDpyResourcesTypeRibbon;


	public DevpSysDpyResourcesTypeVO add(DevpSysDpyResourcesTypeAddDto addDto){
		DevpSysDpyResourcesTypeResult result = devpSysDpyResourcesTypeRibbon.add(addDto);

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
		DevpSysDpyResourcesTypeResult result = devpSysDpyResourcesTypeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpyResourcesTypeVO merge(Long id, DevpSysDpyResourcesTypeEditDto editDto){
		DevpSysDpyResourcesTypeResult result = devpSysDpyResourcesTypeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpyResourcesTypeVO find(Long id){
		DevpSysDpyResourcesTypeResult result = devpSysDpyResourcesTypeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpyResourcesTypeVO> list(PageSearchRequest<DevpSysDpyResourcesTypeCondition> pageSearchRequest) {
		DevpSysDpyResourcesTypePageResult result = devpSysDpyResourcesTypeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
