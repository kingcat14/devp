package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsDockerPathRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsDockerPathPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsDockerPathResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsDockerPathVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsDockerPathRibbonService")
public class DevpSysOpsDockerPathRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerPathRibbonService.class);


	@Autowired
	private DevpSysOpsDockerPathRibbon devpSysOpsDockerPathRibbon;


	public DevpSysOpsDockerPathVO add(DevpSysOpsDockerPathAddDto addDto){
		DevpSysOpsDockerPathResult result = devpSysOpsDockerPathRibbon.add(addDto);

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
		DevpSysOpsDockerPathResult result = devpSysOpsDockerPathRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsDockerPathVO merge(Long id, DevpSysOpsDockerPathEditDto editDto){
		DevpSysOpsDockerPathResult result = devpSysOpsDockerPathRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsDockerPathVO find(Long id){
		DevpSysOpsDockerPathResult result = devpSysOpsDockerPathRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsDockerPathVO> list(PageSearchRequest<DevpSysOpsDockerPathCondition> pageSearchRequest) {
		DevpSysOpsDockerPathPageResult result = devpSysOpsDockerPathRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
