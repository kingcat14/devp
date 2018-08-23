package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsPipeNodeRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipeNodePageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipeNodeResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsPipeNodeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsPipeNodeRibbonService")
public class DevpSysOpsPipeNodeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipeNodeRibbonService.class);


	@Autowired
	private DevpSysOpsPipeNodeRibbon devpSysOpsPipeNodeRibbon;


	public DevpSysOpsPipeNodeVO add(DevpSysOpsPipeNodeAddDto addDto){
		DevpSysOpsPipeNodeResult result = devpSysOpsPipeNodeRibbon.add(addDto);

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
		DevpSysOpsPipeNodeResult result = devpSysOpsPipeNodeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsPipeNodeVO merge(Long id, DevpSysOpsPipeNodeEditDto editDto){
		DevpSysOpsPipeNodeResult result = devpSysOpsPipeNodeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsPipeNodeVO find(Long id){
		DevpSysOpsPipeNodeResult result = devpSysOpsPipeNodeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsPipeNodeVO> list(PageSearchRequest<DevpSysOpsPipeNodeCondition> pageSearchRequest) {
		DevpSysOpsPipeNodePageResult result = devpSysOpsPipeNodeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
