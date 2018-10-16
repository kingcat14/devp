package net.aicoder.speedcloud.console.business.speedcloud.deploy.valid;


import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpySchemeAddDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpySchemeValidator implements Validator {

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
	    if(obj instanceof DevpSysDpySchemeAddDto){
            this.validateAddDto((DevpSysDpySchemeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysDpyScheme 部署方案
     * @param errors
     */
		public void validateAddDto(DevpSysDpySchemeAddDto devpSysDpyScheme, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyScheme.getName()) > 255){
			errors.rejectValue("name", null, "方案名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getCode()) > 255){
			errors.rejectValue("code", null, "方案代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getAlias()) > 255){
			errors.rejectValue("alias", null, "方案别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getType()) > 255){
			errors.rejectValue("type", null, "方案类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getVersion()) > 255){
			errors.rejectValue("version", null, "版本最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getVerPostfix()) > 255){
			errors.rejectValue("verPostfix", null, "版本标识后缀最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
	}
}