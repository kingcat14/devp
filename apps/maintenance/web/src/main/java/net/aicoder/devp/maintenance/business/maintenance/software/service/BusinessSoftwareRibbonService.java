package net.aicoder.devp.maintenance.business.maintenance.software.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.maintenance.client.software.BusinessSoftwareRibbon;
import net.aicoder.devp.maintenance.client.software.result.BusinessSoftwarePageResult;
import net.aicoder.devp.maintenance.client.software.result.BusinessSoftwareResult;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareCondition;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareAddDto;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareEditDto;
import net.aicoder.devp.maintenance.business.software.vo.BusinessSoftwareVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("businessSoftwareRibbonService")
public class BusinessSoftwareRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessSoftwareRibbonService.class);


	@Autowired
	private BusinessSoftwareRibbon businessSoftwareRibbon;


	public BusinessSoftwareVO add(BusinessSoftwareAddDto addDto){
		BusinessSoftwareResult result = businessSoftwareRibbon.add(addDto);

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
		BusinessSoftwareResult result = businessSoftwareRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}
	}
	public BusinessSoftwareVO merge(Long id, BusinessSoftwareEditDto editDto){
		BusinessSoftwareResult result = businessSoftwareRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public BusinessSoftwareVO find(Long id){
		BusinessSoftwareResult result = businessSoftwareRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<BusinessSoftwareVO> list(PageSearchRequest<BusinessSoftwareCondition> pageSearchRequest) {
		BusinessSoftwarePageResult result = businessSoftwareRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "SOFTWARE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
