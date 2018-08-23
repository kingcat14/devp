package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsTaskConfigRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskConfigPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskConfigResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskConfigVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskConfigRibbonService")
public class DevpSysOpsTaskConfigRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskConfigRibbonService.class);


	@Autowired
	private DevpSysOpsTaskConfigRibbon devpSysOpsTaskConfigRibbon;


	public DevpSysOpsTaskConfigVO add(DevpSysOpsTaskConfigAddDto addDto){
		DevpSysOpsTaskConfigResult result = devpSysOpsTaskConfigRibbon.add(addDto);

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
		DevpSysOpsTaskConfigResult result = devpSysOpsTaskConfigRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsTaskConfigVO merge(Long id, DevpSysOpsTaskConfigEditDto editDto){
		DevpSysOpsTaskConfigResult result = devpSysOpsTaskConfigRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsTaskConfigVO find(Long id){
		DevpSysOpsTaskConfigResult result = devpSysOpsTaskConfigRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsTaskConfigVO> list(PageSearchRequest<DevpSysOpsTaskConfigCondition> pageSearchRequest) {
		DevpSysOpsTaskConfigPageResult result = devpSysOpsTaskConfigRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
