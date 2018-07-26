package net.aicoder.devp.maintenance.business.deploy.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.deploy.DevpSysDpyCmpRefRibbon;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyCmpRefPageResult;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyCmpRefResult;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyCmpRefCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyCmpRefAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyCmpRefEditDto;
import net.aicoder.devp.deploy.business.deploy.vo.DevpSysDpyCmpRefVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpyCmpRefRibbonService")
public class DevpSysDpyCmpRefRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyCmpRefRibbonService.class);


	@Autowired
	private DevpSysDpyCmpRefRibbon devpSysDpyCmpRefRibbon;


	public DevpSysDpyCmpRefVO add(DevpSysDpyCmpRefAddDto addDto){
		DevpSysDpyCmpRefResult result = devpSysDpyCmpRefRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysDpyCmpRefResult result = devpSysDpyCmpRefRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpyCmpRefVO merge(Long id, DevpSysDpyCmpRefEditDto editDto){
		DevpSysDpyCmpRefResult result = devpSysDpyCmpRefRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpyCmpRefVO find(Long id){
		DevpSysDpyCmpRefResult result = devpSysDpyCmpRefRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpyCmpRefVO> list(PageSearchRequest<DevpSysDpyCmpRefCondition> pageSearchRequest) {
		DevpSysDpyCmpRefPageResult result = devpSysDpyCmpRefRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
