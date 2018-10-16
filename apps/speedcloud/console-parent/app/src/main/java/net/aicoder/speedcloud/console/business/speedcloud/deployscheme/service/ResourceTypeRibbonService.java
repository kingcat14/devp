package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.ResourceTypeRibbon;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceTypePageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceTypeResult;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceTypeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("resourceTypeRibbonService")
public class ResourceTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceTypeRibbonService.class);


	@Autowired
	private ResourceTypeRibbon resourceTypeRibbon;


	public ResourceTypeVO add(ResourceTypeAddDto addDto){
		ResourceTypeResult result = resourceTypeRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		ResourceTypeResult result = resourceTypeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
	}
	public ResourceTypeVO merge(Long id, ResourceTypeEditDto editDto){
		ResourceTypeResult result = resourceTypeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ResourceTypeVO find(Long id){
		ResourceTypeResult result = resourceTypeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ResourceTypeVO> list(PageSearchRequest<ResourceTypeCondition> pageSearchRequest) {
		ResourceTypePageResult result = resourceTypeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
