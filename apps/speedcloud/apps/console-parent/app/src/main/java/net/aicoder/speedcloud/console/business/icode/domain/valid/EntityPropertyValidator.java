package net.aicoder.speedcloud.console.business.icode.domain.valid;


import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EntityPropertyValidator implements Validator {

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
	    if(obj instanceof EntityPropertyAddDto){
            this.validateAddDto((EntityPropertyAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param entityProperty 领域对象属性
     * @param errors
     */
	public void validateAddDto(EntityPropertyAddDto entityProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(entityProperty.getEntity()) > 255){
			errors.rejectValue("entity", null, "所属领域对象最长255个字符");
		}
		if(StringUtils.length(entityProperty.getCode()) > 255){
			errors.rejectValue("code", null, "属性代码最长255个字符");
		}
		if(StringUtils.length(entityProperty.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(entityProperty.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(entityProperty.getRelatedEntityId()) > 255){
			errors.rejectValue("relatedEntityId", null, "引用对象最长255个字符");
		}
		if(StringUtils.length(entityProperty.getRelatedEntityPropertyCode()) > 255){
			errors.rejectValue("relatedEntityPropertyCode", null, "引用对象属性代码最长255个字符");
		}
		if(StringUtils.length(entityProperty.getMemo()) > 255){
			errors.rejectValue("memo", null, "备注最长255个字符");
		}
	}
}