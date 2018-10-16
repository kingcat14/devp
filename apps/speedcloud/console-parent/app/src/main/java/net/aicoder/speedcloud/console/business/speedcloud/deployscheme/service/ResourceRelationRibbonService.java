package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.ResourceRelationRibbon;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceRelationPageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceRelationResult;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceRelationVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("resourceRelationRibbonService")
public class ResourceRelationRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceRelationRibbonService.class);


	@Autowired
	private ResourceRelationRibbon resourceRelationRibbon;


	public ResourceRelationVO add(ResourceRelationAddDto addDto){
		ResourceRelationResult result = resourceRelationRibbon.add(addDto);

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
		ResourceRelationResult result = resourceRelationRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
	}
	public ResourceRelationVO merge(Long id, ResourceRelationEditDto editDto){
		ResourceRelationResult result = resourceRelationRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ResourceRelationVO find(Long id){
		ResourceRelationResult result = resourceRelationRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ResourceRelationVO> list(PageSearchRequest<ResourceRelationCondition> pageSearchRequest) {
		ResourceRelationPageResult result = resourceRelationRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
