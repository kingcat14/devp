package net.aicoder.speedcloud.console.business.icode.project.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeEditDto;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentTypeVO;
import net.aicoder.speedcloud.icode.client.project.ComponentTypeRibbon;
import net.aicoder.speedcloud.icode.client.project.result.ComponentTypePageResult;
import net.aicoder.speedcloud.icode.client.project.result.ComponentTypeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("componentTypeRibbonService")
public class ComponentTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentTypeRibbonService.class);


	@Autowired
	private ComponentTypeRibbon componentTypeRibbon;


	public ComponentTypeVO add(ComponentTypeAddDto addDto){
		ComponentTypeResult result = componentTypeRibbon.add(addDto);

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
		ComponentTypeResult result = componentTypeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}
	}
	public ComponentTypeVO merge(String id, ComponentTypeEditDto editDto){
		ComponentTypeResult result = componentTypeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ComponentTypeVO find(String id){
		ComponentTypeResult result = componentTypeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ComponentTypeVO> list(PageSearchRequest<ComponentTypeCondition> pageSearchRequest) {
		ComponentTypePageResult result = componentTypeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
