package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceCategoryVO;
import net.aicoder.speedcloud.client.deployscheme.ResourceCategoryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("resourceCategoryRibbonService")
public class ResourceCategoryRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCategoryRibbonService.class);


	@Autowired
	private ResourceCategoryClient resourceCategoryClient;


	public ResourceCategoryVO add(ResourceCategoryAddDto addDto){
		RestResponse<ResourceCategoryVO> result = resourceCategoryClient.add(addDto);

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
		RestResponse<ResourceCategoryVO> result = resourceCategoryClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
	}
	public ResourceCategoryVO merge(String id, ResourceCategoryEditDto editDto){
		RestResponse<ResourceCategoryVO> result = resourceCategoryClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ResourceCategoryVO find(String id){
		RestResponse<ResourceCategoryVO> result = resourceCategoryClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ResourceCategoryVO> list(PageSearchRequest<ResourceCategoryCondition> pageSearchRequest) {
		RestResponse<PageContent<ResourceCategoryVO>> result = resourceCategoryClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
