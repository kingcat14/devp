package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsTaskCompileRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskCompilePageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskCompileResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskCompileVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskCompileRibbonService")
public class DevpSysOpsTaskCompileRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskCompileRibbonService.class);


	@Autowired
	private DevpSysOpsTaskCompileRibbon devpSysOpsTaskCompileRibbon;


	public DevpSysOpsTaskCompileVO add(DevpSysOpsTaskCompileAddDto addDto){
		DevpSysOpsTaskCompileResult result = devpSysOpsTaskCompileRibbon.add(addDto);

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
		DevpSysOpsTaskCompileResult result = devpSysOpsTaskCompileRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsTaskCompileVO merge(Long id, DevpSysOpsTaskCompileEditDto editDto){
		DevpSysOpsTaskCompileResult result = devpSysOpsTaskCompileRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsTaskCompileVO find(Long id){
		DevpSysOpsTaskCompileResult result = devpSysOpsTaskCompileRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsTaskCompileVO> list(PageSearchRequest<DevpSysOpsTaskCompileCondition> pageSearchRequest) {
		DevpSysOpsTaskCompilePageResult result = devpSysOpsTaskCompileRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
