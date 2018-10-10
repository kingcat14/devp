package net.aicoder.speedcloud.console.business.speedCloud.env.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigAddDto;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AppEnvConfigValidator implements Validator {

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
	    if(obj instanceof AppEnvConfigAddDto){
            this.validateAddDto((AppEnvConfigAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param appEnvConfig 应用环境
     * @param errors
     */
		public void validateAddDto(AppEnvConfigAddDto appEnvConfig, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(appEnvConfig.getName()) > 255){
			errors.rejectValue("name", null, "环境名称最长255个字符");
		}
		if(StringUtils.length(appEnvConfig.getLevel()) > 255){
			errors.rejectValue("level", null, "环境级别最长255个字符");
		}
	}
}