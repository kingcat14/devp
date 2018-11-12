package com.yunkang.saas.platform.monitor.business.app.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationInstanceAddDto;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationInstanceEditDto;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationInstanceCondition;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ApplicationInstanceValidator implements Validator {

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
	    if(obj instanceof ApplicationInstanceAddDto){
            this.validateApplicationInstanceAddDto((ApplicationInstanceAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ApplicationInstanceCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ApplicationInstanceCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param applicationInstance 程序实例
     * @param errors
     */
	public void validateApplicationInstanceAddDto(ApplicationInstanceAddDto applicationInstance, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(applicationInstance.getApp()) > 255){
			errors.rejectValue(ApplicationInstance.PROPERTY_APP,null,"app最长255个字符");
		}
		if(StringUtils.length(applicationInstance.getIp()) > 255){
			errors.rejectValue(ApplicationInstance.PROPERTY_IP,null,"ip最长255个字符");
		}
	}
}