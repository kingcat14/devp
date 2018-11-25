package net.aicoder.speedcloud.console.business.icode.project.valid;


import net.aicoder.speedcloud.icode.business.project.dto.ComponentAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ComponentValidator implements Validator {

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
	    if(obj instanceof ComponentAddDto){
            this.validateAddDto((ComponentAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param component 组件
     * @param errors
     */
	public void validateAddDto(ComponentAddDto component, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(component.getName()) > 255){
			errors.rejectValue("name", null, "组件名称最长255个字符");
		}
		if(StringUtils.length(component.getCode()) > 255){
			errors.rejectValue("code", null, "组件代码最长255个字符");
		}
		if(StringUtils.length(component.getBasePackage()) > 255){
			errors.rejectValue("basePackage", null, "基础包最长255个字符");
		}
		if(StringUtils.length(component.getDescription()) > 255){
			errors.rejectValue("description", null, "描述最长255个字符");
		}
		if(StringUtils.length(component.getTplSet()) > 255){
			errors.rejectValue("tplSet", null, "代码模板最长255个字符");
		}
		if(StringUtils.length(component.getGroupCode()) > 255){
			errors.rejectValue("groupCode", null, "分组代码最长255个字符");
		}
		if(StringUtils.length(component.getProduct()) > 255){
			errors.rejectValue("product", null, "所属产品最长255个字符");
		}
	}
}