package com.kingzoo.kingcat.project.icode4.business.system.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.ProductAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(ProductAddRequest.class.equals(aClass))
            return true;
		return Product.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof Product){
            this.validateProduct((Product)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param product Product
     * @param errors
     */
	public void validateProduct(Product product, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(product.getProductName())){
			errors.rejectValue(Product.PROPERTY_PRODUCT_NAME, "EMPTY_"+Product.PROPERTY_PRODUCT_NAME, "产品名称不能为空");
		}
		if(StringUtils.isEmpty(product.getProductCode())){
			errors.rejectValue(Product.PROPERTY_PRODUCT_CODE, "EMPTY_"+Product.PROPERTY_PRODUCT_CODE, "产品代码不能为空");
		}

		//验证长度
		if(StringUtils.length(product.getProductName()) > 255){
			errors.rejectValue(Product.PROPERTY_PRODUCT_NAME,null,"产品名称最长255个字符");
		}
		if(StringUtils.length(product.getProductCode()) > 255){
			errors.rejectValue(Product.PROPERTY_PRODUCT_CODE,null,"产品代码最长255个字符");
		}
	}
}