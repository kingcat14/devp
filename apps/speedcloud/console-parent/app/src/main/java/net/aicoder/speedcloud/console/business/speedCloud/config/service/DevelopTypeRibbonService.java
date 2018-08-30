package net.aicoder.speedcloud.console.business.speedCloud.config.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.config.DevelopTypeRibbon;
import net.aicoder.speedcloud.client.config.result.DevelopTypePageResult;
import net.aicoder.speedcloud.client.config.result.DevelopTypeResult;
import net.aicoder.speedcloud.business.config.dto.DevelopTypeCondition;
import net.aicoder.speedcloud.business.config.dto.DevelopTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.DevelopTypeEditDto;
import net.aicoder.speedcloud.business.config.vo.DevelopTypeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("developTypeRibbonService")
public class DevelopTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevelopTypeRibbonService.class);


	@Autowired
	private DevelopTypeRibbon developTypeRibbon;


	public DevelopTypeVO add(DevelopTypeAddDto addDto){
		DevelopTypeResult result = developTypeRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevelopTypeResult result = developTypeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
	}
	public DevelopTypeVO merge(Long id, DevelopTypeEditDto editDto){
		DevelopTypeResult result = developTypeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevelopTypeVO find(Long id){
		DevelopTypeResult result = developTypeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevelopTypeVO> list(PageSearchRequest<DevelopTypeCondition> pageSearchRequest) {
		DevelopTypePageResult result = developTypeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
