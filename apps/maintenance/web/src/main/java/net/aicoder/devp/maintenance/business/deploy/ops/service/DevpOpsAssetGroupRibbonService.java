package net.aicoder.devp.maintenance.business.deploy.ops.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.ops.DevpOpsAssetGroupRibbon;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsAssetGroupPageResult;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsAssetGroupResult;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetGroupCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetGroupAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetGroupEditDto;
import net.aicoder.devp.deploy.business.ops.vo.DevpOpsAssetGroupVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpOpsAssetGroupRibbonService")
public class DevpOpsAssetGroupRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAssetGroupRibbonService.class);


	@Autowired
	private DevpOpsAssetGroupRibbon devpOpsAssetGroupRibbon;


	public DevpOpsAssetGroupVO add(DevpOpsAssetGroupAddDto addDto){
		DevpOpsAssetGroupResult result = devpOpsAssetGroupRibbon.add(addDto);

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
		DevpOpsAssetGroupResult result = devpOpsAssetGroupRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}
	}
	public DevpOpsAssetGroupVO merge(Long id, DevpOpsAssetGroupEditDto editDto){
		DevpOpsAssetGroupResult result = devpOpsAssetGroupRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpOpsAssetGroupVO find(Long id){
		DevpOpsAssetGroupResult result = devpOpsAssetGroupRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpOpsAssetGroupVO> list(PageSearchRequest<DevpOpsAssetGroupCondition> pageSearchRequest) {
		DevpOpsAssetGroupPageResult result = devpOpsAssetGroupRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
