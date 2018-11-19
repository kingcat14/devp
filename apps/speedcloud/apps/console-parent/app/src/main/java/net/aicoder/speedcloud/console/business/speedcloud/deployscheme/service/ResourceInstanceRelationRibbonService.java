package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceInstanceRelationVO;
import net.aicoder.speedcloud.client.deployscheme.ResourceInstanceRelationRibbon;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceInstanceRelationPageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceInstanceRelationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("resourceInstanceRelationRibbonService")
public class ResourceInstanceRelationRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceInstanceRelationRibbonService.class);


	@Autowired
	private ResourceInstanceRelationRibbon resourceInstanceRelationRibbon;


	public ResourceInstanceRelationVO add(ResourceInstanceRelationAddDto addDto){
		ResourceInstanceRelationResult result = resourceInstanceRelationRibbon.add(addDto);

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
		ResourceInstanceRelationResult result = resourceInstanceRelationRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
	}
	public ResourceInstanceRelationVO merge(Long id, ResourceInstanceRelationEditDto editDto){
		ResourceInstanceRelationResult result = resourceInstanceRelationRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ResourceInstanceRelationVO updateAll(Long id, List<ResourceInstanceRelationAddDto> addDtoList){
		ResourceInstanceRelationResult result = resourceInstanceRelationRibbon.updateAll(id, addDtoList);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ResourceInstanceRelationVO find(Long id){
		ResourceInstanceRelationResult result = resourceInstanceRelationRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ResourceInstanceRelationVO> list(PageSearchRequest<ResourceInstanceRelationCondition> pageSearchRequest) {
		ResourceInstanceRelationPageResult result = resourceInstanceRelationRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
