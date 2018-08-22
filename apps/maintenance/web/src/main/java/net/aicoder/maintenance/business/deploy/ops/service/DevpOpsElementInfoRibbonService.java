package net.aicoder.maintenance.business.deploy.ops.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.ops.DevpOpsElementInfoRibbon;
import net.aicoder.devp.client.ops.result.DevpOpsElementInfoPageResult;
import net.aicoder.devp.client.ops.result.DevpOpsElementInfoResult;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoEditDto;
import net.aicoder.devp.business.ops.vo.DevpOpsElementInfoVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpOpsElementInfoRibbonService")
public class DevpOpsElementInfoRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsElementInfoRibbonService.class);


	@Autowired
	private DevpOpsElementInfoRibbon devpOpsElementInfoRibbon;


	public DevpOpsElementInfoVO add(DevpOpsElementInfoAddDto addDto){
		DevpOpsElementInfoResult result = devpOpsElementInfoRibbon.add(addDto);

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
		DevpOpsElementInfoResult result = devpOpsElementInfoRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}
	}
	public DevpOpsElementInfoVO merge(Long id, DevpOpsElementInfoEditDto editDto){
		DevpOpsElementInfoResult result = devpOpsElementInfoRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpOpsElementInfoVO find(Long id){
		DevpOpsElementInfoResult result = devpOpsElementInfoRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpOpsElementInfoVO> list(PageSearchRequest<DevpOpsElementInfoCondition> pageSearchRequest) {
		DevpOpsElementInfoPageResult result = devpOpsElementInfoRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
