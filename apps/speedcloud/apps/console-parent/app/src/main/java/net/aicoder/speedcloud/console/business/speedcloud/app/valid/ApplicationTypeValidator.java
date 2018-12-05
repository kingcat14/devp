package net.aicoder.speedcloud.console.business.speedcloud.app.valid;


import net.aicoder.speedcloud.business.app.dto.ApplicationTypeAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ApplicationTypeValidator implements Validator {

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
	    if(obj instanceof ApplicationTypeAddDto){
            this.validateAddDto((ApplicationTypeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param applicationType 应用类型
     * @param errors
     */
	public void validateAddDto(ApplicationTypeAddDto applicationType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(applicationType.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(applicationType.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(applicationType.getCategory()) > 255){
			errors.rejectValue("category", null, "种类最长255个字符");
		}
		if(StringUtils.length(applicationType.getIcon()) > 255){
			errors.rejectValue("icon", null, "默认图标最长255个字符");
		}
	}
}