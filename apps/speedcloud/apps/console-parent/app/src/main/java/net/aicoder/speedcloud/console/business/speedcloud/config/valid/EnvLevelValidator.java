package net.aicoder.speedcloud.console.business.speedcloud.config.valid;


import net.aicoder.speedcloud.business.config.dto.EnvLevelAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EnvLevelValidator implements Validator {

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
	    if(obj instanceof EnvLevelAddDto){
            this.validateAddDto((EnvLevelAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param envLevel 环境级别
     * @param errors
     */
	public void validateAddDto(EnvLevelAddDto envLevel, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(envLevel.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(envLevel.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(envLevel.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
	}
}