package net.aicoder.maintenance.business.deploy.ops.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.ops.DevpOpsParasDefineRibbon;
import net.aicoder.devp.client.ops.result.DevpOpsParasDefinePageResult;
import net.aicoder.devp.client.ops.result.DevpOpsParasDefineResult;
import net.aicoder.devp.business.ops.dto.DevpOpsParasDefineCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsParasDefineAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsParasDefineEditDto;
import net.aicoder.devp.business.ops.vo.DevpOpsParasDefineVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpOpsParasDefineRibbonService")
public class DevpOpsParasDefineRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsParasDefineRibbonService.class);


	@Autowired
	private DevpOpsParasDefineRibbon devpOpsParasDefineRibbon;


	public DevpOpsParasDefineVO add(DevpOpsParasDefineAddDto addDto){
		DevpOpsParasDefineResult result = devpOpsParasDefineRibbon.add(addDto);

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
		DevpOpsParasDefineResult result = devpOpsParasDefineRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}
	}
	public DevpOpsParasDefineVO merge(Long id, DevpOpsParasDefineEditDto editDto){
		DevpOpsParasDefineResult result = devpOpsParasDefineRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpOpsParasDefineVO find(Long id){
		DevpOpsParasDefineResult result = devpOpsParasDefineRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpOpsParasDefineVO> list(PageSearchRequest<DevpOpsParasDefineCondition> pageSearchRequest) {
		DevpOpsParasDefinePageResult result = devpOpsParasDefineRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
