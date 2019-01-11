package com.yunkang.saas.platform.monitor.business.monitor.app.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.app.dto.UnknownAppAddDto;
import com.yunkang.saas.platform.monitor.business.app.dto.UnknownAppEditDto;
import com.yunkang.saas.platform.monitor.business.app.dto.UnknownAppCondition;
import com.yunkang.saas.platform.monitor.business.app.domain.UnknownApp;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UnknownAppValidator implements Validator {

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
	    if(obj instanceof UnknownAppAddDto){
            this.validateUnknownAppAddDto((UnknownAppAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<UnknownAppCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new UnknownAppCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param unknownApp 未知程序
     * @param errors
     */
	public void validateUnknownAppAddDto(UnknownAppAddDto unknownApp, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(unknownApp.getCode()) > 255){
			errors.rejectValue(UnknownApp.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(unknownApp.getStatus()) > 255){
			errors.rejectValue(UnknownApp.PROPERTY_STATUS,null,"状态最长255个字符");
		}
	}
}