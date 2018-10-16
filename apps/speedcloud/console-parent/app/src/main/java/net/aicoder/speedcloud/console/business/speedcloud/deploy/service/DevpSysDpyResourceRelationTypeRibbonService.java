package net.aicoder.speedcloud.console.business.speedcloud.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.DevpSysDpyResourceRelationTypeRibbon;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourceRelationTypePageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourceRelationTypeResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeEditDto;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourceRelationTypeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResourceRelationTypeRibbonService")
public class DevpSysDpyResourceRelationTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourceRelationTypeRibbonService.class);


	@Autowired
	private DevpSysDpyResourceRelationTypeRibbon devpSysDpyResourceRelationTypeRibbon;


	public DevpSysDpyResourceRelationTypeVO add(DevpSysDpyResourceRelationTypeAddDto addDto){
		DevpSysDpyResourceRelationTypeResult result = devpSysDpyResourceRelationTypeRibbon.add(addDto);

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
		DevpSysDpyResourceRelationTypeResult result = devpSysDpyResourceRelationTypeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpyResourceRelationTypeVO merge(Long id, DevpSysDpyResourceRelationTypeEditDto editDto){
		DevpSysDpyResourceRelationTypeResult result = devpSysDpyResourceRelationTypeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpyResourceRelationTypeVO find(Long id){
		DevpSysDpyResourceRelationTypeResult result = devpSysDpyResourceRelationTypeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpyResourceRelationTypeVO> list(PageSearchRequest<DevpSysDpyResourceRelationTypeCondition> pageSearchRequest) {
		DevpSysDpyResourceRelationTypePageResult result = devpSysDpyResourceRelationTypeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
