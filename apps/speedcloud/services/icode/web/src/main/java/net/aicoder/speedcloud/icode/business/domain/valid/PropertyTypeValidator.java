package net.aicoder.speedcloud.icode.business.domain.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.domain.PropertyType;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeCondition;
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
            this.validatePropertyTypeAddDto((PropertyTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PropertyTypeCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PropertyTypeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param propertyType 属性类型
     * @param errors
     */
	public void validatePropertyTypeAddDto(PropertyTypeAddDto propertyType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(propertyType.getCode()) > 255){
			errors.rejectValue(PropertyType.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(propertyType.getName()) > 255){
			errors.rejectValue(PropertyType.PROPERTY_NAME,null,"名称最长255个字符");
		}
	}
}