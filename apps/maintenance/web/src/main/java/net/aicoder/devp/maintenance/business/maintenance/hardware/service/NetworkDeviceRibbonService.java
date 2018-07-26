package net.aicoder.devp.maintenance.business.maintenance.hardware.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.maintenance.client.hardware.NetworkDeviceRibbon;
import net.aicoder.devp.maintenance.client.hardware.result.NetworkDevicePageResult;
import net.aicoder.devp.maintenance.client.hardware.result.NetworkDeviceResult;
import net.aicoder.devp.maintenance.business.hardware.dto.NetworkDeviceCondition;
import net.aicoder.devp.maintenance.business.hardware.dto.NetworkDeviceAddDto;
import net.aicoder.devp.maintenance.business.hardware.dto.NetworkDeviceEditDto;
import net.aicoder.devp.maintenance.business.hardware.vo.NetworkDeviceVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("networkDeviceRibbonService")
public class NetworkDeviceRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(NetworkDeviceRibbonService.class);


	@Autowired
	private NetworkDeviceRibbon networkDeviceRibbon;


	public NetworkDeviceVO add(NetworkDeviceAddDto addDto){
		NetworkDeviceResult result = networkDeviceRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "HARDWARE", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		NetworkDeviceResult result = networkDeviceRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "HARDWARE", result.getCode()+"", result.getMessage());
		}
	}
	public NetworkDeviceVO merge(Long id, NetworkDeviceEditDto editDto){
		NetworkDeviceResult result = networkDeviceRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "HARDWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public NetworkDeviceVO find(Long id){
		NetworkDeviceResult result = networkDeviceRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "HARDWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<NetworkDeviceVO> list(PageSearchRequest<NetworkDeviceCondition> pageSearchRequest) {
		NetworkDevicePageResult result = networkDeviceRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "HARDWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
