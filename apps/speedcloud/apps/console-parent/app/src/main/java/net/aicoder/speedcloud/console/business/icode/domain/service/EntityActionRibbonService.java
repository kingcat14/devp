package net.aicoder.speedcloud.console.business.icode.domain.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionVO;
import net.aicoder.speedcloud.icode.client.domain.EntityActionRibbon;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionPageResult;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("entityActionRibbonService")
public class EntityActionRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityActionRibbonService.class);


	@Autowired
	private EntityActionRibbon entityActionRibbon;


	public EntityActionVO add(EntityActionAddDto addDto){
		EntityActionResult result = entityActionRibbon.add(addDto);

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
		EntityActionResult result = entityActionRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
	}
	public EntityActionVO merge(String id, EntityActionEditDto editDto){
		EntityActionResult result = entityActionRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public EntityActionVO find(String id){
		EntityActionResult result = entityActionRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<EntityActionVO> list(PageSearchRequest<EntityActionCondition> pageSearchRequest) {
		EntityActionPageResult result = entityActionRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
