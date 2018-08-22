package net.aicoder.maintenance.business.maintenance.software.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.maintenance.business.software.dto.SoftwareLicenseAddDto;
import net.aicoder.maintenance.business.software.dto.SoftwareLicenseCondition;
import net.aicoder.maintenance.business.software.dto.SoftwareLicenseEditDto;
import net.aicoder.maintenance.business.software.vo.SoftwareLicenseVO;
import net.aicoder.maintenance.spi.software.SoftwareLicenseRibbon;
import net.aicoder.maintenance.spi.software.result.SoftwareLicensePageResult;
import net.aicoder.maintenance.spi.software.result.SoftwareLicenseResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("softwareLicenseRibbonService")
public class SoftwareLicenseRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(SoftwareLicenseRibbonService.class);


	@Autowired
	private SoftwareLicenseRibbon softwareLicenseRibbon;


	public SoftwareLicenseVO add(SoftwareLicenseAddDto addDto){
		SoftwareLicenseResult result = softwareLicenseRibbon.add(addDto);

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
		SoftwareLicenseResult result = softwareLicenseRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}
	}
	public SoftwareLicenseVO merge(Long id, SoftwareLicenseEditDto editDto){
		SoftwareLicenseResult result = softwareLicenseRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public SoftwareLicenseVO find(Long id){
		SoftwareLicenseResult result = softwareLicenseRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<SoftwareLicenseVO> list(PageSearchRequest<SoftwareLicenseCondition> pageSearchRequest) {
		SoftwareLicensePageResult result = softwareLicenseRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
