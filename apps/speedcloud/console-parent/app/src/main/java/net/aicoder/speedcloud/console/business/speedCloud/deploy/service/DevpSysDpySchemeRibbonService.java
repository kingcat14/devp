package net.aicoder.speedcloud.console.business.speedCloud.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.DevpSysDpySchemeRibbon;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpySchemePageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpySchemeResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpySchemeCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpySchemeAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpySchemeEditDto;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpySchemeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpySchemeRibbonService")
public class DevpSysDpySchemeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpySchemeRibbonService.class);


	@Autowired
	private DevpSysDpySchemeRibbon devpSysDpySchemeRibbon;


	public DevpSysDpySchemeVO add(DevpSysDpySchemeAddDto addDto){
		DevpSysDpySchemeResult result = devpSysDpySchemeRibbon.add(addDto);

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
		DevpSysDpySchemeResult result = devpSysDpySchemeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpySchemeVO merge(Long id, DevpSysDpySchemeEditDto editDto){
		DevpSysDpySchemeResult result = devpSysDpySchemeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpySchemeVO find(Long id){
		DevpSysDpySchemeResult result = devpSysDpySchemeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpySchemeVO> list(PageSearchRequest<DevpSysDpySchemeCondition> pageSearchRequest) {
		DevpSysDpySchemePageResult result = devpSysDpySchemeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
