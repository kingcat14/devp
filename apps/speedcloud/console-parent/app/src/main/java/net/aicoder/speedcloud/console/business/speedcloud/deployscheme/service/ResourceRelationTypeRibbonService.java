package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.ResourceRelationTypeRibbon;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceRelationTypePageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceRelationTypeResult;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceRelationTypeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("resourceRelationTypeRibbonService")
public class ResourceRelationTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceRelationTypeRibbonService.class);


	@Autowired
	private ResourceRelationTypeRibbon resourceRelationTypeRibbon;


	public ResourceRelationTypeVO add(ResourceRelationTypeAddDto addDto){
		ResourceRelationTypeResult result = resourceRelationTypeRibbon.add(addDto);

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
		ResourceRelationTypeResult result = resourceRelationTypeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
	}
	public ResourceRelationTypeVO merge(Long id, ResourceRelationTypeEditDto editDto){
		ResourceRelationTypeResult result = resourceRelationTypeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ResourceRelationTypeVO find(Long id){
		ResourceRelationTypeResult result = resourceRelationTypeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ResourceRelationTypeVO> list(PageSearchRequest<ResourceRelationTypeCondition> pageSearchRequest) {
		ResourceRelationTypePageResult result = resourceRelationTypeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
