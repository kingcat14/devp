package net.aicoder.devp.maintenance.business.deploy.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.deploy.DevpSysDpyResInstHostRibbon;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyResInstHostPageResult;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyResInstHostResult;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstHostCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstHostAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstHostEditDto;
import net.aicoder.devp.deploy.business.deploy.vo.DevpSysDpyResInstHostVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResInstHostRibbonService")
public class DevpSysDpyResInstHostRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResInstHostRibbonService.class);


	@Autowired
	private DevpSysDpyResInstHostRibbon devpSysDpyResInstHostRibbon;


	public DevpSysDpyResInstHostVO add(DevpSysDpyResInstHostAddDto addDto){
		DevpSysDpyResInstHostResult result = devpSysDpyResInstHostRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysDpyResInstHostResult result = devpSysDpyResInstHostRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpyResInstHostVO merge(Long id, DevpSysDpyResInstHostEditDto editDto){
		DevpSysDpyResInstHostResult result = devpSysDpyResInstHostRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpyResInstHostVO find(Long id){
		DevpSysDpyResInstHostResult result = devpSysDpyResInstHostRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpyResInstHostVO> list(PageSearchRequest<DevpSysDpyResInstHostCondition> pageSearchRequest) {
		DevpSysDpyResInstHostPageResult result = devpSysDpyResInstHostRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
