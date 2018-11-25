package net.aicoder.speedcloud.console.business.icode.project.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentEditDto;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
import net.aicoder.speedcloud.icode.client.project.ComponentRibbon;
import net.aicoder.speedcloud.icode.client.project.result.ComponentPageResult;
import net.aicoder.speedcloud.icode.client.project.result.ComponentResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("componentRibbonService")
public class ComponentRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentRibbonService.class);


	@Autowired
	private ComponentRibbon componentRibbon;


	public ComponentVO add(ComponentAddDto addDto){
		ComponentResult result = componentRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		ComponentResult result = componentRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}
	}
	public ComponentVO merge(String id, ComponentEditDto editDto){
		ComponentResult result = componentRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ComponentVO find(String id){
		ComponentResult result = componentRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ComponentVO> list(PageSearchRequest<ComponentCondition> pageSearchRequest) {
		ComponentPageResult result = componentRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
