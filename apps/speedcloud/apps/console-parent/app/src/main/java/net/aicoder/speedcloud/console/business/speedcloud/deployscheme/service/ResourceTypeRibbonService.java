package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceTypeVO;
import net.aicoder.speedcloud.client.deployscheme.ResourceTypeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("resourceTypeRibbonService")
public class ResourceTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceTypeRibbonService.class);


	@Autowired
	private ResourceTypeClient resourceTypeClient;


	public ResourceTypeVO add(ResourceTypeAddDto addDto){
		RestResponse<ResourceTypeVO> result = resourceTypeClient.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		RestResponse<ResourceTypeVO> result = resourceTypeClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
	}
	public ResourceTypeVO merge(String id, ResourceTypeEditDto editDto){
		RestResponse<ResourceTypeVO> result = resourceTypeClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ResourceTypeVO find(String id){
		RestResponse<ResourceTypeVO> result = resourceTypeClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ResourceTypeVO> list(PageSearchRequest<ResourceTypeCondition> pageSearchRequest) {
		RestResponse<PageContent<ResourceTypeVO>> result = resourceTypeClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
