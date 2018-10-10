package net.aicoder.speedcloud.console.business.speedCloud.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.DevpSysDpyResourcesCategoryRibbon;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcesCategoryPageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcesCategoryResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryEditDto;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesCategoryVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResourcesCategoryRibbonService")
public class DevpSysDpyResourcesCategoryRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesCategoryRibbonService.class);


	@Autowired
	private DevpSysDpyResourcesCategoryRibbon devpSysDpyResourcesCategoryRibbon;


	public DevpSysDpyResourcesCategoryVO add(DevpSysDpyResourcesCategoryAddDto addDto){
		DevpSysDpyResourcesCategoryResult result = devpSysDpyResourcesCategoryRibbon.add(addDto);

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
		DevpSysDpyResourcesCategoryResult result = devpSysDpyResourcesCategoryRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpyResourcesCategoryVO merge(Long id, DevpSysDpyResourcesCategoryEditDto editDto){
		DevpSysDpyResourcesCategoryResult result = devpSysDpyResourcesCategoryRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpyResourcesCategoryVO find(Long id){
		DevpSysDpyResourcesCategoryResult result = devpSysDpyResourcesCategoryRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpyResourcesCategoryVO> list(PageSearchRequest<DevpSysDpyResourcesCategoryCondition> pageSearchRequest) {
		DevpSysDpyResourcesCategoryPageResult result = devpSysDpyResourcesCategoryRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
