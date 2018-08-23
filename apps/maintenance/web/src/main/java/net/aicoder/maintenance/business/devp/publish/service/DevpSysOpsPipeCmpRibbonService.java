package net.aicoder.maintenance.business.devp.publish.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.DevpSysOpsPipeCmpRibbon;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipeCmpPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipeCmpResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsPipeCmpVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysOpsPipeCmpRibbonService")
public class DevpSysOpsPipeCmpRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipeCmpRibbonService.class);


	@Autowired
	private DevpSysOpsPipeCmpRibbon devpSysOpsPipeCmpRibbon;


	public DevpSysOpsPipeCmpVO add(DevpSysOpsPipeCmpAddDto addDto){
		DevpSysOpsPipeCmpResult result = devpSysOpsPipeCmpRibbon.add(addDto);

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
		DevpSysOpsPipeCmpResult result = devpSysOpsPipeCmpRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysOpsPipeCmpVO merge(Long id, DevpSysOpsPipeCmpEditDto editDto){
		DevpSysOpsPipeCmpResult result = devpSysOpsPipeCmpRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysOpsPipeCmpVO find(Long id){
		DevpSysOpsPipeCmpResult result = devpSysOpsPipeCmpRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysOpsPipeCmpVO> list(PageSearchRequest<DevpSysOpsPipeCmpCondition> pageSearchRequest) {
		DevpSysOpsPipeCmpPageResult result = devpSysOpsPipeCmpRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEVP", "PUBLISH", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
