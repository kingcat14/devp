package net.aicoder.speedcloud.console.business.icode.domain.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
import net.aicoder.speedcloud.icode.client.domain.DomainRibbon;
import net.aicoder.speedcloud.icode.client.domain.result.DomainPageResult;
import net.aicoder.speedcloud.icode.client.domain.result.DomainResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("domainRibbonService")
public class DomainRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainRibbonService.class);


	@Autowired
	private DomainRibbon domainRibbon;


	public DomainVO add(DomainAddDto addDto){
		DomainResult result = domainRibbon.add(addDto);

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
		DomainResult result = domainRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
	}
	public DomainVO merge(String id, DomainEditDto editDto){
		DomainResult result = domainRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DomainVO find(String id){
		DomainResult result = domainRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DomainVO> list(PageSearchRequest<DomainCondition> pageSearchRequest) {
		DomainPageResult result = domainRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
