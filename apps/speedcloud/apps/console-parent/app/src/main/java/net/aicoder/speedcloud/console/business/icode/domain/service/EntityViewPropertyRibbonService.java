package net.aicoder.speedcloud.console.business.icode.domain.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityViewPropertyVO;
import net.aicoder.speedcloud.icode.client.domain.EntityViewPropertyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("entityViewPropertyRibbonService")
public class EntityViewPropertyRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityViewPropertyRibbonService.class);


	@Autowired
	private EntityViewPropertyClient entityViewPropertyClient;

	public EntityViewPropertyVO createByProperty(String id){
		RestResponse<EntityViewPropertyVO> result = entityViewPropertyClient.createByProperty(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	}
	public EntityViewPropertyVO add(EntityViewPropertyAddDto addDto){
		RestResponse<EntityViewPropertyVO> result = entityViewPropertyClient.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}


	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		RestResponse<EntityViewPropertyVO> result = entityViewPropertyClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
	}
	public EntityViewPropertyVO merge(String id, EntityViewPropertyEditDto editDto){
		RestResponse<EntityViewPropertyVO> result = entityViewPropertyClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public EntityViewPropertyVO find(String id){
		RestResponse<EntityViewPropertyVO> result = entityViewPropertyClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<EntityViewPropertyVO> list(PageSearchRequest<EntityViewPropertyCondition> pageSearchRequest) {
		RestResponse<PageContent<EntityViewPropertyVO>> result = entityViewPropertyClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
