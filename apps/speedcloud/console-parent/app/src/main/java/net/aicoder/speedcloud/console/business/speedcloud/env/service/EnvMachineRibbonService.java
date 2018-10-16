package net.aicoder.speedcloud.console.business.speedcloud.env.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.env.EnvMachineRibbon;
import net.aicoder.speedcloud.client.env.result.EnvMachinePageResult;
import net.aicoder.speedcloud.client.env.result.EnvMachineResult;
import net.aicoder.speedcloud.business.env.dto.EnvMachineCondition;
import net.aicoder.speedcloud.business.env.dto.EnvMachineAddDto;
import net.aicoder.speedcloud.business.env.dto.EnvMachineEditDto;
import net.aicoder.speedcloud.business.env.vo.EnvMachineVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("envMachineRibbonService")
public class EnvMachineRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvMachineRibbonService.class);


	@Autowired
	private EnvMachineRibbon envMachineRibbon;


	public EnvMachineVO add(EnvMachineAddDto addDto){
		EnvMachineResult result = envMachineRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		EnvMachineResult result = envMachineRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}
	}
	public EnvMachineVO merge(Long id, EnvMachineEditDto editDto){
		EnvMachineResult result = envMachineRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public EnvMachineVO find(Long id){
		EnvMachineResult result = envMachineRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<EnvMachineVO> list(PageSearchRequest<EnvMachineCondition> pageSearchRequest) {
		EnvMachinePageResult result = envMachineRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
