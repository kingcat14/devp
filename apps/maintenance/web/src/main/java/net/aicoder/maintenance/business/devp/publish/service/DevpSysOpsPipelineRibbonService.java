package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsPipelineRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipelinePageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipelineResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsPipelineVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsPipelineRibbonService")
public class DevpSysOpsPipelineRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipelineRibbonService.class);


	@Autowired
	private DevpSysOpsPipelineRibbon devpSysOpsPipelineRibbon;


	public DevpSysOpsPipelineVO add(DevpSysOpsPipelineAddDto addDto){
		DevpSysOpsPipelineResult result = devpSysOpsPipelineRibbon.add(addDto);

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
		DevpSysOpsPipelineResult result = devpSysOpsPipelineRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsPipelineVO merge(Long id, DevpSysOpsPipelineEditDto editDto){
		DevpSysOpsPipelineResult result = devpSysOpsPipelineRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsPipelineVO find(Long id){
		DevpSysOpsPipelineResult result = devpSysOpsPipelineRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsPipelineVO> list(PageSearchRequest<DevpSysOpsPipelineCondition> pageSearchRequest) {
		DevpSysOpsPipelinePageResult result = devpSysOpsPipelineRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
