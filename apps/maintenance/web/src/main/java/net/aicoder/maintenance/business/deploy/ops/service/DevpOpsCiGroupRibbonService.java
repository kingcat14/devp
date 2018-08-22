package net.aicoder.maintenance.business.deploy.ops.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.ops.DevpOpsCiGroupRibbon;
import net.aicoder.devp.client.ops.result.DevpOpsCiGroupPageResult;
import net.aicoder.devp.client.ops.result.DevpOpsCiGroupResult;
import net.aicoder.devp.business.ops.dto.DevpOpsCiGroupCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsCiGroupAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsCiGroupEditDto;
import net.aicoder.devp.business.ops.vo.DevpOpsCiGroupVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpOpsCiGroupRibbonService")
public class DevpOpsCiGroupRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsCiGroupRibbonService.class);


	@Autowired
	private DevpOpsCiGroupRibbon devpOpsCiGroupRibbon;


	public DevpOpsCiGroupVO add(DevpOpsCiGroupAddDto addDto){
		DevpOpsCiGroupResult result = devpOpsCiGroupRibbon.add(addDto);

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
		DevpOpsCiGroupResult result = devpOpsCiGroupRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}
	}
	public DevpOpsCiGroupVO merge(Long id, DevpOpsCiGroupEditDto editDto){
		DevpOpsCiGroupResult result = devpOpsCiGroupRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpOpsCiGroupVO find(Long id){
		DevpOpsCiGroupResult result = devpOpsCiGroupRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpOpsCiGroupVO> list(PageSearchRequest<DevpOpsCiGroupCondition> pageSearchRequest) {
		DevpOpsCiGroupPageResult result = devpOpsCiGroupRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "OPS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
