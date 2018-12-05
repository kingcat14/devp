package net.aicoder.speedcloud.console.business.speedcloud.env.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.env.dto.MachineAddDto;
import net.aicoder.speedcloud.business.env.dto.MachineCondition;
import net.aicoder.speedcloud.business.env.dto.MachineEditDto;
import net.aicoder.speedcloud.business.env.vo.MachineVO;
import net.aicoder.speedcloud.client.env.MachineClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("machineRibbonService")
public class MachineRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(MachineRibbonService.class);


	@Autowired
	private MachineClient machineClient;


	public MachineVO add(MachineAddDto addDto){
		RestResponse<MachineVO> result = machineClient.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		RestResponse<MachineVO> result = machineClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}
	}
	public MachineVO merge(String id, MachineEditDto editDto){
		RestResponse<MachineVO> result = machineClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public MachineVO find(String id){
		RestResponse<MachineVO> result = machineClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<MachineVO> list(PageSearchRequest<MachineCondition> pageSearchRequest) {
		RestResponse<PageContent<MachineVO>> result = machineClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "ENV", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
