package net.aicoder.speedcloud.console.business.icode.domain.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.ModelTypeVO;
import net.aicoder.speedcloud.icode.client.domain.ModelTypeRibbon;
import net.aicoder.speedcloud.icode.client.domain.result.ModelTypePageResult;
import net.aicoder.speedcloud.icode.client.domain.result.ModelTypeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("modelTypeRibbonService")
public class ModelTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelTypeRibbonService.class);


	@Autowired
	private ModelTypeRibbon modelTypeRibbon;


	public ModelTypeVO add(ModelTypeAddDto addDto){
		ModelTypeResult result = modelTypeRibbon.add(addDto);

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
		ModelTypeResult result = modelTypeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
	}
	public ModelTypeVO merge(String id, ModelTypeEditDto editDto){
		ModelTypeResult result = modelTypeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ModelTypeVO find(String id){
		ModelTypeResult result = modelTypeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ModelTypeVO> list(PageSearchRequest<ModelTypeCondition> pageSearchRequest) {
		ModelTypePageResult result = modelTypeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}