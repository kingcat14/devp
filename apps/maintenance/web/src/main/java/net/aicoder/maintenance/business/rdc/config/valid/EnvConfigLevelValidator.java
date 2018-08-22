package net.aicoder.maintenance.business.rdc.config.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.maintenance.business.rdc.config.domain.EnvConfigLevel;
import net.aicoder.maintenance.business.rdc.config.dto.EnvConfigLevelAddDto;
import net.aicoder.maintenance.business.rdc.config.dto.EnvConfigLevelEditDto;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class EnvConfigLevelValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(EnvConfigLevelAddDto.class.equals(aClass))
			return true;
		if(EnvConfigLevelEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return false;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof EnvConfigLevelAddDto){
            this.validateEnvConfigLevelAddDto((EnvConfigLevelAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param envConfigLevel 环境级别
     * @param errors
     */
	public void validateEnvConfigLevelAddDto(EnvConfigLevelAddDto envConfigLevel, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(envConfigLevel.getName()) > 255){
			errors.rejectValue(EnvConfigLevel.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(envConfigLevel.getCode()) > 255){
			errors.rejectValue(EnvConfigLevel.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(envConfigLevel.getType()) > 255){
			errors.rejectValue(EnvConfigLevel.PROPERTY_TYPE,null,"类型最长255个字符");
		}
	}
}