package net.aicoder.maintenance.business.deploy.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.deploy.DevpSysDpyResInstRibbon;
import net.aicoder.devp.client.deploy.result.DevpSysDpyResInstPageResult;
import net.aicoder.devp.client.deploy.result.DevpSysDpyResInstResult;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstEditDto;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyResInstVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResInstRibbonService")
public class DevpSysDpyResInstRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResInstRibbonService.class);


	@Autowired
	private DevpSysDpyResInstRibbon devpSysDpyResInstRibbon;


	public DevpSysDpyResInstVO add(DevpSysDpyResInstAddDto addDto){
		DevpSysDpyResInstResult result = devpSysDpyResInstRibbon.add(addDto);

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
		DevpSysDpyResInstResult result = devpSysDpyResInstRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpyResInstVO merge(Long id, DevpSysDpyResInstEditDto editDto){
		DevpSysDpyResInstResult result = devpSysDpyResInstRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpyResInstVO find(Long id){
		DevpSysDpyResInstResult result = devpSysDpyResInstRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpyResInstVO> list(PageSearchRequest<DevpSysDpyResInstCondition> pageSearchRequest) {
		DevpSysDpyResInstPageResult result = devpSysDpyResInstRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
