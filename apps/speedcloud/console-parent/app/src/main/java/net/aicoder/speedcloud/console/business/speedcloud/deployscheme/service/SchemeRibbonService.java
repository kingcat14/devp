package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.SchemeRibbon;
import net.aicoder.speedcloud.client.deployscheme.result.SchemePageResult;
import net.aicoder.speedcloud.client.deployscheme.result.SchemeResult;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.SchemeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("schemeRibbonService")
public class SchemeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchemeRibbonService.class);


	@Autowired
	private SchemeRibbon schemeRibbon;


	public SchemeVO add(SchemeAddDto addDto){
		SchemeResult result = schemeRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		SchemeResult result = schemeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}
	}
	public SchemeVO merge(Long id, SchemeEditDto editDto){
		SchemeResult result = schemeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public SchemeVO find(Long id){
		SchemeResult result = schemeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<SchemeVO> list(PageSearchRequest<SchemeCondition> pageSearchRequest) {
		SchemePageResult result = schemeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "DEPLOYSCHEME", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
