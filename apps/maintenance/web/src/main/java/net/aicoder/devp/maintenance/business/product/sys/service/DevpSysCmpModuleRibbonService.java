package net.aicoder.devp.maintenance.business.product.sys.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.DevpSysCmpModuleRibbon;
import net.aicoder.devp.product.client.sys.result.DevpSysCmpModulePageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysCmpModuleResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpModuleCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpModuleAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpModuleEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysCmpModuleVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysCmpModuleRibbonService")
public class DevpSysCmpModuleRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysCmpModuleRibbonService.class);


	@Autowired
	private DevpSysCmpModuleRibbon devpSysCmpModuleRibbon;


	public DevpSysCmpModuleVO add(DevpSysCmpModuleAddDto addDto){
		DevpSysCmpModuleResult result = devpSysCmpModuleRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("PRODUCT", "SYS", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysCmpModuleResult result = devpSysCmpModuleRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("PRODUCT", "SYS", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysCmpModuleVO merge(Long id, DevpSysCmpModuleEditDto editDto){
		DevpSysCmpModuleResult result = devpSysCmpModuleRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("PRODUCT", "SYS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysCmpModuleVO find(Long id){
		DevpSysCmpModuleResult result = devpSysCmpModuleRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("PRODUCT", "SYS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysCmpModuleVO> list(PageSearchRequest<DevpSysCmpModuleCondition> pageSearchRequest) {
		DevpSysCmpModulePageResult result = devpSysCmpModuleRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("PRODUCT", "SYS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
