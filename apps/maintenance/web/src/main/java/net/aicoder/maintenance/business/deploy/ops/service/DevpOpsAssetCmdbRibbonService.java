package net.aicoder.maintenance.business.deploy.ops.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.ops.DevpOpsAssetCmdbRibbon;
import net.aicoder.devp.client.ops.result.DevpOpsAssetCmdbPageResult;
import net.aicoder.devp.client.ops.result.DevpOpsAssetCmdbResult;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbEditDto;
import net.aicoder.devp.business.ops.vo.DevpOpsAssetCmdbVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpOpsAssetCmdbRibbonService")
public class DevpOpsAssetCmdbRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAssetCmdbRibbonService.class);


	@Autowired
	private DevpOpsAssetCmdbRibbon devpOpsAssetCmdbRibbon;


	public DevpOpsAssetCmdbVO add(DevpOpsAssetCmdbAddDto addDto){
		DevpOpsAssetCmdbResult result = devpOpsAssetCmdbRibbon.add(addDto);

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
		DevpOpsAssetCmdbResult result = devpOpsAssetCmdbRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}
	}
	public DevpOpsAssetCmdbVO merge(Long id, DevpOpsAssetCmdbEditDto editDto){
		DevpOpsAssetCmdbResult result = devpOpsAssetCmdbRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpOpsAssetCmdbVO find(Long id){
		DevpOpsAssetCmdbResult result = devpOpsAssetCmdbRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpOpsAssetCmdbVO> list(PageSearchRequest<DevpOpsAssetCmdbCondition> pageSearchRequest) {
		DevpOpsAssetCmdbPageResult result = devpOpsAssetCmdbRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
