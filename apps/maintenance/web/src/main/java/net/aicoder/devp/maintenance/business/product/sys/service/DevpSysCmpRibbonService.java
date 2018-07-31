package net.aicoder.devp.maintenance.business.product.sys.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.DevpSysCmpRibbon;
import net.aicoder.devp.product.client.sys.result.DevpSysCmpPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysCmpResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysCmpVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysCmpRibbonService")
public class DevpSysCmpRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysCmpRibbonService.class);


	@Autowired
	private DevpSysCmpRibbon devpSysCmpRibbon;


	public DevpSysCmpVO add(DevpSysCmpAddDto addDto){
		DevpSysCmpResult result = devpSysCmpRibbon.add(addDto);

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
		DevpSysCmpResult result = devpSysCmpRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("PRODUCT", "SYS", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysCmpVO merge(Long id, DevpSysCmpEditDto editDto){
		DevpSysCmpResult result = devpSysCmpRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("PRODUCT", "SYS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysCmpVO find(Long id){
		DevpSysCmpResult result = devpSysCmpRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("PRODUCT", "SYS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysCmpVO> list(PageSearchRequest<DevpSysCmpCondition> pageSearchRequest) {
		DevpSysCmpPageResult result = devpSysCmpRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("PRODUCT", "SYS", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
