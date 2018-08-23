package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsTaskBaselineRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskBaselinePageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskBaselineResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskBaselineVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskBaselineRibbonService")
public class DevpSysOpsTaskBaselineRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskBaselineRibbonService.class);


	@Autowired
	private DevpSysOpsTaskBaselineRibbon devpSysOpsTaskBaselineRibbon;


	public DevpSysOpsTaskBaselineVO add(DevpSysOpsTaskBaselineAddDto addDto){
		DevpSysOpsTaskBaselineResult result = devpSysOpsTaskBaselineRibbon.add(addDto);

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
		DevpSysOpsTaskBaselineResult result = devpSysOpsTaskBaselineRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsTaskBaselineVO merge(Long id, DevpSysOpsTaskBaselineEditDto editDto){
		DevpSysOpsTaskBaselineResult result = devpSysOpsTaskBaselineRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsTaskBaselineVO find(Long id){
		DevpSysOpsTaskBaselineResult result = devpSysOpsTaskBaselineRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsTaskBaselineVO> list(PageSearchRequest<DevpSysOpsTaskBaselineCondition> pageSearchRequest) {
		DevpSysOpsTaskBaselinePageResult result = devpSysOpsTaskBaselineRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
