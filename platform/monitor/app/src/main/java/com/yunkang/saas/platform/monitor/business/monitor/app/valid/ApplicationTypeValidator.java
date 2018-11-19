package com.yunkang.saas.platform.monitor.business.app.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationType;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationTypeAddDto;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationTypeCondition;
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
            this.validateApplicationTypeAddDto((ApplicationTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ApplicationTypeCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ApplicationTypeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param applicationType 程序类型
     * @param errors
     */
	public void validateApplicationTypeAddDto(ApplicationTypeAddDto applicationType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(applicationType.getCode()) > 255){
			errors.rejectValue(ApplicationType.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(applicationType.getName()) > 255){
			errors.rejectValue(ApplicationType.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(applicationType.getIcon()) > 255){
			errors.rejectValue(ApplicationType.PROPERTY_ICON,null,"图标最长255个字符");
		}
	}
}