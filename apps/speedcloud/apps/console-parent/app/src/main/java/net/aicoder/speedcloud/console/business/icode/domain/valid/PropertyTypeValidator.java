package net.aicoder.speedcloud.console.business.icode.domain.valid;


import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PropertyTypeValidator implements Validator {

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
	    if(obj instanceof PropertyTypeAddDto){
            this.validateAddDto((PropertyTypeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param propertyType 属性类型
     * @param errors
     */
	public void validateAddDto(PropertyTypeAddDto propertyType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(propertyType.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(propertyType.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
	}
}