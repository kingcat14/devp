package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsTaskDockerRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskDockerPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskDockerResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskDockerVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskDockerRibbonService")
public class DevpSysOpsTaskDockerRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskDockerRibbonService.class);


	@Autowired
	private DevpSysOpsTaskDockerRibbon devpSysOpsTaskDockerRibbon;


	public DevpSysOpsTaskDockerVO add(DevpSysOpsTaskDockerAddDto addDto){
		DevpSysOpsTaskDockerResult result = devpSysOpsTaskDockerRibbon.add(addDto);

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
		DevpSysOpsTaskDockerResult result = devpSysOpsTaskDockerRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsTaskDockerVO merge(Long id, DevpSysOpsTaskDockerEditDto editDto){
		DevpSysOpsTaskDockerResult result = devpSysOpsTaskDockerRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsTaskDockerVO find(Long id){
		DevpSysOpsTaskDockerResult result = devpSysOpsTaskDockerRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsTaskDockerVO> list(PageSearchRequest<DevpSysOpsTaskDockerCondition> pageSearchRequest) {
		DevpSysOpsTaskDockerPageResult result = devpSysOpsTaskDockerRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
