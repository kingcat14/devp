package net.aicoder.speedcloud.business.app.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.domain.SecurityConfig;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class SecurityConfigValidator implements Validator {

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
	    if(obj instanceof SecurityConfigAddDto){
            this.validateSecurityConfigAddDto((SecurityConfigAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<SecurityConfigCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new SecurityConfigCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param securityConfig 应用私密配置
     * @param errors
     */
	public void validateSecurityConfigAddDto(SecurityConfigAddDto securityConfig, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(securityConfig.getItemName()) > 255){
			errors.rejectValue(SecurityConfig.PROPERTY_ITEM_NAME,null,"配置名最长255个字符");
		}
		if(StringUtils.length(securityConfig.getItemValue()) > 255){
			errors.rejectValue(SecurityConfig.PROPERTY_ITEM_VALUE,null,"配置值最长255个字符");
		}
	}
}