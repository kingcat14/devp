package net.aicoder.maintenance.business.deploy.ops.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.ops.DevpOpsAttachmentRibbon;
import net.aicoder.devp.client.ops.result.DevpOpsAttachmentPageResult;
import net.aicoder.devp.client.ops.result.DevpOpsAttachmentResult;
import net.aicoder.devp.business.ops.dto.DevpOpsAttachmentCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsAttachmentAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAttachmentEditDto;
import net.aicoder.devp.business.ops.vo.DevpOpsAttachmentVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpOpsAttachmentRibbonService")
public class DevpOpsAttachmentRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAttachmentRibbonService.class);


	@Autowired
	private DevpOpsAttachmentRibbon devpOpsAttachmentRibbon;


	public DevpOpsAttachmentVO add(DevpOpsAttachmentAddDto addDto){
		DevpOpsAttachmentResult result = devpOpsAttachmentRibbon.add(addDto);

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
		DevpOpsAttachmentResult result = devpOpsAttachmentRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}
	}
	public DevpOpsAttachmentVO merge(Long id, DevpOpsAttachmentEditDto editDto){
		DevpOpsAttachmentResult result = devpOpsAttachmentRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpOpsAttachmentVO find(Long id){
		DevpOpsAttachmentResult result = devpOpsAttachmentRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpOpsAttachmentVO> list(PageSearchRequest<DevpOpsAttachmentCondition> pageSearchRequest) {
		DevpOpsAttachmentPageResult result = devpOpsAttachmentRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
