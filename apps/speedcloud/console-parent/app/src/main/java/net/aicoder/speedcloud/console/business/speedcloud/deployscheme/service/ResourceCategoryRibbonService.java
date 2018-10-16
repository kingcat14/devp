package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.ResourceCategoryRibbon;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceCategoryPageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceCategoryResult;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceCategoryVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("resourceCategoryRibbonService")
public class ResourceCategoryRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCategoryRibbonService.class);


	@Autowired
	private ResourceCategoryRibbon resourceCategoryRibbon;


	public ResourceCategoryVO add(ResourceCategoryAddDto addDto){
		ResourceCategoryResult result = resourceCategoryRibbon.add(addDto);

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
		ResourceCategoryResult result = resourceCategoryRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
	}
	public ResourceCategoryVO merge(Long id, ResourceCategoryEditDto editDto){
		ResourceCategoryResult result = resourceCategoryRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ResourceCategoryVO find(Long id){
		ResourceCategoryResult result = resourceCategoryRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ResourceCategoryVO> list(PageSearchRequest<ResourceCategoryCondition> pageSearchRequest) {
		ResourceCategoryPageResult result = resourceCategoryRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
