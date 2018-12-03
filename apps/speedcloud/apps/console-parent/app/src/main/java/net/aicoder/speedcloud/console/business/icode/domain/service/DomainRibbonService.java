package net.aicoder.speedcloud.console.business.icode.domain.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
import net.aicoder.speedcloud.icode.client.domain.DomainFeignClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("domainRibbonService")
public class DomainRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainRibbonService.class);


//	@Autowired
//	private DomainRibbon domainRibbon;

	@Autowired
	private DomainFeignClient domainFeignClient;


	public DomainVO findTopDomain(String domainId){
		DomainVO domainVO = this.find(domainId);
		while(domainVO!=null && StringUtils.isNotEmpty(domainVO.getParent())){
			domainVO = this.find(domainVO.getParent());
		}
		return domainVO;
	}

	public DomainVO add(DomainAddDto addDto){
		RestResponse<DomainVO> result = domainFeignClient.add(addDto);
//		DomainResult result = domainRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		RestResponse<DomainVO> result = domainFeignClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
	}
	public DomainVO merge(String id, DomainEditDto editDto){
		RestResponse<DomainVO> result = domainFeignClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DomainVO copy(String id){
		RestResponse<DomainVO> result = domainFeignClient.copy(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DomainVO find(String id){
		RestResponse<DomainVO> result = domainFeignClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DomainVO> list(PageSearchRequest<DomainCondition> pageSearchRequest) {
		RestResponse<PageContent<DomainVO>> result = domainFeignClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}



}
