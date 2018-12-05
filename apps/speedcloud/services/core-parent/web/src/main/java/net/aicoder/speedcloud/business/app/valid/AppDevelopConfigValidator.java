package net.aicoder.speedcloud.business.app.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.domain.AppDevelopConfig;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
            this.validateAppDevelopConfigAddDto((AppDevelopConfigAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AppDevelopConfigCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AppDevelopConfigCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param appDevelopConfig 应用开发配置
     * @param errors
     */
	public void validateAppDevelopConfigAddDto(AppDevelopConfigAddDto appDevelopConfig, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(appDevelopConfig.getApp()) > 255){
			errors.rejectValue(AppDevelopConfig.PROPERTY_APP,null,"应用最长255个字符");
		}
		if(StringUtils.length(appDevelopConfig.getDevelopDatabase()) > 255){
			errors.rejectValue(AppDevelopConfig.PROPERTY_DEVELOP_DATABASE,null,"开发环境DB最长255个字符");
		}
		if(StringUtils.length(appDevelopConfig.getDevelopDomainName()) > 255){
			errors.rejectValue(AppDevelopConfig.PROPERTY_DEVELOP_DOMAIN_NAME,null,"开发环境域名最长255个字符");
		}
		if(StringUtils.length(appDevelopConfig.getTestDatabase()) > 255){
			errors.rejectValue(AppDevelopConfig.PROPERTY_TEST_DATABASE,null,"测试环境DB最长255个字符");
		}
		if(StringUtils.length(appDevelopConfig.getTestDomainName()) > 255){
			errors.rejectValue(AppDevelopConfig.PROPERTY_TEST_DOMAIN_NAME,null,"测试环境域名最长255个字符");
		}
		if(StringUtils.length(appDevelopConfig.getProductionDatabase()) > 255){
			errors.rejectValue(AppDevelopConfig.PROPERTY_PRODUCTION_DATABASE,null,"生产环境DB最长255个字符");
		}
		if(StringUtils.length(appDevelopConfig.getProductionDomainName()) > 255){
			errors.rejectValue(AppDevelopConfig.PROPERTY_PRODUCTION_DOMAIN_NAME,null,"生产环境域名最长255个字符");
		}
	}
}