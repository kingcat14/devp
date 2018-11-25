package net.aicoder.speedcloud.console.business.icode.domain.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterVO;
import net.aicoder.speedcloud.icode.client.domain.EntityActionParameterRibbon;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionParameterPageResult;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionParameterResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("entityActionParameterRibbonService")
public class EntityActionParameterRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityActionParameterRibbonService.class);


	@Autowired
	private EntityActionParameterRibbon entityActionParameterRibbon;


	public EntityActionParameterVO add(EntityActionParameterAddDto addDto){
		EntityActionParameterResult result = entityActionParameterRibbon.add(addDto);

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
		EntityActionParameterResult result = entityActionParameterRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
	}
	public EntityActionParameterVO merge(String id, EntityActionParameterEditDto editDto){
		EntityActionParameterResult result = entityActionParameterRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public EntityActionParameterVO find(String id){
		EntityActionParameterResult result = entityActionParameterRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<EntityActionParameterVO> list(PageSearchRequest<EntityActionParameterCondition> pageSearchRequest) {
		EntityActionParameterPageResult result = entityActionParameterRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
