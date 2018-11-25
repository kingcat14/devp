package net.aicoder.speedcloud.console.business.icode.project.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.dto.ProductAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ProductCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ProductEditDto;
import net.aicoder.speedcloud.icode.business.project.vo.ProductVO;
import net.aicoder.speedcloud.icode.client.project.ProductRibbon;
import net.aicoder.speedcloud.icode.client.project.result.ProductPageResult;
import net.aicoder.speedcloud.icode.client.project.result.ProductResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("productRibbonService")
public class ProductRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRibbonService.class);


	@Autowired
	private ProductRibbon productRibbon;


	public ProductVO add(ProductAddDto addDto){
		ProductResult result = productRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		ProductResult result = productRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}
	}
	public ProductVO merge(String id, ProductEditDto editDto){
		ProductResult result = productRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ProductVO find(String id){
		ProductResult result = productRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ProductVO> list(PageSearchRequest<ProductCondition> pageSearchRequest) {
		ProductPageResult result = productRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
