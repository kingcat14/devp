package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsDockerParamRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsDockerParamPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsDockerParamResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsDockerParamVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsDockerParamRibbonService")
public class DevpSysOpsDockerParamRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerParamRibbonService.class);


	@Autowired
	private DevpSysOpsDockerParamRibbon devpSysOpsDockerParamRibbon;


	public DevpSysOpsDockerParamVO add(DevpSysOpsDockerParamAddDto addDto){
		DevpSysOpsDockerParamResult result = devpSysOpsDockerParamRibbon.add(addDto);

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
		DevpSysOpsDockerParamResult result = devpSysOpsDockerParamRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsDockerParamVO merge(Long id, DevpSysOpsDockerParamEditDto editDto){
		DevpSysOpsDockerParamResult result = devpSysOpsDockerParamRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsDockerParamVO find(Long id){
		DevpSysOpsDockerParamResult result = devpSysOpsDockerParamRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsDockerParamVO> list(PageSearchRequest<DevpSysOpsDockerParamCondition> pageSearchRequest) {
		DevpSysOpsDockerParamPageResult result = devpSysOpsDockerParamRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
