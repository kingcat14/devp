package net.aicoder.speedcloud.console.business.icode.project.valid;


import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ComponentTypeValidator implements Validator {

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
	    if(obj instanceof ComponentTypeAddDto){
            this.validateAddDto((ComponentTypeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param componentType 组件类型
     * @param errors
     */
	public void validateAddDto(ComponentTypeAddDto componentType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(componentType.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(componentType.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(componentType.getCategory()) > 255){
			errors.rejectValue("category", null, "种类最长255个字符");
		}
		if(StringUtils.length(componentType.getIcon()) > 255){
			errors.rejectValue("icon", null, "默认图标最长255个字符");
		}
	}
}