package net.aicoder.maintenance.business.deploy.ops.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.ops.DevpOpsRequirementRibbon;
import net.aicoder.devp.client.ops.result.DevpOpsRequirementPageResult;
import net.aicoder.devp.client.ops.result.DevpOpsRequirementResult;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementEditDto;
import net.aicoder.devp.business.ops.vo.DevpOpsRequirementVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpOpsRequirementRibbonService")
public class DevpOpsRequirementRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsRequirementRibbonService.class);


	@Autowired
	private DevpOpsRequirementRibbon devpOpsRequirementRibbon;


	public DevpOpsRequirementVO add(DevpOpsRequirementAddDto addDto){
		DevpOpsRequirementResult result = devpOpsRequirementRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpOpsRequirementResult result = devpOpsRequirementRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}
	}
	public DevpOpsRequirementVO merge(Long id, DevpOpsRequirementEditDto editDto){
		DevpOpsRequirementResult result = devpOpsRequirementRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpOpsRequirementVO find(Long id){
		DevpOpsRequirementResult result = devpOpsRequirementRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpOpsRequirementVO> list(PageSearchRequest<DevpOpsRequirementCondition> pageSearchRequest) {
		DevpOpsRequirementPageResult result = devpOpsRequirementRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
