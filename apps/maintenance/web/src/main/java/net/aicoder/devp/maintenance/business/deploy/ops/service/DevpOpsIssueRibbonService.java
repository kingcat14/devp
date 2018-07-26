package net.aicoder.devp.maintenance.business.deploy.ops.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.ops.DevpOpsIssueRibbon;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsIssuePageResult;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsIssueResult;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsIssueCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsIssueAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsIssueEditDto;
import net.aicoder.devp.deploy.business.ops.vo.DevpOpsIssueVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpOpsIssueRibbonService")
public class DevpOpsIssueRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsIssueRibbonService.class);


	@Autowired
	private DevpOpsIssueRibbon devpOpsIssueRibbon;


	public DevpOpsIssueVO add(DevpOpsIssueAddDto addDto){
		DevpOpsIssueResult result = devpOpsIssueRibbon.add(addDto);

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
		DevpOpsIssueResult result = devpOpsIssueRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}
	}
	public DevpOpsIssueVO merge(Long id, DevpOpsIssueEditDto editDto){
		DevpOpsIssueResult result = devpOpsIssueRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpOpsIssueVO find(Long id){
		DevpOpsIssueResult result = devpOpsIssueRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpOpsIssueVO> list(PageSearchRequest<DevpOpsIssueCondition> pageSearchRequest) {
		DevpOpsIssuePageResult result = devpOpsIssueRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
