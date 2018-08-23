package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsTaskDeployRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskDeployPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskDeployResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskDeployVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskDeployRibbonService")
public class DevpSysOpsTaskDeployRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskDeployRibbonService.class);


	@Autowired
	private DevpSysOpsTaskDeployRibbon devpSysOpsTaskDeployRibbon;


	public DevpSysOpsTaskDeployVO add(DevpSysOpsTaskDeployAddDto addDto){
		DevpSysOpsTaskDeployResult result = devpSysOpsTaskDeployRibbon.add(addDto);

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
		DevpSysOpsTaskDeployResult result = devpSysOpsTaskDeployRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsTaskDeployVO merge(Long id, DevpSysOpsTaskDeployEditDto editDto){
		DevpSysOpsTaskDeployResult result = devpSysOpsTaskDeployRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsTaskDeployVO find(Long id){
		DevpSysOpsTaskDeployResult result = devpSysOpsTaskDeployRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsTaskDeployVO> list(PageSearchRequest<DevpSysOpsTaskDeployCondition> pageSearchRequest) {
		DevpSysOpsTaskDeployPageResult result = devpSysOpsTaskDeployRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
