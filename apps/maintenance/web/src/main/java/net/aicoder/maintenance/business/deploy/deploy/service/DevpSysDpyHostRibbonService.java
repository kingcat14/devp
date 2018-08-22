package net.aicoder.maintenance.business.deploy.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.deploy.DevpSysDpyHostRibbon;
import net.aicoder.devp.client.deploy.result.DevpSysDpyHostPageResult;
import net.aicoder.devp.client.deploy.result.DevpSysDpyHostResult;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostEditDto;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyHostVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpyHostRibbonService")
public class DevpSysDpyHostRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyHostRibbonService.class);


	@Autowired
	private DevpSysDpyHostRibbon devpSysDpyHostRibbon;


	public DevpSysDpyHostVO add(DevpSysDpyHostAddDto addDto){
		DevpSysDpyHostResult result = devpSysDpyHostRibbon.add(addDto);

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
		DevpSysDpyHostResult result = devpSysDpyHostRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpyHostVO merge(Long id, DevpSysDpyHostEditDto editDto){
		DevpSysDpyHostResult result = devpSysDpyHostRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpyHostVO find(Long id){
		DevpSysDpyHostResult result = devpSysDpyHostRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpyHostVO> list(PageSearchRequest<DevpSysDpyHostCondition> pageSearchRequest) {
		DevpSysDpyHostPageResult result = devpSysDpyHostRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
