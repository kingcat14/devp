package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.ResourceRibbon;
import net.aicoder.speedcloud.client.deployscheme.result.ResourcePageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceResult;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("resourceRibbonService")
public class ResourceRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceRibbonService.class);


	@Autowired
	private ResourceRibbon resourceRibbon;


	public ResourceVO add(ResourceAddDto addDto){
		ResourceResult result = resourceRibbon.add(addDto);

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
		ResourceResult result = resourceRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
	}
	public ResourceVO merge(Long id, ResourceEditDto editDto){
		ResourceResult result = resourceRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ResourceVO find(Long id){
		ResourceResult result = resourceRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ResourceVO> list(PageSearchRequest<ResourceCondition> pageSearchRequest) {
		ResourcePageResult result = resourceRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
