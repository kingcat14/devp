package com.yunkang.saas.platform.monitor.business.monitor.app.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationAddDto;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationEditDto;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationCondition;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ApplicationValidator implements Validator {

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
	    if(obj instanceof ApplicationAddDto){
            this.validateApplicationAddDto((ApplicationAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ApplicationCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ApplicationCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param application 程序
     * @param errors
     */
	public void validateApplicationAddDto(ApplicationAddDto application, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(application.getName())){
			errors.rejectValue(Application.PROPERTY_NAME, "EMPTY_"+Application.PROPERTY_NAME, "名称不能为空");
		}

		//验证长度
		if(StringUtils.length(application.getName()) > 255){
			errors.rejectValue(Application.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(application.getCode()) > 255){
			errors.rejectValue(Application.PROPERTY_CODE,null,"代码最长255个字符");
		}
	}
}