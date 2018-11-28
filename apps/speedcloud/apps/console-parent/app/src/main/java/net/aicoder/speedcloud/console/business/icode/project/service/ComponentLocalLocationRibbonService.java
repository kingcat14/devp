package net.aicoder.speedcloud.console.business.icode.project.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationEditDto;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentLocalLocationVO;
import net.aicoder.speedcloud.icode.client.project.ComponentLocalLocationRibbon;
import net.aicoder.speedcloud.icode.client.project.result.ComponentLocalLocationPageResult;
import net.aicoder.speedcloud.icode.client.project.result.ComponentLocalLocationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("componentLocalLocationRibbonService")
public class ComponentLocalLocationRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentLocalLocationRibbonService.class);


	@Autowired
	private ComponentLocalLocationRibbon componentLocalLocationRibbon;


	public ComponentLocalLocationVO add(ComponentLocalLocationAddDto addDto){
		ComponentLocalLocationResult result = componentLocalLocationRibbon.add(addDto);

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
		ComponentLocalLocationResult result = componentLocalLocationRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}
	}
	public ComponentLocalLocationVO merge(String id, ComponentLocalLocationEditDto editDto){
		ComponentLocalLocationResult result = componentLocalLocationRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ComponentLocalLocationVO find(String id){
		ComponentLocalLocationResult result = componentLocalLocationRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public ComponentLocalLocationVO findByComponentIdAndTenantIdAndAccountId(String componentId, Long tenantId, Long accountId){

		ComponentLocalLocationCondition condition = new ComponentLocalLocationCondition();

		condition.setAccountId(accountId);
		condition.setTid(tenantId);
		condition.setComponent(componentId);

		PageSearchRequest pageSearchRequest  = new PageSearchRequest();
		pageSearchRequest.setSearchCondition(condition);
		PageContent<ComponentLocalLocationVO> pageContent = this.list(pageSearchRequest);
		ComponentLocalLocationVO vo = null;

		if(pageContent.getTotal() > 0){
			vo = pageContent.getContent().get(0);
		}

		return vo;

	}

	public PageContent<ComponentLocalLocationVO> list(PageSearchRequest<ComponentLocalLocationCondition> pageSearchRequest) {
		ComponentLocalLocationPageResult result = componentLocalLocationRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
