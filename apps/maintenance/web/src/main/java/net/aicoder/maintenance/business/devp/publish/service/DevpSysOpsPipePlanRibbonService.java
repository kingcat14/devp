package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsPipePlanRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipePlanPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipePlanResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsPipePlanVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsPipePlanRibbonService")
public class DevpSysOpsPipePlanRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipePlanRibbonService.class);


	@Autowired
	private DevpSysOpsPipePlanRibbon devpSysOpsPipePlanRibbon;


	public DevpSysOpsPipePlanVO add(DevpSysOpsPipePlanAddDto addDto){
		DevpSysOpsPipePlanResult result = devpSysOpsPipePlanRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysOpsPipePlanResult result = devpSysOpsPipePlanRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsPipePlanVO merge(Long id, DevpSysOpsPipePlanEditDto editDto){
		DevpSysOpsPipePlanResult result = devpSysOpsPipePlanRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsPipePlanVO find(Long id){
		DevpSysOpsPipePlanResult result = devpSysOpsPipePlanRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsPipePlanVO> list(PageSearchRequest<DevpSysOpsPipePlanCondition> pageSearchRequest) {
		DevpSysOpsPipePlanPageResult result = devpSysOpsPipePlanRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
