package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsTaskHostRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskHostPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskHostResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskHostVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskHostRibbonService")
public class DevpSysOpsTaskHostRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskHostRibbonService.class);


	@Autowired
	private DevpSysOpsTaskHostRibbon devpSysOpsTaskHostRibbon;


	public DevpSysOpsTaskHostVO add(DevpSysOpsTaskHostAddDto addDto){
		DevpSysOpsTaskHostResult result = devpSysOpsTaskHostRibbon.add(addDto);

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
		DevpSysOpsTaskHostResult result = devpSysOpsTaskHostRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsTaskHostVO merge(Long id, DevpSysOpsTaskHostEditDto editDto){
		DevpSysOpsTaskHostResult result = devpSysOpsTaskHostRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsTaskHostVO find(Long id){
		DevpSysOpsTaskHostResult result = devpSysOpsTaskHostRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsTaskHostVO> list(PageSearchRequest<DevpSysOpsTaskHostCondition> pageSearchRequest) {
		DevpSysOpsTaskHostPageResult result = devpSysOpsTaskHostRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
