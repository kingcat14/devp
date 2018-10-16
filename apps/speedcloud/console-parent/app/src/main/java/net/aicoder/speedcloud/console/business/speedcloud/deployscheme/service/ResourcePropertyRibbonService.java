package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.ResourcePropertyRibbon;
import net.aicoder.speedcloud.client.deployscheme.result.ResourcePropertyPageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourcePropertyResult;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourcePropertyVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("resourcePropertyRibbonService")
public class ResourcePropertyRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcePropertyRibbonService.class);


	@Autowired
	private ResourcePropertyRibbon resourcePropertyRibbon;


	public ResourcePropertyVO add(ResourcePropertyAddDto addDto){
		ResourcePropertyResult result = resourcePropertyRibbon.add(addDto);

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
		ResourcePropertyResult result = resourcePropertyRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
	}
	public ResourcePropertyVO merge(Long id, ResourcePropertyEditDto editDto){
		ResourcePropertyResult result = resourcePropertyRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ResourcePropertyVO find(Long id){
		ResourcePropertyResult result = resourcePropertyRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ResourcePropertyVO> list(PageSearchRequest<ResourcePropertyCondition> pageSearchRequest) {
		ResourcePropertyPageResult result = resourcePropertyRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
