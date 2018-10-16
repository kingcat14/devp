package net.aicoder.speedcloud.console.business.speedcloud.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.DevpSysDpyResourceRefRibbon;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourceRefPageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourceRefResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefEditDto;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourceRefVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResourceRefRibbonService")
public class DevpSysDpyResourceRefRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourceRefRibbonService.class);


	@Autowired
	private DevpSysDpyResourceRefRibbon devpSysDpyResourceRefRibbon;


	public DevpSysDpyResourceRefVO add(DevpSysDpyResourceRefAddDto addDto){
		DevpSysDpyResourceRefResult result = devpSysDpyResourceRefRibbon.add(addDto);

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
		DevpSysDpyResourceRefResult result = devpSysDpyResourceRefRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpyResourceRefVO merge(Long id, DevpSysDpyResourceRefEditDto editDto){
		DevpSysDpyResourceRefResult result = devpSysDpyResourceRefRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpyResourceRefVO find(Long id){
		DevpSysDpyResourceRefResult result = devpSysDpyResourceRefRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpyResourceRefVO> list(PageSearchRequest<DevpSysDpyResourceRefCondition> pageSearchRequest) {
		DevpSysDpyResourceRefPageResult result = devpSysDpyResourceRefRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
