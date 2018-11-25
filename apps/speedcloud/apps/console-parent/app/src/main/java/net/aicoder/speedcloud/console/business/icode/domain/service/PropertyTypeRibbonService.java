package net.aicoder.speedcloud.console.business.icode.domain.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.PropertyTypeVO;
import net.aicoder.speedcloud.icode.client.domain.PropertyTypeRibbon;
import net.aicoder.speedcloud.icode.client.domain.result.PropertyTypeListResult;
import net.aicoder.speedcloud.icode.client.domain.result.PropertyTypePageResult;
import net.aicoder.speedcloud.icode.client.domain.result.PropertyTypeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("propertyTypeRibbonService")
public class PropertyTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyTypeRibbonService.class);


	@Autowired
	private PropertyTypeRibbon propertyTypeRibbon;


	public PropertyTypeVO add(PropertyTypeAddDto addDto){
		PropertyTypeResult result = propertyTypeRibbon.add(addDto);

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
		PropertyTypeResult result = propertyTypeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
	}
	public PropertyTypeVO merge(String id, PropertyTypeEditDto editDto){
		PropertyTypeResult result = propertyTypeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PropertyTypeVO find(String id){
		PropertyTypeResult result = propertyTypeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public List<PropertyTypeVO> reference(String componentId) {

		PropertyTypeListResult result = propertyTypeRibbon.referenceProperty(componentId);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public List<PropertyTypeVO> findAll() {

		PropertyTypeCondition condition = new PropertyTypeCondition();

		PageSearchRequest<PropertyTypeCondition> request = new PageSearchRequest();
		request.setLimit(Integer.MAX_VALUE);
		request.setPage(0);

		PropertyTypePageResult result = propertyTypeRibbon.list(request);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData().getContent();
	}
	public PageContent<PropertyTypeVO> list(PageSearchRequest<PropertyTypeCondition> pageSearchRequest) {
		PropertyTypePageResult result = propertyTypeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
