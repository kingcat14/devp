package net.aicoder.speedcloud.console.business.icode.domain.valid;


import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EntityActionParameterValidator implements Validator {

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
	    if(obj instanceof EntityActionParameterAddDto){
            this.validateAddDto((EntityActionParameterAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param entityActionParameter 领域对象行为参数
     * @param errors
     */
	public void validateAddDto(EntityActionParameterAddDto entityActionParameter, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(entityActionParameter.getCode()) > 255){
			errors.rejectValue("code", null, "对象代码最长255个字符");
		}
		if(StringUtils.length(entityActionParameter.getName()) > 255){
			errors.rejectValue("name", null, "对象名称最长255个字符");
		}
		if(StringUtils.length(entityActionParameter.getMemo()) > 255){
			errors.rejectValue("memo", null, "备注最长255个字符");
		}
		if(StringUtils.length(entityActionParameter.getDescription()) > 255){
			errors.rejectValue("description", null, "描述最长255个字符");
		}
		if(StringUtils.length(entityActionParameter.getEntityAction()) > 255){
			errors.rejectValue("entityAction", null, "领域对象行为最长255个字符");
		}
	}
}