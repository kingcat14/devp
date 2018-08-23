package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsTaskPublishRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskPublishPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskPublishResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskPublishVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskPublishRibbonService")
public class DevpSysOpsTaskPublishRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskPublishRibbonService.class);


	@Autowired
	private DevpSysOpsTaskPublishRibbon devpSysOpsTaskPublishRibbon;


	public DevpSysOpsTaskPublishVO add(DevpSysOpsTaskPublishAddDto addDto){
		DevpSysOpsTaskPublishResult result = devpSysOpsTaskPublishRibbon.add(addDto);

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
		DevpSysOpsTaskPublishResult result = devpSysOpsTaskPublishRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsTaskPublishVO merge(Long id, DevpSysOpsTaskPublishEditDto editDto){
		DevpSysOpsTaskPublishResult result = devpSysOpsTaskPublishRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsTaskPublishVO find(Long id){
		DevpSysOpsTaskPublishResult result = devpSysOpsTaskPublishRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsTaskPublishVO> list(PageSearchRequest<DevpSysOpsTaskPublishCondition> pageSearchRequest) {
		DevpSysOpsTaskPublishPageResult result = devpSysOpsTaskPublishRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
