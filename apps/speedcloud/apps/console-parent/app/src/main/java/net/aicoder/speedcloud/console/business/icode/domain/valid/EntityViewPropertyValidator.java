package net.aicoder.speedcloud.console.business.icode.domain.valid;


import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EntityViewPropertyValidator implements Validator {

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
	    if(obj instanceof EntityViewPropertyAddDto){
            this.validateAddDto((EntityViewPropertyAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param entityViewProperty 领域对象展现属性
     * @param errors
     */
	public void validateAddDto(EntityViewPropertyAddDto entityViewProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(entityViewProperty.getEntity()) > 255){
			errors.rejectValue("entity", null, "所属领域对象最长255个字符");
		}
		if(StringUtils.length(entityViewProperty.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(entityViewProperty.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
	}
}