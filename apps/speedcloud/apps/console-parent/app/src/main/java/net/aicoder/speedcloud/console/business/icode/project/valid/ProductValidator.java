package net.aicoder.speedcloud.console.business.icode.project.valid;


import net.aicoder.speedcloud.icode.business.project.dto.ProductAddDto;
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
            this.validateAddDto((ProductAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param product 产品
     * @param errors
     */
	public void validateAddDto(ProductAddDto product, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(product.getProductName()) > 255){
			errors.rejectValue("productName", null, "名称最长255个字符");
		}
		if(StringUtils.length(product.getProductCode()) > 255){
			errors.rejectValue("productCode", null, "代码最长255个字符");
		}
	}
}