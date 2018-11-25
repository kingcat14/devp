package net.aicoder.speedcloud.console.business.icode.domain.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterPropertyVO;
import net.aicoder.speedcloud.icode.client.domain.EntityActionParameterPropertyRibbon;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionParameterPropertyPageResult;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionParameterPropertyResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("entityActionParameterPropertyRibbonService")
public class EntityActionParameterPropertyRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityActionParameterPropertyRibbonService.class);


	@Autowired
	private EntityActionParameterPropertyRibbon entityActionParameterPropertyRibbon;


	public EntityActionParameterPropertyVO add(EntityActionParameterPropertyAddDto addDto){
		EntityActionParameterPropertyResult result = entityActionParameterPropertyRibbon.add(addDto);

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
		EntityActionParameterPropertyResult result = entityActionParameterPropertyRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
	}
	public EntityActionParameterPropertyVO merge(String id, EntityActionParameterPropertyEditDto editDto){
		EntityActionParameterPropertyResult result = entityActionParameterPropertyRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public EntityActionParameterPropertyVO find(String id){
		EntityActionParameterPropertyResult result = entityActionParameterPropertyRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<EntityActionParameterPropertyVO> list(PageSearchRequest<EntityActionParameterPropertyCondition> pageSearchRequest) {
		EntityActionParameterPropertyPageResult result = entityActionParameterPropertyRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
