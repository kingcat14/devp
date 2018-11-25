package net.aicoder.speedcloud.console.business.icode.domain.valid;


import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EntityActionParameterPropertyValidator implements Validator {

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
	    if(obj instanceof EntityActionParameterPropertyAddDto){
            this.validateAddDto((EntityActionParameterPropertyAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param entityActionParameterProperty 领域对象行为参数属性
     * @param errors
     */
	public void validateAddDto(EntityActionParameterPropertyAddDto entityActionParameterProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(entityActionParameterProperty.getActionParameter()) > 255){
			errors.rejectValue("actionParameter", null, "所属行为参数最长255个字符");
		}
		if(StringUtils.length(entityActionParameterProperty.getCode()) > 255){
			errors.rejectValue("code", null, "属性代码最长255个字符");
		}
		if(StringUtils.length(entityActionParameterProperty.getName()) > 255){
			errors.rejectValue("name", null, "属性名称最长255个字符");
		}
		if(StringUtils.length(entityActionParameterProperty.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(entityActionParameterProperty.getWrapperType()) > 255){
			errors.rejectValue("wrapperType", null, "外覆类型最长255个字符");
		}
		if(StringUtils.length(entityActionParameterProperty.getMemo()) > 255){
			errors.rejectValue("memo", null, "备注最长255个字符");
		}
	}
}