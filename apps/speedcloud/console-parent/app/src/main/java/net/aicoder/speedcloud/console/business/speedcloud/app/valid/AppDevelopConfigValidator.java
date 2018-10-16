package net.aicoder.speedcloud.console.business.speedcloud.app.valid;


import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigAddDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AppDevelopConfigValidator implements Validator {

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
	    if(obj instanceof AppDevelopConfigAddDto){
            this.validateAddDto((AppDevelopConfigAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param appDevelopConfig 应用开发配置
     * @param errors
     */
	public void validateAddDto(AppDevelopConfigAddDto appDevelopConfig, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == appDevelopConfig.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户id不能为空");
		}

		//验证长度
		if(StringUtils.length(appDevelopConfig.getTestDatabase()) > 255){
			errors.rejectValue("testDatabase", null, "测试环境DB最长255个字符");
		}
		if(StringUtils.length(appDevelopConfig.getTestDomainName()) > 255){
			errors.rejectValue("testDomainName", null, "测试环境域名最长255个字符");
		}
		if(StringUtils.length(appDevelopConfig.getProductionDatabase()) > 255){
			errors.rejectValue("productionDatabase", null, "生产环境DB最长255个字符");
		}
		if(StringUtils.length(appDevelopConfig.getProductionDomainName()) > 255){
			errors.rejectValue("productionDomainName", null, "生产环境域名最长255个字符");
		}
	}
}