package net.aicoder.maintenance.business.maintenance.software.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.maintenance.business.software.dto.InfrastructuralSoftwareAddDto;
import net.aicoder.maintenance.business.software.dto.InfrastructuralSoftwareCondition;
import net.aicoder.maintenance.business.software.dto.InfrastructuralSoftwareEditDto;
import net.aicoder.maintenance.business.software.vo.InfrastructuralSoftwareVO;
import net.aicoder.maintenance.spi.software.InfrastructuralSoftwareRibbon;
import net.aicoder.maintenance.spi.software.result.InfrastructuralSoftwarePageResult;
import net.aicoder.maintenance.spi.software.result.InfrastructuralSoftwareResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("infrastructuralSoftwareRibbonService")
public class InfrastructuralSoftwareRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfrastructuralSoftwareRibbonService.class);


	@Autowired
	private InfrastructuralSoftwareRibbon infrastructuralSoftwareRibbon;


	public InfrastructuralSoftwareVO add(InfrastructuralSoftwareAddDto addDto){
		InfrastructuralSoftwareResult result = infrastructuralSoftwareRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		InfrastructuralSoftwareResult result = infrastructuralSoftwareRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}
	}
	public InfrastructuralSoftwareVO merge(Long id, InfrastructuralSoftwareEditDto editDto){
		InfrastructuralSoftwareResult result = infrastructuralSoftwareRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public InfrastructuralSoftwareVO find(Long id){
		InfrastructuralSoftwareResult result = infrastructuralSoftwareRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<InfrastructuralSoftwareVO> list(PageSearchRequest<InfrastructuralSoftwareCondition> pageSearchRequest) {
		InfrastructuralSoftwarePageResult result = infrastructuralSoftwareRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
