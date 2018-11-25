package net.aicoder.speedcloud.console.business.icode.project.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationEditDto;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentDomainRelationVO;
import net.aicoder.speedcloud.icode.client.project.ComponentDomainRelationRibbon;
import net.aicoder.speedcloud.icode.client.project.result.ComponentDomainRelationPageResult;
import net.aicoder.speedcloud.icode.client.project.result.ComponentDomainRelationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("componentDomainRelationRibbonService")
public class ComponentDomainRelationRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentDomainRelationRibbonService.class);


	@Autowired
	private ComponentDomainRelationRibbon componentDomainRelationRibbon;


	public ComponentDomainRelationVO add(ComponentDomainRelationAddDto addDto){
		ComponentDomainRelationResult result = componentDomainRelationRibbon.add(addDto);

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
		ComponentDomainRelationResult result = componentDomainRelationRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}
	}
	public ComponentDomainRelationVO merge(String id, ComponentDomainRelationEditDto editDto){
		ComponentDomainRelationResult result = componentDomainRelationRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ComponentDomainRelationVO find(String id){
		ComponentDomainRelationResult result = componentDomainRelationRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ComponentDomainRelationVO> list(PageSearchRequest<ComponentDomainRelationCondition> pageSearchRequest) {
		ComponentDomainRelationPageResult result = componentDomainRelationRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
