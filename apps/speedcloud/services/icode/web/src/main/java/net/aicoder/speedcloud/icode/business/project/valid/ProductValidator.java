package net.aicoder.speedcloud.icode.business.project.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.domain.Product;
import net.aicoder.speedcloud.icode.business.project.dto.ProductAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ProductCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ProductValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof ProductAddDto){
            this.validateProductAddDto((ProductAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ProductCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ProductCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param product 产品
     * @param errors
     */
	public void validateProductAddDto(ProductAddDto product, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(product.getProductName()) > 255){
			errors.rejectValue(Product.PROPERTY_PRODUCT_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(product.getProductCode()) > 255){
			errors.rejectValue(Product.PROPERTY_PRODUCT_CODE,null,"代码最长255个字符");
		}
	}
}